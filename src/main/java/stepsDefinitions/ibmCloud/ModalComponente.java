package stepsDefinitions.ibmCloud;

import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Então;
import map.ModalComponenteMap;
import pagesObjects.ModalComponentePage;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ModalComponente {
    private final ModalComponentePage mCP = new ModalComponentePage();

    @Então("^deverá apresentar o titulo \"([^\"]*)\" no modal$")
    public void deveraApresentarOTitulo(String titulo) {
        assertEquals(titulo, new ModalComponenteMap().getTituloModal().getText());
    }

    @E("^deverá apresentar as informações sobre ID e nome$")
    public void deveraApresentarAsInformacoesIDeNome() {
        List<String> listaInfoNomeID = mCP.getListaInfoNomeID();
        assertEquals("Informações faltando no campo: " + listaInfoNomeID.toString(), 0, listaInfoNomeID.size());
    }

    @E("^deverá mostrar a lista com elementos$")
    public void deveraMostrarAListaComElementos() {
        assertTrue(mCP.getCountLinhas() > 1);
    }
}
