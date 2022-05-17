package pagesObjects.ibmCloud;

import br.com.bb.ath.ftabb.exceptions.ElementoNaoLocalizadoException;
import map.ModalAdicionarMembroMap;
import org.openqa.selenium.WebElement;
import support.Utils;

import java.util.stream.Collectors;

import static support.Utils.printLog;
import static support.enums.LogTypes.INFO;

public class ModalAdicionarMembroPage {
    private final ModalAdicionarMembroMap mAMM;

    public ModalAdicionarMembroPage() {
        this.mAMM = new ModalAdicionarMembroMap();
    }

    public void adicionarMembro(String funcao, String chave) {
        try {
            mAMM.getInputAdicionarMembro().sendKeys(chave);
            mAMM.getInputAdicionarMembro().click();
            if (isDropDowndisplayed()) selecionarFuncao(funcao);
        } catch (ElementoNaoLocalizadoException e) {
            new Utils().logError(e);
        }
    }

    private boolean isDropDowndisplayed(){
        try{
            return mAMM.getDropDownFuncao().isDisplayed();
        }catch (NullPointerException e){
            printLog("Dropdown função não está sendo exibido.", INFO);
            return false;
        }
    }

    private void selecionarFuncao(String funcao) throws ElementoNaoLocalizadoException {
        mAMM.getDropDownFuncao().click();
        if (funcao.equals("")) {
            mAMM.getDropDownFuncao().click();
            mAMM.getInputAdicionarMembro().click();
            return;
        }
        for (WebElement wE : mAMM.getListSpanFuncao()) {
            if (wE.getText().equals(funcao)) {
                wE.click();
                break;
            }
        }
    }

    public void acessarAdicionarMembro() {
        mAMM.getBtnAdicionarMembro().click();
    }

    public boolean isBtnConfirmarAtivo(){
        return mAMM.getBtnConfirmar().isEnabled();
    }

    public String getMensagem() {
        return mAMM.getListSmallMsg().stream()
                .map(webElement -> webElement.getText().replaceAll("\\n", ""))
                .collect(Collectors.joining());
    }
}
