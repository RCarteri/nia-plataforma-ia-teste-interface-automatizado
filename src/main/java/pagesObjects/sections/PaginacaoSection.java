package pagesObjects.sections;

import map.PaginacaoMap;

import static support.Utils.rolarPaginaAteElemento;

public class PaginacaoSection {
    public String getQuantResultados(String local) {
        String frase = null;
        PaginacaoMap pM = new PaginacaoMap();
        switch (local) {
            case "componente":
                frase = pM.getTxtPaginacao().getText();
                break;
            case "modal":
                frase = pM.getTxtPaginacaoModal().getText();
                break;
        }
        assert frase != null;
        return frase.substring(frase.indexOf("de") + 3, frase.length() - 1);
    }

    public void avancarPagina() {
        PaginacaoMap pM = new PaginacaoMap();
        rolarPaginaAteElemento(pM.getBtnAvancarPagina());
        pM.getBtnAvancarPagina().click();
    }
}
