package support.APIRest;

import br.com.bb.ath.ftabb.utilitarios.FTABBUtils;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONObject;
import support.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

import static io.qameta.allure.Allure.descriptionHtml;
import static io.qameta.allure.Allure.link;
import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.JSON;
import static java.lang.String.valueOf;
import static java.lang.System.setProperty;
import static java.util.regex.Pattern.compile;
import static support.GetElements.getDriver;
import static support.Utils.*;
import static support.enums.Ambiente.*;
import static support.enums.Cookie.*;
import static support.enums.LogTypes.INFO;
import static support.enums.User.getChave;

public class BaseClass extends FTABBUtils {
    private RequestSpecification request;
    private String endpoint;
    private String payload;
    protected Response response;
    private ArrayList<Map<String, String>> listaRetorno;
    private final Utils utils = new Utils();

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public void setChave(String tipoPayload) {
        setPayload(tipoPayload);
        new Utils().setDatapool("desenvolvimento");
        payload = payload.replace("chaveUsuario", getChave());
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

    protected void enviarPayload() {
        setRequest();
        response = request.given()
                .body(payload)
                .when()
                .post(endpoint);
        setTable();
    }

    protected void enviarPayload(String endpoint) {
        setRequest();
        response = request.given()
                .body(payload)
                .when()
                .post(endpoint);
        setListaRetorno();
    }

    protected void printResult() {
        System.out.println("---------------------------------------------------------");
        printLog("Request enviado para o endpoint: " + endpoint, INFO);
        System.out.println(payload);
        printLog("Recebeu o seguinte retorno com o status code:" + response.getStatusLine().replace("HTTP/1.1", ""), INFO);
        response.body().prettyPrint();
    }

    private String getCodComponente() {
        int indexRandom = getRandom(listaRetorno.size());
        printLog("Nome do componente escolhido: " + listaRetorno.get(indexRandom).get("nomeComponente"), INFO);
        return listaRetorno.get(indexRandom).get("codigoComponente");
    }

    private String getNameComponente(String componente) {
        String str = utils.getPayload("op5806077v2", componente);
        return new JSONObject(str).getString("nomeComponente");
    }

    protected void setConfComponenteNoPayload(String tipoPayload, String componente) {
        setPayload(tipoPayload);
        payload = payload
                .replace("codComponente", getCodComponente())
                .replace("nameComponente", getNameComponente(componente));
    }

    private void setListaRetorno() {
        listaRetorno = response.jsonPath().get("listaRetorno");
    }

    protected void setPayload(String endpoint, String tipoPayload) {
        payload = utils.getPayload(endpoint, tipoPayload);
    }

    protected void setPayload(String tipoPayload) {
        payload = utils.getPayload(endpoint, tipoPayload);
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
