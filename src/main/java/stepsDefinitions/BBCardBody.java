package stepsDefinitions;

import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Então;
import pagesObjects.ComponentePage;
import support.Utils;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static support.enums.LogTypes.INFO;

public class BBCardBody extends Utils {
    private final ComponentePage cP;

    public BBCardBody() {
        this.cP = new ComponentePage();
    }

    @Então("^deverão ser apresentados os gráficos na tela$")
    public void deveraSerApresentadosOsGraficosNaTela() {
        try {
            assertTrue("Algum dos gráficos não foram apresentados na tela inicial.", new ComponentePage().getGraficos().size() >= 2);
        } finally {
            capturaTela();
        }
    }

    @E("^deverá apresentar os cards com as informações$")
    public void deveraApresentarOsCardsComAsInformacoes() {
        try {
            assertTrue(cP.getCards().size() > 0);
            assertTrue(cP.isInfoCardsApresentadas());
            printLog("Os cards foram apresentados com sucesso.", INFO);
        } catch (AssertionError e) {
            fail("Os cards não foram apresentados.");
        }
    }

    @Então("^deverão ser apresentados os cards de infromações gerenciais$")
    public void deveraoSerApresentadosOsCardsDeInfo() {
        try {
            assertTrue(cP.getCardsInfo().size() > 0);
            printLog("Os cards foram apresentados com sucesso.", INFO);
        } catch (AssertionError e) {
            fail("Os cards não foram apresentados.");
        }
    }
}
