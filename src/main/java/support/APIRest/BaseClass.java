package support.APIRest;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import support.Utils;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.JSON;
import static java.lang.System.setProperty;
import static support.GetElements.getDriver;
import static support.Utils.printLog;
import static support.enums.Cookie.*;
import static support.enums.LogTypes.INFO;

public class BaseClass {
    protected final String BASE_URL = "https://plataforma.desenv.bb.com.br/nia-plat-ia-api/v3/api/nia";
    private RequestSpecification request;
    private String endpoint;
    private String payload;
    protected Response response;
    protected Map<String, String> json = new HashMap<>();

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public void setPayload(String tipoPayload) {
        this.payload = new Utils().getPayload(tipoPayload, getEndpoint());
    }

    public void getCookies() {
        if (isLoggedIntranet()) {
            printLog("Cookies já estão salvos.", INFO);
        } else {
            setProperty(IBBID.toString(), String.valueOf(getDriver().manage().getCookieNamed("IBBID").getValue()));
        }
    }

    private void setRequest() {
        proxy("170.66.49.180", 3128);
        baseURI = BASE_URL;
        request = given()
                .contentType(JSON)
                .cookie("IBBID", getIBBID())
                .relaxedHTTPSValidation();
    }

    public RequestSpecification getRequest() {
        return request;
    }

    protected void enviarPayload() {
        setRequest();
        response = request.given()
                .body(payload)
                .when()
                .post(endpoint);
    }

    protected void printResult() {
        System.out.println("---------------------------------------------------------");
        printLog("Request enviado para o endpoint: " + endpoint, INFO);
        System.out.println(payload);
        printLog("Recebeu o seguinte retorno com o status code:" + response.getStatusLine().replace("HTTP/1.1", ""),  INFO);
        response.body().prettyPrint();
    }
}
