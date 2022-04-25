package pagesObjects;

import br.com.bb.ath.ftabb.Pagina;
import map.ComponenteMap;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ComponentePage extends Pagina {
    private final ComponenteMap cM = new ComponenteMap();

    public void acessarComponente(String componente) {
        cM.getDropComponente().click();
        List<WebElement> listComponente = cM.getListComponente();
        for (WebElement webElement : listComponente) {
            if (webElement.getText().intern().equals(componente.intern())) {
                webElement.click();
                break;
            }
        }
    }

    public String getTxtTituloComponente() {
        return cM.getTituloComponente().getText();
    }

}
