package pagesObjects;

import map.ComponenteMap;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import static support.Utils.printLog;
import static support.enums.LogTypes.INFO;

public class ComponentePage{
    private final ComponenteMap cM;

    public ComponentePage() {
        this.cM = new ComponenteMap();
    }

    public void acessarComponente(String componente) {
        cM.getDropDownComponente().click();
        cM.getListComponente().stream()
                .filter(webElement -> webElement.getText().intern().equals(componente.intern()))
                .findFirst()
                .ifPresent(WebElement::click);
    }

    public String getTxtTituloComponente() {
        return cM.getTituloComponente().getText();
    }

    public void clickBtnFechar(boolean elemNaoExiste, String local) {
        if (elemNaoExiste) {
            printLog(local.equals("alerta") ? "O modal não foi apresentado." : "O alerta não foi apresentado.", INFO);
        }
        try {
            ComponenteMap cM = new ComponenteMap();
            if ((local.equals("alerta"))) {
                cM.getListBtnFecharAlerta().forEach(WebElement::click);
            } else {
                cM.getBtnFecharModal().click();
            }
            printLog("O " + local + " presente na página foi fechado.", INFO);
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            printLog("Não existe " + local + " presente na página para ser fechado.", INFO);
        }
    }

    public WebElement getMensagemAlerta(String opcao) {
        ComponenteMap cM = new ComponenteMap();
        switch (opcao) {
            case "sucesso":
                return cM.getAlertSuccess();
            case "informação":
                return cM.getAlertInfo();
            default:
                return null;
        }
    }
}
