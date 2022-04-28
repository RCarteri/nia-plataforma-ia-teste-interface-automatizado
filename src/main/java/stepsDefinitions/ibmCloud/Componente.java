package stepsDefinitions.ibmCloud;

import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import pagesObjects.ComponentePage;
import pagesObjects.PanelContentSection;
import support.Utils;

import static org.junit.Assert.*;

public class Componente {
    private final ComponentePage cP = new ComponentePage();
    private final PanelContentSection pCS = new PanelContentSection();
    private final Utils utils = new Utils();

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
    }

    @Quando("^existir \"([^\"]*)\"$")
    public void existirOpcao(String opcao) {
        assertTrue("Nenhum projeto possui " + opcao + ".\nNão foi possível realizar este teste.",
               pCS.existeOpcao(true, opcao));
    }

    @Então("^deverá ser apresentado o alerta de \"([^\"]*)\" com a mensagem \"([^\"]*)\"$")
    public void deveraSerApresentadaOAlertaComAMensagem(String opcao, String mensagem) {
        assertEquals("Resultado esperado: '" + mensagem + "'. Resultado obtido: '" + cP.getTxtMensagemAlerta(opcao) + "'.",
                mensagem, cP.getTxtMensagemAlerta(opcao));
        cP.fecharAlertas();
    }
}
