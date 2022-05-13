package stepsDefinitions.ibmCloud;

import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import pagesObjects.ComponentePage;
import pagesObjects.ModalComponentePage;
import pagesObjects.sections.PanelContentSection;
import support.Utils;

import static org.junit.Assert.*;
import static support.Utils.printResultadoEsperadoObtido;
import static support.enums.User.getUser;

public class Componente {
    private final ComponentePage cP;
    private final PanelContentSection pCS;
    private final Utils utils;

    public Componente() {
        this.cP = new ComponentePage();
        this.pCS = new PanelContentSection();
        this.utils = new Utils();
    }

    @Quando("^selecionar o componente \"([^\"]*)\"$")
    public void selecionarOComponente(String componente) {
        cP.acessarComponente(componente);
        assertNotNull(cP.getTxtTituloComponente());
    }

    @Então("^deverá apresentar o título \"([^\"]*)\" na página$")
    public void deveApresentarOTituloNaPagina(String titulo) {
       utils.capturaTela();
       assertEquals(titulo, cP.getTxtTituloComponente());
    }

    @Dado("^que não exista \"([^\"]*)\"$")
    public void naoExistirOpcao(String opcao) {
        assertFalse("Todos os projetos possuem " + opcao + "+.\nNão foi possível realizar este teste.",
                pCS.existeOpcao(false, false, opcao));
        utils.capturaTela();
    }

    @Dado("^que exista \"([^\"]*)\"$")
    public void existirOpcao(String opcao) {
        assertTrue("Nenhum projeto possui " + opcao + ".\nNão foi possível realizar este teste.",
                pCS.existeOpcao(true, false, opcao));
        utils.capturaTela();
    }

    @Dado("^que exista \"([^\"]*)\" onde o usuário logado seja o administrador$")
    public void queExistaOndeOUsuarioLogadoSejaOAdministrador(String opcao) {
        assertTrue("O usuário logado '" + getUser() + "' não é administrador de nenhum projeto.\nNão foi possível realizar este teste.",
                pCS.existeOpcao(true, true, opcao));
        utils.capturaTela();
    }

    @Quando("^escolher um papel diferente$")
    public void escolherUmPapelDiferente() {
        new ModalComponentePage().editarPapel(pCS.getIndexADM());
        utils.capturaTela();
    }

    @E("^deverá apresentar o mesmo nome do item selecionado$")
    public void deveraApresentarOMesmoNomeDoItemSelecionado() {
        ModalComponentePage mCP = new ModalComponentePage();
        assertTrue(printResultadoEsperadoObtido(pCS.getNomeItemSelecionado(), mCP.getNomeElemento()),
                mCP.isNomeIgual(pCS.getNomeItemSelecionado()));
    }

    @Então("^deverá ser apresentado o alerta de \"([^\"]*)\" com a mensagem \"([^\"]*)\"$")
    public void deveraSerApresentadoOAlertaComAMensagem(String opcao, String mensagem) {
        assertEquals(printResultadoEsperadoObtido(mensagem,cP.getTxtMensagemAlerta(opcao)),
                mensagem, cP.getTxtMensagemAlerta(opcao));
        cP.clickBtnFechar(false,"alerta");
    }
}
