package pagesObjects.ibmCloud.WatsonStudio;

import br.com.bb.ath.ftabb.Pagina;
import br.com.bb.ath.ftabb.exceptions.ElementoNaoLocalizadoException;
import map.WatsonStudioMap;
import org.openqa.selenium.WebElement;
import support.Utils;

public class WatsonStudioPage extends Pagina {
    private final WatsonStudioMap wSM = new WatsonStudioMap();

    public void selecionarSigla(String sigla) {
        try {
            wSM.getDropDownSigla().clicar();
            wSM.getListaSigla().stream()
                    .filter(webElement -> webElement.getText().intern().equals(sigla.intern()))
                    .findFirst()
                    .ifPresent(WebElement::click);
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
