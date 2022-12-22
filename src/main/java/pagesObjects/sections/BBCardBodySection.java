package pagesObjects.sections;

import map.BBCardBodyMap;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import stepsDefinitions.ibmCloud.Pesquisa;

import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.fail;
import static support.Utils.getRandom;
import static support.Utils.printLog;
import static support.enums.LogTypes.ERROR;

public class BBCardBodySection extends BBCardBodyMap {
    public List<WebElement> getGraficos() {
        return getListGraficos();
    }

    public String getDadoPesquisa(String dado) {
        switch (dado) {
            case "inválido":
                return "#inválido";
            case "válido":
                int indexCardRandom = getRandom(getQuantResultados());
                return getListNomeComponente().get(indexCardRandom).getText();
            default:
                throw new IllegalArgumentException("Os únicos dados aceitos são: válido e inválido");
        }
    }

    private int getQuantResultados() {
        if (getListNomeComponente().size() == 0) {
            printLog("Não foi apresentado nenhum card na lista.", ERROR);
            fail("Não foi apresentado nenhum card na lista.");
        }
        return getListNomeComponente().size();
    }

    public boolean resultadosContemString(String palavraPesquisada) {
        return getListNomeComponente().stream()
                .allMatch(nomeComponente -> nomeComponente.getText().equals(palavraPesquisada));
    }

    public boolean siglasOk(String siglaSelecionada) {
        return getListSigla().stream()
                .allMatch(sigla -> sigla.getText().equals(siglaSelecionada));
    }

    public boolean siglasOk() {
        return getQuantResultados() > getListSigla().size();
    }

    private List<WebElement> getCards() {
        return getListCards();
    }

    private int getCardsSize() {
        return getCards().size();
    }

    public boolean isCardsApresentados() {
        if (getCardsSize() == 0) {
            new Pesquisa().naoSelecionarUmaSigla();
            return getCardsSize() > 0;
        } else
            return true;
    }

    public List<WebElement> getCardsInfo() {
        return getListCardsInfo();
    }

    public boolean isListCardsInfoContemValores() {
        return getCardsInfo().stream()
                .anyMatch(webElement ->
                        !webElement.findElement(By.cssSelector("h2"))
                                .getText().equals("0"));
    }

    public boolean isInfoCardsApresentadas() {
        boolean infoCardsApresentadas = true;
        for (WebElement card : getListCards()) {
            List<String> infoList = asList(card.getText().split("\n"));
            if (infoList.size() < 4) {
                printLog("Está faltando informações no card: " + infoList.get(0), ERROR);
                infoCardsApresentadas = false;
            }
        }
        return infoCardsApresentadas;
    }
}