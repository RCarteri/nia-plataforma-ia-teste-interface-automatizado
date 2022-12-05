package pagesObjects.sections;

import map.BBCardBodyMap;

import static org.junit.Assert.fail;
import static support.Utils.getRandom;
import static support.Utils.printLog;
import static support.enums.LogTypes.ERROR;

public class BBCardBodySection extends BBCardBodyMap {
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
}