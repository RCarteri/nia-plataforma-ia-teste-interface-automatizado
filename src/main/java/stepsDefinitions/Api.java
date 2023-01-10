package stepsDefinitions;

import cucumber.api.java.pt.*;
import support.APIRest.BaseClass;

import static br.com.bb.ath.ftabb.gaw.Plataforma.fecharPlataforma;
import static org.junit.Assert.assertTrue;

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

    @Dado("^que tenha a lista de retorno do \"([^\"]*)\" no endpoint \"([^\"]*)\"$")
    public void queTenhaAListaDeDadosDoComponenteNoEndpoint(String componente, String endpoint){
        setPayload(endpoint, componente);
        tratarPayload(componente);
        enviarPayload(endpoint);
        salvarListaDados(componente);
    }

    @Quando("^enviar um payload \"([^\"]*)\" com o código do \"([^\"]*)\" selecionado aleatóriamente$")
    public void enviarUmPayloadComOCodigoDaListaDeDadosDoComponente(String subComponente, String codComponente) {
        setPayload(subComponente);
        tratarPayload(subComponente);
        enviarPayload();
    }

    @Mas("^o papel do usuário logado precisa ser \"([^\"]*)\" no projeto selecionado$")
    public void queOPapelDoUsuarioLogadoPrecisaSer(String papel) {
        setListaRetorno();
        assertTrue(verificarMembrosProjeto(papel));
    }

    @E("^que selecione um membro da lista que não seja o usuário logado$")
    public void queSelecioneUmMembroDaListaQueNaoSejaOUsuarioLogado() {
        setMembro();
    }
}
