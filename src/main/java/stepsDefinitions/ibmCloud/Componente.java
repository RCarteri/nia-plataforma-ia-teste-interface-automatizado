package stepsDefinitions.ibmCloud;

import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import pagesObjects.ComponentePage;
import pagesObjects.ModalComponentePage;
import pagesObjects.PanelContentSection;
import support.Utils;

import static org.junit.Assert.*;
import static support.Utils.printResultadoEsperadoObtido;

public class Componente {
    private final ComponentePage cP = new ComponentePage();
    private final PanelContentSection pCS = new PanelContentSection();
    private final Utils utils = new Utils();
    private String nomeItemSelecionado;

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

    @Quando("^não existir \"([^\"]*)\"$")
    public void naoExistirOpcao(String opcao) {
        assertFalse("Todos os projetos possuem " + opcao + "+.\nNão foi possível realizar este teste.",
                pCS.existeOpcao(false, opcao));
        utils.capturaTela();
    }

    @Quando("^existir \"([^\"]*)\"$")
    public void existirOpcao(String opcao) {
        assertTrue("Nenhum projeto possui " + opcao + ".\nNão foi possível realizar este teste.",
                pCS.existeOpcao(true, opcao));
        this.nomeItemSelecionado = pCS.getNomeItemSelecionado();
        utils.capturaTela();
    }

    @E("^deverá apresentar o mesmo nome do item selecionado$")
    public void deveraApresentarOMesmoNomeDoItemSelecionado() {
        ModalComponentePage mCP = new ModalComponentePage();
        assertTrue(printResultadoEsperadoObtido(this.nomeItemSelecionado, mCP.getNomeElemento()),
                mCP.isNomeIgual(this.nomeItemSelecionado));
    }

    @Então("^deverá ser apresentado o alerta de \"([^\"]*)\" com a mensagem \"([^\"]*)\"$")
    public void deveraSerApresentadoOAlertaComAMensagem(String opcao, String mensagem) {
        assertEquals(printResultadoEsperadoObtido(mensagem,cP.getTxtMensagemAlerta(opcao)),
                mensagem, cP.getTxtMensagemAlerta(opcao));
        cP.clickBtnFechar("alerta");
    }
}
