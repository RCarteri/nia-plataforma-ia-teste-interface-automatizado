package pagesObjects.sections;

import map.BBCardHeaderMap;

public class BBCardHeaderSection extends BBCardHeaderMap {
    public void limparPesquisa() {
        getInputPesquisa().clear();
    }

    public void pesquisar(String palavraPesquisada) {
        getInputPesquisa().sendKeys(palavraPesquisada);
    }
}
