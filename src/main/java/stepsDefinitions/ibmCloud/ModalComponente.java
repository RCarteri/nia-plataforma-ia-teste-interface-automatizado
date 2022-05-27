package stepsDefinitions.ibmCloud;

import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Então;
import map.ModalComponenteMap;
import pagesObjects.ModalComponentePage;
import support.Utils;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ModalComponente extends Utils{
    private final ModalComponentePage mCP;

    public ModalComponente() {
        this.mCP = new ModalComponentePage();
    }

    @Então("^deverá apresentar o titulo \"([^\"]*)\" no modal$")
    public void deveraApresentarOTitulo(String titulo) {
        try {
            assertEquals(titulo, new ModalComponenteMap().getTituloModal().getText());
        } catch (Exception e) {
            logError(e);
            capturaTela();
        }
    }

    @E("^deverá apresentar as informações sobre ID e nome$")
    public void deveraApresentarAsInformacoesIDeNome() {
        List<String> listaInfoNomeID = mCP.getListaInfoNomeID();
        try {
            assertEquals("Informações faltando no campo: " + listaInfoNomeID.toString(), 0, listaInfoNomeID.size());
        } catch (Exception e) {
            logError(e);
            capturaTela();
        }
    }

    @E("^deverá mostrar a lista com elementos$")
    public void deveraMostrarAListaComElementos() {
        try {
            assertTrue(mCP.getCountLinhas() > 1);
        } catch (Exception e) {
            logError(e);
            capturaTela();
        }
    }
}
