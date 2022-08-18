package stepsDefinitions;

import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import support.APIRest.BaseClass;

public class Api extends BaseClass {

    @E("^que não tenha cookies, pegue os cookies para o endpoint \"([^\"]*)\"$")
    public void queNaoTenhaCookiesPegueOsCookiesParaOEndpoint(String endpoint) {
        setEndpoint(endpoint);
        getCookies();
    }

    @Quando("^enviar um payload \"([^\"]*)\"$")
    public void enviarUmPayloadParaOEndpoint(String tipoPayload) {
        setPayload(tipoPayload);
        enviarPayload();
    }

    @Então("^deve retornar o código (\\d+)$")
    public void deveRetornarOCodigo(int sc) {
        printResult();
        response.then().assertThat()
                .statusCode(sc);
    }
}
