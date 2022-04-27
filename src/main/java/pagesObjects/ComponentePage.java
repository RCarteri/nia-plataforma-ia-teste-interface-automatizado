package pagesObjects;

import br.com.bb.ath.ftabb.Pagina;
import br.com.bb.ath.ftabb.exceptions.ElementoNaoLocalizadoException;
import map.ComponenteMap;
import org.openqa.selenium.WebElement;

public class ComponentePage extends Pagina {
    private final ComponenteMap cM = new ComponenteMap();

    public void acessarComponente(String componente) throws ElementoNaoLocalizadoException {
        cM.getDropDownComponente().clicar();
        cM.getListComponente().stream()
                .filter(webElement -> webElement.getText().intern().equals(componente.intern()))
                .findFirst()
                .ifPresent(WebElement::click);
    }

    public String getTituloComponente() throws ElementoNaoLocalizadoException {
        return cM.getDivTituloComponente().recuperarTexto();
    }

}
