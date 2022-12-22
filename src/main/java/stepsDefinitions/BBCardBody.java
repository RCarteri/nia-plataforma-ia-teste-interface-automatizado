package stepsDefinitions;

import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Então;
import pagesObjects.sections.BBCardBodySection;
import support.Utils;

import static org.junit.Assert.assertTrue;
import static support.Utils.printLog;
import static support.enums.LogTypes.INFO;

public class BBCardBody extends BBCardBodySection {
    private final Utils utils;

    public BBCardBody() {
        this.utils = new Utils();
    }

    @Então("^deverão ser apresentados os gráficos na tela$")
    public void deveraSerApresentadosOsGraficosNaTela() {
        try {
            assertTrue("Algum dos gráficos não foram apresentados na tela inicial.", new BBCardBodySection().getGraficos().size() >= 2);
        } finally {
            utils.capturaTela();
        }
    }

    @E("^deverá apresentar os cards com as informações$")
    public void deveraApresentarOsCardsComAsInformacoes() {
        assertTrue("Os cards não foram apresentados.", isCardsApresentados());
        assertTrue("Alguma das informações não foram apresentadas no card.", isInfoCardsApresentadas());
        printLog("Os cards foram apresentados com sucesso.", INFO);
    }

    @Então("^deverão ser apresentados os cards de infromações gerenciais com os valores$")
    public void deveraoSerApresentadosOsCardsDeInfo() {
        try {
            assertTrue("Os cards não foram apresentados. Necessário verificar se há projetos com sigla.", getCardsInfo().size() > 0);
            printLog("Os cards foram apresentados com sucesso.", INFO);
            assertTrue("Nenhum card possui valor.", isListCardsInfoContemValores());
        } finally {
            utils.capturaTela();
        }
    }
}
