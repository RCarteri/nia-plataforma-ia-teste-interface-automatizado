package map;

import org.openqa.selenium.WebElement;

import java.util.List;

import static support.GetElements.getElement;
import static support.GetElements.getElements;
import static support.Utils.waitElement;
import static support.enums.SelectorsDelays.CARR_PAG;

public class ModalComponenteMap {
    public WebElement getTituloModal() {
        return getElement(".p-dialog-title");
    }

    public WebElement getModal() {
        return waitElement("div.p-dialog-header", CARR_PAG);
    }

    public List<WebElement> getListInfoNomeID() {
        return getElements(".ng-trigger h4");
    }

    public List<WebElement> getListaElementosModal() {
        return getElements(".p-dialog-content td");
    }

    public List<WebElement> getListCabecalhoNomeID() {
        return getElements("div[class*='p-ai-center ng-tns'] h4");
    }

    public List<WebElement> getListNome() {
        return getElements("nia-membros-table td:nth-child(2)");
    }

    public WebElement getPapel(int i) {
        return getElements("nia-membros-table td:nth-child(3)").get(i);
    }

    public List<WebElement> getListPapel() {
        return getElements("li.p-ripple");
    }

    public WebElement getBtnSalvar() {
        return getElement(".pi-save");
    }

    public WebElement getBtnEditar(int i) {
        return getElements(".pi-pencil").get(i);
    }
}