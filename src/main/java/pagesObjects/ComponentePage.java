package pagesObjects;

import map.ComponenteMap;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import static br.com.bb.ath.ftabb.utilitarios.Utils.sleep;
import static support.Utils.printLog;
import static support.enums.LogTypes.INFO;

public class ComponentePage extends ComponenteMap {
    public void clickBtnFechar(boolean elemNaoExiste, String local) {
        if (elemNaoExiste) {
            printLog(local.equals("alerta") ? "O modal não foi apresentado." : "O alerta não foi apresentado.", INFO);
        }
        try {
            if ((local.equals("alerta"))) {
                getMensagemAlerta("sucesso");
                sleep(1);
                getListBtnFecharAlerta().forEach(WebElement::click);
            } else {
                getBtnFecharModal().click();
            }
            printLog("O " + local + " presente na página foi fechado.", INFO);
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            printLog("Não existe " + local + " presente na página para ser fechado.", INFO);
        }
    }

    public WebElement getMensagemAlerta(String opcao) {
        switch (opcao) {
            case "sucesso":
                return getAlertSuccess();
            case "informação":
                return getAlertInfo();
            default:
                return null;
        }
    }

    //legado
//    public void acessarComponente(String componente) {
//        cM.getDropDownComponente().click();
//        cM.getListComponente().stream()
//                .filter(webElement -> webElement.getText().intern().equals(componente.intern()))
//                .findFirst()
//                .ifPresent(WebElement::click);
//    }
}
