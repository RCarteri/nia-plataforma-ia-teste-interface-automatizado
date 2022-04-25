package stepsDefinitions.ibmCloud;

import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import map.ComponenteMap;
import pagesObjects.ComponentePage;
import pagesObjects.PanelContentSection;
import support.Utils;

import static org.junit.Assert.*;

public class Componente {
    private final ComponentePage componentePage = new ComponentePage();
    private final Utils utils = new Utils();

    @Quando("^selecionar o componente \"([^\"]*)\"$")
    public void selecionarOComponente(String componente) {
        componentePage.acessarComponente(componente);
        assertNotNull(componentePage.getTxtTituloComponente());
    }

    @Então("^deverá apresentar o título \"([^\"]*)\" na página$")
    public void deveApresentarOTituloNaPagina(String titulo) {
       utils.capturaTela();
       assertEquals(titulo, componentePage.getTxtTituloComponente());
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
    }

    @Então("^deverá apresentar a mensagem de alerta \"([^\"]*)\"$")
    public void deveraApresentarAMensagemDeAlerta(String mensagem) {
        assertEquals(mensagem, new ComponenteMap().getAlert().getText());
        new Utils().capturaTela();
    }
}
