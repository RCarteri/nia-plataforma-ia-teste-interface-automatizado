package pagesObjects.ibmCloud;

import br.com.bb.ath.ftabb.exceptions.ElementoNaoLocalizadoException;
import cucumber.api.DataTable;
import map.ComponenteMap;
import map.ModalAdicionarMembroMap;
import org.openqa.selenium.WebElement;
import pagesObjects.MensagemErro;
import stepsDefinitions.forms.addMembro.MembroData;
import support.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertFalse;
import static support.Utils.printLog;
import static support.enums.LogTypes.INFO;

public class ModalAdicionarMembroPage {
    private final ModalAdicionarMembroMap mAMM;
    private final List<MembroData> membros = new ArrayList<>();

    public ModalAdicionarMembroPage() {
        this.mAMM = new ModalAdicionarMembroMap();
    }

    public void addMembro(MembroData membro) {
        membros.add(membro);
    }

    public void fillMapMembro(DataTable table) {
        List<Map<String, String>> linhas = table.asMaps(String.class, String.class);
        for (Map<String, String> colunas : linhas) {
            addMembro(new MembroData(colunas.get("Chave"), colunas.get("Função")));
        }
    }

    public void fillForm(MensagemErro mE) {
        Utils utils = new Utils();
        try {
            acessarForm();
            checkBtnInativo();
            for (MembroData membro : membros) {
                preencherCampos(membro);
                mE.isMensagemEsperada(membros, membro);
                checkBtnInativo();
                utils.capturaTela();
            }
        } catch (ElementoNaoLocalizadoException e) {
            utils.logError(e);
        }
    }

    private void checkBtnInativo() {
        assertFalse("O botão confirmar está ativo", isBtnConfirmarAtivo());
    }

    private void preencherCampos(MembroData membro) throws ElementoNaoLocalizadoException {
        mAMM.getInputChave().clear();
        mAMM.getInputChave().sendKeys(membro.getChave());
        if (isDropDowndisplayed()) selecionarFuncao(membro.getFuncao());
        new ComponenteMap().getForm().click();
    }


    private boolean isDropDowndisplayed() {
        try {
            return mAMM.getDropDownFuncao().isDisplayed();
        } catch (NullPointerException e) {
            printLog("Dropdown função não está sendo exibido.", INFO);
            return false;
        }
    }

    private void selecionarFuncao(String funcao) {
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

    public void acessarForm() {
        mAMM.getBtnAdicionarMembro().click();
    }

    public boolean isBtnConfirmarAtivo() {
        return mAMM.getBtnConfirmar().isEnabled();
    }
}
