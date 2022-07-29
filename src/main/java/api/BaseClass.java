package api;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static io.restassured.RestAssured.proxy;
import static support.GetElements.getDriver;
import static support.Utils.printLog;
import static support.enums.LogTypes.INFO;
import static support.enums.SysProps.isLogged;

public class BaseClass {
    protected final String BASE_ENDPOINT = "https://plataforma.desenv.bb.com.br/nia-plat-ia-api/v3/api/nia";
    private RequestSpecification request;
    protected String IBBID;
    protected Map<String, String> json = new HashMap<>();

    public void getCookies() {
        if (isLogged()) {
            printLog("Cookies já estão salvos.", INFO);
        } else {
            IBBID = getDriver().manage().getCookieNamed("IBBID").getValue();
            setRequest();
        }
    }

    private void setRequest() {
        proxy("170.66.49.180", 3128);
        request = given()
                .contentType("application/json")
                .cookie("IBBID", IBBID)
                .relaxedHTTPSValidation();
    }

    public RequestSpecification getRequest() {
        return request;
    }

    protected void enviarPayload(String endpoint, String json) {
        Response response = getRequest().given()
                .body(json)
                .when()
                .post(BASE_ENDPOINT + endpoint);
        response.body().print();
    }
}
