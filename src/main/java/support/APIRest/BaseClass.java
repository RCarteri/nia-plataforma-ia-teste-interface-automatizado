package support.APIRest;

import br.com.bb.ath.ftabb.utilitarios.FTABBUtils;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONArray;
import support.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.stream.Collectors;

import static io.qameta.allure.Allure.descriptionHtml;
import static io.qameta.allure.Allure.link;
import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.JSON;
import static java.lang.String.valueOf;
import static java.lang.System.setProperty;
import static java.util.regex.Pattern.compile;
import static support.APIRest.DadosSelecionadosApi.getInstanceDSApi;
import static support.GetElements.getDriver;
import static support.Utils.isQteste;
import static support.Utils.printLog;
import static support.enums.Ambiente.*;
import static support.enums.Cookie.*;
import static support.enums.LogTypes.INFO;

public class BaseClass extends FTABBUtils {
    private RequestSpecification request;
    private String endpoint;
    private String payload;
    public Response response;
    private JSONArray listaRetorno;
    private final Utils utils = new Utils();

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public void getCookies() {
        if (isLoggedIntranet()) {
            printLog("Cookies já estão salvos.", INFO);
        } else {
            setProperty(IBBID.toString(), valueOf(getDriver().manage().getCookieNamed("IBBID").getValue()));
        }
    }

    private void setProxy() {
        if (!isQteste())
            proxy("170.66.49.180", 3128);
    }

    private void setRequest() {
        setProxy();
        if (endpoint.contains("/")) {
            baseURI = BASE_URL_INTRANET.getUrl();
            request = given()
                    .contentType(JSON)
                    .cookie("IBBID", getIBBID())
                    .relaxedHTTPSValidation();
        } else {
            baseURI = BASE_URL.getUrl();
            request = given()
                    .contentType(JSON)
                    .relaxedHTTPSValidation();
        }
    }

    public void enviarPayload() {
        setRequest();
        response = request.given()
                .body(payload)
                .when()
                .post(endpoint);
        setTable();
        printResult();
    }

    protected void enviarPayload(String endpoint) {
        setRequest();
        response = request.given()
                .body(payload)
                .when()
                .post(endpoint);
        setListaRetorno();
        printResult();
    }

    protected void printResult() {
        System.out.println("---------------------------------------------------------");
        printLog("Request enviado para a URL: " + baseURI + endpoint, INFO);
        System.out.println(payload);
        printLog("Recebeu o seguinte retorno com o status code:" + response.getStatusLine().replace("HTTP/1.1", ""), INFO);
        response.body().prettyPrint();
    }

    public void salvarListaDados(){
        getInstanceDSApi().setListaDadosResponseComponente(listaRetorno);
    }

    public void setListaRetorno() {
        ArrayList<Map<String, String>> ArrayListListaRetorno = response.jsonPath().get("listaRetorno");
        listaRetorno = new JSONArray(ArrayListListaRetorno);
        listaRetorno = new TratarListaRetorno(listaRetorno, endpoint).tratarListaRetorno();
    }

    public void setPayload(String tipoPayload) {
        setPayload(endpoint, tipoPayload);
    }

    private List<HashMap<String, Object>> jsonArraytoListHashMap(JSONArray jsonArray){
        return jsonArray.toList().stream()
                .map(m -> ((HashMap<String, Object>) m))
                .collect(Collectors.toList());
    }

    public boolean isUsuarioLogado(String papel){
        List<HashMap<String, Object>> listaRetorno = jsonArraytoListHashMap(this.listaRetorno);


        return true;
    }

    protected void setPayload(String endpoint, String componente) {
        this.endpoint = endpoint;
        payload = utils.getPayload(this.endpoint, componente);
    }

    public void tratarPayload(String componente) {
        payload = new TratarPayload(payload, listaRetorno).tratarPayload(componente, endpoint);
    }

    private String getDescricao() {
        return utils.getPayload(endpoint, "descrição");
    }

    public String gerarHtmlRequisicaoPost() {
        return "<table><tr><td colspan=\"2\" style=\"padding:12px;text-align: left;background-color:#f0f2f4;font-weight:bold;\">" +
                "POST" +
                "</td></tr><tr><td style=\"min-width:100px;padding:12px;\">" +
                "Descrição</td><td>" +
                getDescricao() +
                "</td></tr><tr><td style=\"min-width:100px;padding:12px;\">URL</td><td>" +
                BASE_URL.getUrl() + endpoint +
                "</td></tr><tr><td style=\"min-width:100px;padding:12px;\">Status Code</td><td>" +
                response.getStatusLine().replace("HTTP/1.1 ", "") +
                "</td></tr><tr><td style=\"min-width:100px;padding:12px;\">Request payload</td><td>" +
                payload +
                "</td></tr><tr><td style=\\\"min-width:100px;padding:12px;\\\">Response body</td><td>\"" +
                response.body().print() +
                "</td></tr></table>";
    }

    private void setTable() {
        descriptionHtml(gerarHtmlRequisicaoPost());
        link("Logar na intranet", DESENV.getUrl());
        link("Acessar " + endpoint, getUrl());
    }

    private String getUrl() {
        String endpoint;
        Matcher matcher = compile("[a-z]{3}").matcher(this.endpoint);
        if (matcher.find()) {
            endpoint = this.endpoint.substring(0, 3).toUpperCase() + this.endpoint.substring(3).replace("-", "_");
            return API.getUrl() + endpoint;
        } else {
            endpoint = this.endpoint.substring(0, 1).toUpperCase() + this.endpoint.substring(1, 9) + "_" + this.endpoint.substring(9);
            return API.getUrl() + "NIA/" + endpoint;
        }
    }
}
