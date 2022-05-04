package pagesObjects;

import br.com.bb.ath.ftabb.exceptions.ElementoNaoLocalizadoException;
import map.ComponenteMap;
import map.MensagemMap;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import static support.Utils.logError;

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
            System.out.println(local.equals("alerta") ? "O modal não foi apresentado." : "O alerta não foi apresentado.");
        }
        try {
            new ComponenteMap().getListBtnFechar().forEach(WebElement::click);
            System.out.println("O " + local + " presente na página foi fechado.");
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            System.out.println("Não existe " + local + " presente na página para ser fechado.");
        }
    }

    public String getTxtMensagemAlerta(@NotNull String opcao) {
        switch (opcao) {
            case "sucesso":
                try {
                    return new MensagemMap().getAlertSuccess().recuperarTexto();
                } catch (ElementoNaoLocalizadoException e) {
                    logError(e);
                }
            case "informação":
                return new ComponenteMap().getAlertInfo().getText();
            default:
                return null;
        }
    }
}
