package pagesObjects.triton;

import org.openqa.selenium.WebElement;
import utils.Utils;

import java.util.List;

public class TritonPage {
    private WebElement getBtnMaisDetalhes() {
        return Utils.getElement(".p-dialog-content button");
    }

    private WebElement getTdNomeModelo(){
        return Utils.getElement(".p-dialog-content td:first-child");
    }

    private WebElement getBtnPreMaisDetalhes(){
        return Utils.getElement(".p-dialog-content pre");
    }

    private WebElement getRequest(){
        return Utils.getElement("#request");
    }

    private List<WebElement> getInformacoes(){
        return Utils.getElements(".p-mb-2 span");
    }

    public String getNomeModelo() {
        return getTdNomeModelo().getText();
    }

    public String getPreMaisDetalhes() {
        getBtnMaisDetalhes().click();
        return getBtnPreMaisDetalhes().getText();
    }

    public boolean estaRetornandoInformacoes() {
        for (WebElement info : getInformacoes()) {
            if (info.getText() == null) return false;
        }
        return getRequest() != null;
    }
}
