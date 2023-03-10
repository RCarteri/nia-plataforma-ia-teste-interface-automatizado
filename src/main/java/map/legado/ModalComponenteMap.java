package map.legado;

import org.openqa.selenium.WebElement;

import java.util.List;

import static support.GetElements.*;
import static support.enums.SelectorsDelays.MODAL;

public class ModalComponenteMap {
    public WebElement getTituloModal() {
        return getElement(".p-dialog-title");
    }

    public WebElement getModal() {
        return waitElement(MODAL);
    }

    public WebElement getComponenteTransferirS3(){
        return getElement("nia-storage-transfer-page");
    }

    public List<WebElement> getListInfoNomeID() {
        return getElements(".ng-trigger h4");
    }

    public List<WebElement> getListElementosModal() {
        return getElements(".p-dialog-content tbody td:nth-child(2)");
    }

    public List<WebElement> getListCabecalhoNomeID() {
        return getElements("div[class*='p-ai-center ng-tns'] h4");
    }

    public List<WebElement> getListNome() {
        return getElements("nia-membros-table td:nth-child(2)");
    }

    public WebElement getPapel(int i) {
        return getElements("nia-membros-table td:nth-child(3) .p-inputtext").get(i);
    }

    public List<WebElement> getListPapel() {
        return getElements("li.p-ripple");
    }

    public WebElement getBtnSalvar() {
        return getElement(".pi-save");
    }

    public WebElement getBtnConfirmarExclusao() {
        return getElement(".ng-trigger-animation:last-child .p-confirm-popup-accept");
    }

    public WebElement getBtnEditar(int i) {
        return getElements(".pi-pencil").get(i);
    }

    public WebElement getBtnExcluir(int i) {
        return getElements(".pi-trash").get(i);
    }

    public WebElement getBtnAdicionarMembro() {
        return getElement(".p-button-secondary.p-ml-auto");
    }
}