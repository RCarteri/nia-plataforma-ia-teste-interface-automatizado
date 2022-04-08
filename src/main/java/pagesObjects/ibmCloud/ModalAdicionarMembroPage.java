package pagesObjects.ibmCloud;

import br.com.bb.ath.ftabb.exceptions.ElementoNaoLocalizadoException;
import map.ModalAdicionarMembroMap;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebElement;
import utils.Utils;

public class ModalAdicionarMembroPage {
    private final ModalAdicionarMembroMap mAMM = new ModalAdicionarMembroMap();

    public void adicionarMembro(String funcao, String chave) {
        try {
            mAMM.getInputAdicionarMembro().escrever(chave);
            mAMM.getInputAdicionarMembro().clicar();
            if (mAMM.getDropDownFuncao().elementoExiste()) selecionarFuncao(funcao);
        } catch (ElementoNaoLocalizadoException e) {
            Utils.logError(e);
        }
    }

    private void selecionarFuncao(@NotNull String funcao) throws ElementoNaoLocalizadoException {
        mAMM.getDropDownFuncao().clicar();
        if (funcao.equals("")) {
            mAMM.getDropDownFuncao().clicar();
            mAMM.getInputAdicionarMembro().clicar();
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
        try {
            mAMM.getBtnAdicionarMembro().clicar();
        } catch (ElementoNaoLocalizadoException e) {
            Utils.logError(e);
        }
    }

    public boolean isBtnConfirmarAtivo() {
        try {
            return mAMM.getBtnConfirmar().elementoAtivo();
        } catch (ElementoNaoLocalizadoException e) {
            Utils.logError(e);
        }
        return true;
    }

    public String getMensagem() {
        StringBuilder mensagem = new StringBuilder();
        for (WebElement webElement : mAMM.getListSmallMsg()) {
            mensagem.append(webElement.getText().replaceAll("\\n", ""));
        }
        return mensagem.toString();
    }
}
