package pagesObjects.sections;

import map.PaginacaoMap;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebElement;

import static support.Utils.printLog;
import static support.Utils.rolarPaginaAteElemento;
import static support.enums.LogTypes.INFO;

public class PaginacaoSection {
    public String getQuantResultados(@NotNull String local) {
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

    protected void avancarPagina(@NotNull WebElement nPagina) {
        printLog("Testando a página " + nPagina.getText() + ".", INFO);
        rolarPaginaAteElemento(nPagina);
        nPagina.click();
    }
}