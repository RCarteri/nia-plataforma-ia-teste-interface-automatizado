package stepsDefinitions.ibmCloud;

import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import map.ComponenteMap;
import pagesObjects.ComponentePage;
import pagesObjects.ModalComponentePage;
import pagesObjects.PanelContentSection;
import support.Utils;

import static org.junit.Assert.*;

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
                new PanelContentSection().existeOpcao(false, opcao));
    }

    @Quando("^existir \"([^\"]*)\"$")
    public void existirOpcao(String opcao) {
        assertTrue("Nenhum projeto possui " + opcao + ".\nNão foi possível realizar este teste.",
                new PanelContentSection().existeOpcao(true, opcao));
        this.nomeItemSelecionado = pCS.getNomeItemSelecionado();
    }

    @E("^deverá apresentar o mesmo nome do item selecionado$")
    public void deveraApresentarOMesmoNomeDoItemSelecionado() {
        ModalComponentePage mCP = new ModalComponentePage();
        assertTrue("Nome esperado: '" + this.nomeItemSelecionado + "'. Nome obtido: '" + mCP.getNomeElemento() + "'.",
                mCP.isNomeIgual(this.nomeItemSelecionado));
    }

    @Então("^deverá apresentar a mensagem de alerta \"([^\"]*)\"$")
    public void deveraApresentarAMensagemDeAlerta(String mensagem) {
        assertEquals(mensagem, new ComponenteMap().getAlertInfo().getText());
        new Utils().capturaTela();
    }

    @Então("^deverá ser apresentada o alerta de sucesso com a mensagem \"([^\"]*)\"$")
    public void deveraSerApresentadaOAlertaDeSucessoComAMensagem(String mensagem) {
        assertEquals(mensagem, cP.getTxtMensagemAlertaSuccess());
        cP.fecharAlertas();
    }
}
