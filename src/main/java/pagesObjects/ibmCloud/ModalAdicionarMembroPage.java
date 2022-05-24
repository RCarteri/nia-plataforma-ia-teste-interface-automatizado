package pagesObjects.ibmCloud;

import br.com.bb.ath.ftabb.exceptions.ElementoNaoLocalizadoException;
import map.ModalAdicionarMembroMap;
import org.openqa.selenium.WebElement;
import stepsDefinitions.ibmCloud.addMembro.Membro;
import stepsDefinitions.ibmCloud.addMembro.Mensagem;
import support.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static support.Utils.printLog;
import static support.enums.LogTypes.INFO;

public class ModalAdicionarMembroPage {
    private final ModalAdicionarMembroMap mAMM;
    private final List<Membro> membros = new ArrayList<>();
    private final List<Mensagem> mensagens = new ArrayList<>();

    public ModalAdicionarMembroPage() {
        this.mAMM = new ModalAdicionarMembroMap();
    }

    public void addMembro(Membro membro) {
        membros.add(membro);
    }

    public void addMensagem(Mensagem mensagem) {
        mensagens.add(mensagem);
    }

    public void adicionarMembro() {
        Utils utils = new Utils();
        try {
            for (Membro membro : membros) {
                preencherCampos(membro);
                isMensagemEsperada(membro);
                assertFalse("O botão confirmar está ativo", isBtnConfirmarAtivo());
                utils.capturaTela();
                mAMM.getInputChave().clear();
            }
        } catch (ElementoNaoLocalizadoException e) {
            utils.logError(e);
        }
    }

    private void preencherCampos(Membro membro) throws ElementoNaoLocalizadoException {
        mAMM.getInputChave().sendKeys(membro.getChave());
        mAMM.getInputChave().click();
        if (isDropDowndisplayed()) selecionarFuncao(membro.getFuncao());
    }

    private void isMensagemEsperada(Membro membro) {
        mAMM.getForm().click();
        String mensagemEsperada = mensagens.get(membros.indexOf(membro)).getMensagem();
        assertTrue(Utils.printResultadoEsperadoObtido(mensagemEsperada, getMensagem()),
                getMensagem().contains(mensagemEsperada));
    }

    public void adicionarMembro(String funcao, String chave) {
        try {
            mAMM.getInputChave().sendKeys(chave);
            mAMM.getInputChave().click();
            if (isDropDowndisplayed()) selecionarFuncao(funcao);
        } catch (ElementoNaoLocalizadoException e) {
            new Utils().logError(e);
        }
    }

    private boolean isDropDowndisplayed() {
        try {
            return mAMM.getDropDownFuncao().isDisplayed();
        } catch (NullPointerException e) {
            printLog("Dropdown função não está sendo exibido.", INFO);
            return false;
        }
    }

    private void selecionarFuncao(String funcao) throws ElementoNaoLocalizadoException {
        mAMM.getDropDownFuncao().click();
        if (funcao.equals("")) {
            mAMM.getDropDownFuncao().click();
            mAMM.getInputChave().click();
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

    public boolean isBtnConfirmarAtivo() {
        return mAMM.getBtnConfirmar().isEnabled();
    }

    private String getMensagem() {
        return mAMM.getListSmallMsg().stream()
                .map(webElement -> webElement.getText().replaceAll("\\n", ""))
                .collect(Collectors.joining());
    }
}
