package pagesObjects.sections;

import map.PaginacaoMap;

import static support.Utils.rolarPaginaAteElemento;

public class PaginacaoSection extends PaginacaoMap{
    public String getQuantResultados(String local) {
        String frase = null;
        switch (local) {
            case "componente":
                frase = getTxtPaginacao().getText();
                break;
            case "modal":
                frase = getTxtPaginacaoModal().getText();
                break;
        }
        assert frase != null;
        return frase.substring(frase.indexOf("de") + 3, frase.length() - 1);
    }

    public void avancarPagina() {
        rolarPaginaAteElemento(getBtnAvancarPagina());
        getBtnAvancarPagina().click();
    }
}
