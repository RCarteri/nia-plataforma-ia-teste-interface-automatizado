package stepsDefinitions;

import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import support.APIRest.BaseClass;

import static br.com.bb.ath.ftabb.gaw.Plataforma.fecharPlataforma;

public class Api extends BaseClass {

    @E("^que defina o endpoint \"([^\"]*)\"$")
    public void definaOEndpoint(String endpoint) {
        setEndpoint(endpoint);
    }

    @Quando("^enviar um payload \"([^\"]*)\"$")
    public void enviarUmPayload(String tipoPayload) {
        setPayload(tipoPayload);
        tratarPayload(tipoPayload);
        enviarPayload();
    }

    @Então("^deve retornar o código (\\d+)$")
    public void deveRetornarOCodigo(int sc) {
        response.then().assertThat()
                .statusCode(sc);
    }

    @Quando("^definir a chave a ser usada enviando um payload \"([^\"]*)\"$")
    public void definirAChaveASerUsadaEnviandoUmPayload(String tipoPayload) {
        setPayload(tipoPayload);
        tratarPayload(tipoPayload);
        enviarPayload();
    }

    @E("^que não tenha cookies, pegue os cookies$")
    public void queNaoTenhaCookiesPegueOsCookies() {
        getCookies();
        fecharPlataforma();
    }

    @Dado("^que tenha a lista de \"([^\"]*)\" \"([^\"]*)\" no endpoint \"([^\"]*)\"$")
    public void queTenhaAListaDeDadosDoComponenteNoEndpoint(String local, String componente, String endpoint){
        setPayload(endpoint, componente);
        tratarPayload(componente);
        enviarPayload(endpoint);
    }

    @Quando("^enviar um payload \"([^\"]*)\" com o código do \"([^\"]*)\" selecionado aleatóriamente$")
    public void enviarUmPayloadComOCodigoDaListaDeDadosDoComponente(String subComponente, String codComponente) {
        setPayload(subComponente);
        tratarPayload(subComponente);
        enviarPayload();
    }

    @Então("^salvar os resultados do response$")
    public void salvarOsResultadosDoResponse() {
        setListaRetorno();
    }
}
