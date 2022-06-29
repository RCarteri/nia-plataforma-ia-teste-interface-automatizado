package stepsDefinitions.triton;

import cucumber.api.DataTable;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Quando;
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

    @E("^apresente as informações do request$")
    public void deveraApresentarAsInformacoesDoRequest() {
        try {
            assertTrue(tP.estaRetornandoInformacoes());
        } finally {
            capturaTela();
        }
    }

    @Quando("^executar um request com os dados$")
    public void executarUmRequestValido(DataTable table) {
        try{
            tP.executarRequest(table);
        }catch (Exception e){
            capturaTela();
        }
    }
}
