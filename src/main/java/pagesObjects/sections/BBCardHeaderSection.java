package pagesObjects.sections;

import map.BBCardHeaderMap;
import org.openqa.selenium.WebElement;

import static support.Utils.getRandom;
import static support.Utils.printLog;
import static support.enums.LogTypes.INFO;

public class BBCardHeaderSection extends BBCardHeaderMap {
    public void limparPesquisa() {
        getInputPesquisa().clear();
    }

    public void pesquisar(String palavraPesquisada) {
        getInputPesquisa().sendKeys(palavraPesquisada);
    }

    public String selecionarSigla() {
        getInputSigla().click();
        int indexSigla = getRandom(getListSiglaDisponiveis().size());
        String txtSigla = getListSiglaDisponiveis().get(indexSigla).getText();
        getListSiglaDisponiveis().get(indexSigla).click();
        printLog("Sigla selecionada: " + txtSigla, INFO);
        return txtSigla;
    }

    public void filtrar() {
        getBtnFiltrar().click();
    }

    public void removerSigla() {
        getListBtnRemoverSigla().forEach(WebElement::click);
    }

    public boolean isPesquisaPreenchida() {
        return !getInputPesquisa().getText().equals("");
    }

    public boolean isSiglaSelecionada() {
        return getListSiglasSelecionada().size() > 0;
    }

    public int getTotalResultados() {
        try {
            return Integer.parseInt(getTxtTotalResultados().getText().replaceAll("[\\D]", ""));
        } catch (NumberFormatException e) {
            printLog("Não foi apresentado nenhum resultado com a pesquisa selecionada.", INFO);
            return 0;
        }
    }

    public void limparFiltro() {
        getBtnLimparTudo().click();
        printLog("Filtros foram limpos.", INFO);
    }

}
