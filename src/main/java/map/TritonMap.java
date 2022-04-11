package map;

import org.openqa.selenium.WebElement;

import java.util.List;

import static support.Utils.getElement;
import static support.Utils.getElements;

public class TritonMap {
    public WebElement getBtnMaisDetalhes() {
        return getElement(".p-dialog-content button");
    }

    public WebElement getTdNomeModelo(){
        return getElement(".p-dialog-content td:first-child");
    }

    public WebElement getBtnPreMaisDetalhes(){
        return getElement(".p-dialog-content pre");
    }

    public WebElement getRequest(){
        return getElement("#request");
    }

    public List<WebElement> getInformacoes(){
        return getElements(".p-mb-2 span");
    }
}
