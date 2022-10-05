package pagesObjects.sections;

import map.BBCardBodyMap;

import static support.Utils.getRandom;

public class BBCardBodySection extends BBCardBodyMap {
    public String getDadoPesquisa(String dado) {
        switch (dado) {
            case "inválido":
                return "#inválido";
            case "válido":
                int indexCardRandom = getRandom(getListNomeComponente().size());
                return getListNomeComponente().get(indexCardRandom).getText();
            default:
                throw new IllegalArgumentException("Os únicos dados aceitos são: válido e inválido");
        }
    }

    public boolean resultadosContemString(String palavraPesquisada) {
        return getListNomeComponente().stream()
                .allMatch(nomeComponente -> nomeComponente.getText().equals(palavraPesquisada));
    }

    public int getQuantResultados() {
        return getListNomeComponente().size();
    }
}