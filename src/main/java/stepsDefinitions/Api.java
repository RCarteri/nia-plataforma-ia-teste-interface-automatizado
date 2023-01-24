package stepsDefinitions;

import cucumber.api.java.pt.*;
import support.APIRest.BaseClass;

import java.util.List;

import static br.com.bb.ath.ftabb.gaw.Plataforma.fecharPlataforma;
import static org.hamcrest.Matchers.containsString;
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
        printSiglasExistentes();
    }

    @E("^que não tenha cookies, pegue os cookies$")
    public void queNaoTenhaCookiesPegueOsCookies() {
        getCookies();
        fecharPlataforma();
    }

    @Dado("^que tenha a lista de retorno do \"([^\"]*)\" no endpoint \"([^\"]*)\"$")
    public void queTenhaAListaDeRetornoDoComponenteNoEndpoint(String componente, String endpoint){
        setPayload(endpoint, componente);
        tratarPayload(componente);
        enviarPayload(endpoint);
        salvarListaDados(componente);
    }

    @Quando("^enviar um payload \"([^\"]*)\" com o código do componente selecionado$")
    public void enviarUmPayloadComOCodigoDoComponenteSelecionado(String subComponente) {
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

    @E("^que um dos membros não esteja cadastrado$")
    public void queUmDosMembrosNaoEstejaCadastrado(List<List<String>> membros) {
        setMembroParaIncluir(membros);
    }

    @E("^a mensagem de erro deve conter \"([^\"]*)\"$")
    public void aMensagemDeErroDeveConter(String mensagem) {
        response.then().assertThat()
                .body(containsString(mensagem));
    }
}
