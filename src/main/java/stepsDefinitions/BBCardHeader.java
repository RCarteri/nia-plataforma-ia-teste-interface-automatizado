package stepsDefinitions;

import cucumber.api.java.pt.Então;
import pagesObjects.sections.BBCardHeaderSection;
import support.Utils;

import static org.junit.Assert.assertEquals;
import static support.Utils.printLog;
import static support.Utils.printResultadoEsperadoObtido;
import static support.enums.LogTypes.INFO;

public class BBCardHeader extends BBCardHeaderSection {
    private final Utils utils;

    public BBCardHeader() {
        this.utils = new Utils();
    }

    @Então("^deverá apresentar o título \"([^\"]*)\" na página$")
    public void deveApresentarOTituloNaPagina(String titulo) {
        String tituloObtido = getTxtTituloComponente();
        try {
            assertEquals(printResultadoEsperadoObtido(titulo, tituloObtido), titulo, tituloObtido);
            printLog("O título foi apresentado com sucesso: " + titulo, INFO);
        } finally {
            utils.capturaTela();
        }
    }
}
