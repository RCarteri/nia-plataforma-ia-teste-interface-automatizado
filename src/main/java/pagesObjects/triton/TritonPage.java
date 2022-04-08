package pagesObjects.triton;

import map.TritonMap;
import org.openqa.selenium.WebElement;

public class TritonPage {
    private final TritonMap tM = new TritonMap();

    public String getNomeModelo() {
        return tM.getTdNomeModelo().getText();
    }

    public String getPreMaisDetalhes() {
        tM.getBtnMaisDetalhes().click();
        return tM.getBtnPreMaisDetalhes().getText();
    }

    public boolean estaRetornandoInformacoes() {
        for (WebElement info : tM.getInformacoes()) {
            if (info.getText() == null) return false;
        }
        return tM.getRequest() != null;
    }
}
