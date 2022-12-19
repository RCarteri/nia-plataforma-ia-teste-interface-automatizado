package pagesObjects.legado.ibmCloud.WatsonStudio;

import map.WatsonStudioMap;

import static org.junit.Assert.assertTrue;
import static support.Utils.getRandom;
import static support.enums.User.getUser;

public class WatsonStudioPage {
    private final WatsonStudioMap wSM;

    public WatsonStudioPage() {
        this.wSM = new WatsonStudioMap();
    }

    public String selecionarSigla() {
        wSM.getDropDownSigla().click();
        assertTrue("O usuário " + getUser() + " não pertence a nenhuma sigla.", wSM.getListaSigla().size() > 0);
        wSM.getListaSigla().get(sortearSigla()).click();
        return new WatsonStudioMap().getDropDownSigla().getText();
    }

    public void atualizarProjetos() {
        wSM.getBtnAtualizar().click();
    }

    private int sortearSigla(){
        return getRandom(wSM.getListaSigla().size());
    }
}
