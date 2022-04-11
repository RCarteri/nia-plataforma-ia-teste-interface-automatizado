package stepsDefinitions.ibmCloud;

import br.com.bb.ath.ftabb.exceptions.ElementoNaoLocalizadoException;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import map.ComponenteMap;
import pagesObjects.ComponentePage;
import pagesObjects.PanelContentSection;
import support.Utils;

import static org.junit.Assert.*;

public class Componente {
    private final ComponentePage cP = new ComponentePage();
    private final PanelContentSection pCS = new PanelContentSection();
    private final Utils utils = new Utils();

    @Quando("^selecionar o componente \"([^\"]*)\"$")
    public void selecionarOComponente(String componente) throws ElementoNaoLocalizadoException {
        cP.acessarComponente(componente);
        assertNotNull(cP.getTituloComponente());
    }

    @Então("^deverá apresentar o título \"([^\"]*)\" na página$")
    public void deveApresentarOTituloNaPagina(String titulo) throws ElementoNaoLocalizadoException {
       utils.capturaTela();
       assertEquals(titulo, cP.getTituloComponente());
    }

    @Quando("^não existir \"([^\"]*)\"$")
    public void naoExistirOpcao(String opcao) {
        assertFalse("Todos os projetos possuem " + opcao + "+.\nNão foi possível realizar este teste.",
                pCS.existeOpcao(false, opcao));
    }

    @Quando("^existir \"([^\"]*)\"$")
    public void existirOpcao(String opcao) {
        assertTrue("Nenhum projeto possui  " + opcao + ".\nNão foi possível realizar este teste.",
                pCS.existeOpcao(true, opcao));
    }

    @Então("^deverá apresentar a mensagem de alerta \"([^\"]*)\"$")
    public void deveraApresentarAMensagemDeAlerta(String mensagem) {
        assertEquals(mensagem, new ComponenteMap().getAlert().getText());
        utils.capturaTela();
    }
}
