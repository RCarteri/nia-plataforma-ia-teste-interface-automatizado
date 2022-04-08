package pagesObjects.ibmCloud.WatsonStudio;

import br.com.bb.ath.ftabb.exceptions.ElementoNaoLocalizadoException;
import map.WatsonStudioMap;
import org.openqa.selenium.WebElement;
import utils.Utils;

public class WatsonStudioPage {
    private final WatsonStudioMap wSM = new WatsonStudioMap();

    public void selecionarSigla(String sigla) {
        try {
            wSM.getDropDownSigla().clicar();
            for (WebElement webElement : wSM.getListaSigla()) {
                if (webElement.getText().intern().equals(sigla.intern())) {
                    webElement.click();
                    break;
                }
            }
        } catch (ElementoNaoLocalizadoException e) {
            Utils.logError(e);
        }
    }

    public void atualizarProjetos() {
        try {
            wSM.getBtnAtualizar().clicar();
        } catch (ElementoNaoLocalizadoException e) {
            Utils.logError(e);
        }
    }
}
