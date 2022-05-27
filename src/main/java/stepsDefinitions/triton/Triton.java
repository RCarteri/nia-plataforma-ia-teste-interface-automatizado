package stepsDefinitions.triton;

import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Então;
import pagesObjects.triton.TritonPage;
import support.Utils;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class Triton extends Utils {
    private final TritonPage tP;

    public Triton() {
        this.tP = new TritonPage();
    }

    @E("^deverá mostrar a lista com detalhes$")
    public void deveraMostrarAListaComDetalhes() {
        try {
            assertNotNull(tP.getNomeModelo());
        } catch (Exception e) {
            logError(e);
            capturaTela();
        }
    }

    @E("^deverá apresentar mais detalhes$")
    public void deveraApresentarMaisDetalhes() {
        try {
            assertNotNull(tP.getPreMaisDetalhes());
        } finally {
            capturaTela();
        }
    }

    @Então("^deverá apresentar as informações do request$")
    public void deveraApresentarAsInformacoesDoRequest() {
        try {
            assertTrue(tP.estaRetornandoInformacoes());
        } finally {
            capturaTela();
        }
    }
}
