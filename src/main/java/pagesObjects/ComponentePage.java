package pagesObjects;

import br.com.bb.ath.ftabb.Pagina;
import map.ComponenteMap;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

public class ComponentePage extends Pagina {
    private final ComponenteMap cM = new ComponenteMap();

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


    public String getTxtMensagemAlertaSuccess() {
        return new ComponenteMap().getAlertSuccess().getText();
    }

    public void fecharAlertas() {
        try {
            new ComponenteMap().getListBtnFecharAlerta().forEach(WebElement::click);
            System.out.println("Alertas fechados.");
        }catch (NoSuchElementException e){
            System.out.println("Não existem alertas presentes na página para serem fechados.");
        }
    }
}
