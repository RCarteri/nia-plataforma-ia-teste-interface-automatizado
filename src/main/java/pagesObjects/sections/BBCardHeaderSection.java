package pagesObjects.sections;

import map.BBCardHeaderMap;
import org.openqa.selenium.WebElement;

import static support.Utils.getRandom;

public class BBCardHeaderSection extends BBCardHeaderMap {
    public void limparPesquisa() {
        getInputPesquisa().clear();
    }

    public void pesquisar(String palavraPesquisada) {
        getInputPesquisa().sendKeys(palavraPesquisada);
    }

    public String selecionarTag() {
        getInputTag().click();
        int indexTag = getRandom(getListTag().size());
        String txtTag = getListTag().get(indexTag).getText();
        getListTag().get(indexTag).click();
        filtrar();
        return txtTag;
    }

    public void filtrar(){
        getBtnFiltrar().click();
    }

    public void removerTags(){
        getListBtnRemoverTag().forEach(WebElement::click);
    }
}
