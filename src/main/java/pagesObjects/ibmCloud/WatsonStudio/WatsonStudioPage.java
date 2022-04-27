package pagesObjects.ibmCloud.WatsonStudio;

import br.com.bb.ath.ftabb.Pagina;
import map.WatsonStudioMap;
import org.openqa.selenium.WebElement;

public class WatsonStudioPage extends Pagina {
    private final WatsonStudioMap wSM = new WatsonStudioMap();

    public void selecionarSigla(String sigla) {
        wSM.getDropDownSigla().click();
        wSM.getListaSigla().stream()
                .filter(webElement -> webElement.getText().intern().equals(sigla.intern()))
                .findFirst()
                .ifPresent(WebElement::click);

    }

    public void atualizarProjetos() {
        wSM.getBtnAtualizar().click();
    }
}
