package support.APIRest;

import br.com.bb.ath.ftabb.utilitarios.FTABBUtils;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

import static io.qameta.allure.Allure.descriptionHtml;
import static io.qameta.allure.Allure.link;
import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.JSON;
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
    private List<Map<String, String>> yamlMap;

    public void setEndpoint(String endpoint) {
        printLog("Definindo endpoint.", INFO);
        this.endpoint = endpoint;
        printLog("Endpoint definido.", INFO);
    }

    public void definirChave(String tipoPayload) {
        setPayload(tipoPayload);
        printLog("Definindo chave.", INFO);
        payload = payload.replace("CHAVE_USUARIO", getChave());
        printLog("Chave definida.", INFO);
    }

    public void getCookies() {
        if (isLoggedIntranet()) {
            printLog("Cookies já estão salvos.", INFO);
        } else {
            printLog("Cookies: " + getDriver().manage().getCookieNamed("IBBID").getValue(), INFO);
            setProperty(IBBID.toString(), getDriver().manage().getCookieNamed("IBBID").getValue());
        }
    }

    private void setProxy() {
        if (!isQteste()) {
            printLog("Definindo proxy.", INFO);
            proxy("170.66.49.180", 3128);
            printLog("Proxy definido.", INFO);
        }
    }

    private void setRequest() {
        setProxy();
        printLog("Definindo request de desenvolvimento.", INFO);
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
        printLog("Request definido.", INFO);
    }

    protected void enviarPayload() {
        setRequest();
        printLog("Enviando request.", INFO);
        response = request.given()
                .body(payload)
                .when()
                .post(endpoint);
        printLog("Request enviado.", INFO);
        setTable();
    }

    protected void printResult() {
        System.out.println("---------------------------------------------------------");
        printLog("Request enviado para o endpoint: " + endpoint, INFO);
        System.out.println(payload);
        printLog("Recebeu o seguinte retorno com o status code:" + response.getStatusLine().replace("HTTP/1.1", ""), INFO);
        response.body().prettyPrint();
    }

    protected void setPayload(String tipoPayload) {
        printLog("Definindo payload.", INFO);
        yamlMap = getYamlMap("api", endpoint);
        payload = getValueYamlMap(yamlMap, tipoPayload);
        printLog("Payload definido.", INFO);
    }

    private String getDescricao() {
        return getValueYamlMap(yamlMap, "descrição");
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
