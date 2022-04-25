package pagesObjects.ibmCloud.WatsonStudio;

import br.com.bb.ath.ftabb.Pagina;
import map.WatsonStudioMap;
import org.openqa.selenium.WebElement;

public class WatsonStudioPage extends Pagina {
    private final WatsonStudioMap wSM = new WatsonStudioMap();

    public void selecionarSigla(String sigla) {
        wSM.getDropDownSigla().click();
        for (WebElement webElement : wSM.getListaSigla()) {
            if (webElement.getText().intern().equals(sigla.intern())) {
                webElement.click();
                break;
            }
        }
    }

    public void atualizarProjetos() {
        wSM.getBtnAtualizar().click();
    }
}
