package pagesObjects.ibmCloud;

import cucumber.api.DataTable;
import map.ComponenteMap;
import map.ModalAdicionarMembroMap;
import pagesObjects.MensagemErro;
import stepsDefinitions.forms.addMembro.MembroData;
import stepsDefinitions.ibmCloud.Componente;
import support.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static support.Utils.*;
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
        assertBtnDisabled(mAMM.getBtnConfirmar());
        for (MembroData membro : membros) {
            preencherCampos(membro);
            mE.isMensagemEsperada(membros.indexOf(membro));
            assertBtnDisabled(mAMM.getBtnConfirmar());
            new Utils().capturaTela();
        }
    }

    private void preencherCampos(MembroData membro) {
        ComponenteMap cM = new ComponenteMap();
        mAMM.getInputChave().clear();
        mAMM.getInputChave().sendKeys(membro.getChave());
        cM.getForm().click();
        if (isDropDowndisplayed()) selecionarFuncao(membro.getFuncao());
        mAMM.getBtnConfirmar().click();
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
        switch (funcao){
            case "any":
                mAMM.getListSpanFuncao().get(
                        getRandom(mAMM.getListSpanFuncao().size())
                ).click();
            case "":
                new ComponenteMap().getForm().click();
        }
    }

    public void addMembro() {
        fillMapMembro(new Utils().createDataTable());
        preencherCampos(membros.get(0));
        new Componente().deveraSerApresentadoOAlertaComAMensagem("sucesso", "Membro foi incluído com sucesso!");
    }
}
