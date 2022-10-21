package support.APIRest;

import br.com.bb.ath.ftabb.utilitarios.FTABBUtils;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONArray;
import org.json.JSONObject;
import pagesObjects.LoginPage;
import stepsDefinitions.Api;
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
import static java.util.stream.Collectors.joining;
import static support.GetElements.getDriver;
import static support.Utils.*;
import static support.enums.Ambiente.*;
import static support.enums.Cookie.*;
import static support.enums.LogTypes.INFO;
import static support.enums.Siglas.getInstance;
import static support.enums.User.getChave;
import static support.enums.User.getUser;

public class BaseClass extends FTABBUtils {
    private RequestSpecification request;
    private String endpoint;
    private String endpointTratar;
    private String payload;
    protected Response response;
    private ArrayList<Map<String, String>> listaRetorno;
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

    protected void enviarPayload() {
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
        printLog("Request enviado para o endpoint: " + endpoint, INFO);
        System.out.println(payload);
        printLog("Recebeu o seguinte retorno com o status code:" + response.getStatusLine().replace("HTTP/1.1", ""), INFO);
        response.body().prettyPrint();
    }

    private String getCodComponente() {
        if (listaRetorno == null) return "";
        JSONObject componente = tratarListaRetorno();
        if (componente.get("nomeComponente").toString().equals("null")) {
            printLog("Nome do componente escolhido: " + componente.get("nome"), INFO);
            return (String) componente.get("id");
        } else {
            printLog("Nome do componente escolhido: " + componente.get("nomeComponente"), INFO);
            return (String) componente.get("codigoComponente");
        }
    }

    private JSONObject tratarListaRetorno() {
        JSONArray list = null;
        switch (endpointTratar) {
            case "op5806077v2":
                list = new JSONArray(listaRetorno);
                break;
            case "op5839181v1":
                String newList = listaRetorno.stream()
                        .map(item -> item.get("nomeComponente"))
                        .collect(joining());
                list = new JSONArray(newList);
                break;
        }
        assert list != null;
        int index = getRandom(list.length());
        return list.getJSONObject(index);
    }

    private void setListaRetorno() {
        listaRetorno = response.jsonPath().get("listaRetorno");
    }

    protected void setPayload(String tipoPayload) {
        setPayload(endpoint, tipoPayload);
    }

    protected void setPayload(String endpoint, String componente) {
        if(endpointTratar == null) endpointTratar = endpoint;
        payload = utils.getPayload(endpoint, componente);
    }

    protected void tratarPayload(String componente) {
        if (getUser() == null)
            new Utils().setDatapool("desenvolvimento");
        payload = payload
                .replaceFirst("nameComponente", componente)
                .replaceFirst("chaveUsuario", getChave());

        if (componente.equals("WATSON_STUDIO"))
            setSigla();
    }

    protected void tratarPayload(String componente, String subComponente) {
        if (getUser() == null)
            new Utils().setDatapool("desenvolvimento");
        payload = payload
                .replaceFirst("codComponente", getCodComponente())
                .replaceFirst("nameComponente", subComponente)
                .replaceFirst("_MEMBROS", "");

        if (componente.equals("WATSON_STUDIO"))
            setSigla();
    }

    private void setSigla() {
        if (getInstance().getSiglas() == null)
            getSiglas();
        else
            printLog("As siglas do Usuário logado '" + getUser() + "' já estão em memória: " + getInstance().getSiglas(), INFO);
        List<String> siglas = getInstance().getSiglas();
        String sigla = siglas.get(getRandom(siglas.size()));
        payload = payload.replace("sigla", sigla);
    }

    private void getSiglas() {
        BaseClass bC = new BaseClass();
        initDesenv();
        bC.setEndpoint("dpr/Op5903588-v1");
        bC.setPayload("OK");
        bC.tratarPayload("OK");
        bC.enviarPayload();
        String path = "data.listaOcorrencia.siglaSistemaSoftware";
        List<String> siglas = bC.response.body().jsonPath().get(path);
        getInstance().setSiglas(siglas);
    }

    private void initDesenv() {
        new LoginPage().abraPlataforma();
        new LoginPage().logar("desenvolvimento");
        new Api().queNaoTenhaCookiesPegueOsCookies();
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
