package stepsDefinitions.triton;

import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Então;
import pagesObjects.triton.TritonPage;
import support.Utils;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class Triton {
    private final TritonPage tP;

    public Triton() {
        this.tP = new TritonPage();
    }

    @E("^deverá mostrar a lista com detalhes$")
    public void deveraMostrarAListaComDetalhes() {
        assertNotNull(tP.getNomeModelo());
    }

    @E("^deverá apresentar mais detalhes$")
    public void deveraApresentarMaisDetalhes() {
        assertNotNull(tP.getPreMaisDetalhes());
        new Utils().capturaTela();
    }

    @Então("^deverá apresentar as informações do request$")
    public void deveraApresentarAsInformacoesDoRequest() {
        new Utils().capturaTela();
        assertTrue(tP.estaRetornandoInformacoes());
    }
}
