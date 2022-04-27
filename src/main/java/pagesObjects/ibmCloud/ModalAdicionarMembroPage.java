package pagesObjects.ibmCloud;

import br.com.bb.ath.ftabb.exceptions.ElementoNaoLocalizadoException;
import map.ModalAdicionarMembroMap;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebElement;
import support.Utils;

import java.util.stream.Collectors;

public class ModalAdicionarMembroPage {
    private final ModalAdicionarMembroMap mAMM = new ModalAdicionarMembroMap();

    public void adicionarMembro(String funcao, String chave) {
        try {
            mAMM.getInputAdicionarMembro().sendKeys(chave);
            mAMM.getInputAdicionarMembro().click();
            if (isDropDowndisplayed()) selecionarFuncao(funcao);
        } catch (ElementoNaoLocalizadoException e) {
            Utils.logError(e);
        }
    }

    private boolean isDropDowndisplayed(){
        try{
            return mAMM.getDropDownFuncao().isDisplayed();
        }catch (NullPointerException e){
            System.out.println("Dropdown função não está sendo exibido.");
            return false;
        }
    }

    private void selecionarFuncao(@NotNull String funcao) throws ElementoNaoLocalizadoException {
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
