package stepsDefinitions.ibmCloud;

import br.com.bb.ath.ftabb.exceptions.ElementoNaoLocalizadoException;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Então;
import pagesObjects.ModalComponentePage;
import utils.Utils;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ModalComponente {
    private final ModalComponentePage modalComponentePage = new ModalComponentePage();
    private final Utils utils = new Utils();

    @Então("^deverá apresentar o titulo \"([^\"]*)\" no modal$")
    public void deveraApresentarOTitulo(String titulo) throws ElementoNaoLocalizadoException {
        utils.capturaTela();
        assertEquals(titulo, modalComponentePage.getTituloModal().recuperarTexto());
    }

    @E("^deverá apresentar as informações sobre ID e nome$")
    public void deveraApresentarAsInformacoesIDeNome() {
        List<String> listaInfoNomeID = new ModalComponentePage().getListaInfoNomeID();
        assertEquals("Informações faltando no campo: " + listaInfoNomeID.toString(), 0, listaInfoNomeID.size());
    }

    @E("^deverá mostrar a lista com elementos$")
    public void deveraMostrarAListaComElementos() {
        assertTrue(new ModalComponentePage().getCountLinhas() > 1);
    }
}
