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
import static support.enums.SelectorsDelays.CARR_PAG;
import static support.enums.User.getUser;

public class Componente extends Utils{
    private final ComponentePage cP;
    private final PanelContentSection pCS;

    public Componente() {
        this.cP = new ComponentePage();
        this.pCS = new PanelContentSection();
    }

    @Quando("^selecionar o componente \"([^\"]*)\"$")
    public void selecionarOComponente(String componente) {
        try {
            cP.acessarComponente(componente);
            assertNotNull(cP.getTxtTituloComponente());
            waitLoadPage(CARR_PAG);
        } catch (Exception e) {
            logError(e);
        }
    }

    @Então("^deverá apresentar o título \"([^\"]*)\" na página$")
    public void deveApresentarOTituloNaPagina(String titulo) {
        try {
            assertEquals(titulo, cP.getTxtTituloComponente());
        } finally {
            capturaTela();
        }
    }

    @Dado("^que não exista \"([^\"]*)\"$")
    public void naoExistirOpcao(String opcao) {
        try {
            assertFalse("Todos os projetos possuem " + opcao + "+.\nNão foi possível realizar este teste.",
                    pCS.existeOpcao(false, false, opcao));
        } catch (Exception e) {
            logError(e);
        }
    }

    @Dado("^que exista \"([^\"]*)\"$")
    public void existirOpcao(String opcao) {
        try {
            assertTrue("Nenhum projeto possui " + opcao + ".\nNão foi possível realizar este teste.",
                    pCS.existeOpcao(true, false, opcao));
        } finally {
            capturaTela();
        }
    }

    @Dado("^que exista \"([^\"]*)\" onde o usuário logado seja o administrador$")
    public void queExistaOndeOUsuarioLogadoSejaOAdministrador(String opcao) {
        try {
            assertTrue("O usuário logado '" + getUser() + "' não é administrador de nenhum projeto.\nNão foi possível realizar este teste.",
                    pCS.existeOpcao(true, true, opcao));
        } finally {
            capturaTela();
        }
    }

    @Quando("^editar o papel de um membro$")
    public void editarOPapelDeUmMembro() {
        try {
            new ModalComponentePage().editarPapel(pCS.getIndexADM());
        } catch (Exception e){
            capturaTela();
        }
    }

    @E("^deverá apresentar o mesmo nome do item selecionado$")
    public void deveraApresentarOMesmoNomeDoItemSelecionado() {
        ModalComponentePage mCP = new ModalComponentePage();
        try {
            assertTrue(printResultadoEsperadoObtido(pCS.getNomeItemSelecionado(), mCP.getNomeElemento()),
                    mCP.isNomeIgual(pCS.getNomeItemSelecionado()));
        } catch (Exception e) {
            logError(e);
        }
    }

    @Então("^deverá ser apresentado o alerta de \"([^\"]*)\" com a mensagem \"([^\"]*)\"$")
    public void deveraSerApresentadoOAlertaComAMensagem(String opcao, String mensagem) {
        try {
            assertEquals(printResultadoEsperadoObtido(mensagem, cP.getTxtMensagemAlerta(opcao)),
                    mensagem, cP.getTxtMensagemAlerta(opcao));
        } finally {
            capturaTela();
        }
        cP.clickBtnFechar(false, "alerta");
    }
}
