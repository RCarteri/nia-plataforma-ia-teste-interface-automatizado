package pagesObjects;

import br.com.bb.ath.ftabb.exceptions.ElementoNaoLocalizadoException;
import map.ComponenteMap;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ComponentePage {
    private final ComponenteMap cM = new ComponenteMap();
    public void acessarComponente(String componente) throws ElementoNaoLocalizadoException {
        cM.getDropDownComponente().clicar();
        List<WebElement> listComponente = cM.getListComponente();
        for (WebElement webElement : listComponente) {
            if (webElement.getText().intern().equals(componente.intern())) {
                webElement.click();
                break;
            }
        }
    }

    public String getTituloComponente() throws ElementoNaoLocalizadoException {
        return cM.getDivTituloComponente().recuperarTexto();
    }
}
