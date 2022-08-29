package stepsDefinitions;

import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import support.APIRest.BaseClass;

public class Api extends BaseClass {

    @E("^que defina o endpoint \"([^\"]*)\"$")
    public void definaOEndpoint(String endpoint) {
        setEndpoint(endpoint);
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

    @Quando("^definir a chave a ser usada enviando um payload \"([^\"]*)\"$")
    public void definirAChaveASerUsadaEnviandoUmPayload(String tipoPayload) {
        definirChave(tipoPayload);
        enviarPayload();
    }

    @E("^que não tenha cookies, pegue os cookies$")
    public void queNaoTenhaCookiesPegueOsCookies() {
        getCookies();
    }
}
