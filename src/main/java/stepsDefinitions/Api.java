package stepsDefinitions;

import api.BaseClass;
import cucumber.api.DataTable;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Quando;

import static support.Utils.gerarJson;

public class Api extends BaseClass{
    @E("^que que não tenha cookies, pegue os cookies$")
    public void queQueNaoTenhaCookiesPegueOsCookies() {
        getCookies();
    }

    @Quando("^enviar o payload para o endpoint \"([^\"]*)\"$")
    public void enviarOPayloadParaOEndpoint(String endpoint, DataTable table) {
        String json = gerarJson(table);
        enviarPayload(endpoint, json);
    }
}
