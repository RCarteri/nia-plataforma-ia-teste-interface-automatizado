package support.APIRest;

import br.com.bb.ath.ftabb.exceptions.DataPoolException;
import br.com.bb.ath.ftabb.utilitarios.FTABBUtils;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import support.Utils;

import static io.qameta.allure.Allure.descriptionHtml;
import static io.qameta.allure.Allure.link;
import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.JSON;
import static java.lang.String.valueOf;
import static java.lang.System.setProperty;
import static support.GetElements.getDriver;
import static support.Utils.isQteste;
import static support.Utils.printLog;
import static support.enums.Ambiente.API;
import static support.enums.Cookie.*;
import static support.enums.LogTypes.INFO;

public class BaseClass extends FTABBUtils {
    protected final String BASE_URL = "https://plataforma.desenv.bb.com.br/nia-plat-ia-api/v3/api/nia";
    private RequestSpecification request;
    private String endpoint;
    private String payload;
    protected Response response;

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setPayload(String tipoPayload) {
        payload = getPayload(tipoPayload, getEndpoint());
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
        baseURI = BASE_URL;
        request = given()
                .contentType(JSON)
                .cookie("IBBID", getIBBID())
                .relaxedHTTPSValidation();
    }

    protected void enviarPayload() {
        setRequest();
        response = request.given()
                .body(payload)
                .when()
                .post(endpoint);
        setTable();
    }

    protected void printResult() {
        System.out.println("---------------------------------------------------------");
        printLog("Request enviado para o endpoint: " + endpoint, INFO);
        System.out.println(payload);
        printLog("Recebeu o seguinte retorno com o status code:" + response.getStatusLine().replace("HTTP/1.1", ""), INFO);
        response.body().prettyPrint();
    }

    private String getPayload(String tipoPayload, String endpoint) {
        String param = "payloads." + endpoint + "." + tipoPayload;
        try {
            return String.valueOf($(param));
        } catch (DataPoolException e) {
            new Utils().logError(e);
            return null;
        }
    }

    private String getDescricao(String endpoint) {
        String param = "payloads." + endpoint + ".descrição";
        try {
            return String.valueOf($(param));
        } catch (DataPoolException e) {
            new Utils().logError(e);
            return null;
        }
    }

    public String gerarHtmlRequisicaoPost() {
        return "<table><tr><td colspan=\"2\" style=\"padding:12px;text-align: left;background-color:#f0f2f4;font-weight:bold;\">" +
                "POST" +
                "</td></tr><tr><td style=\"min-width:100px;padding:12px;\">" +
                "Descrição</td><td>" +
                getDescricao(getEndpoint()) +
                "</td></tr><tr><td style=\"min-width:100px;padding:12px;\">Endpoint</td><td>" +
                endpoint +
                "</td></tr><tr><td style=\"min-width:100px;padding:12px;\">Status Code</td><td>" +
                response.getStatusLine().replace("HTTP/1.1 ", "") +
                "</td></tr><tr><td style=\"min-width:100px;padding:12px;\">Request</td><td>" +
                payload +
                "</td></tr><tr><td style=\\\"min-width:100px;padding:12px;\\\">Response</td><td>\"" +
                response.body().print() +
                "</td></tr></table>";
    }

    public void setTable() {
        descriptionHtml(gerarHtmlRequisicaoPost());
        String url = API.getUrl() + endpoint.replace("-", "_");
        link(endpoint, "POST", url);
    }
}
