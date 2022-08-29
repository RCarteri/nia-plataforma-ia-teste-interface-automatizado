package pagesObjects.ibmCloud;

import cucumber.api.DataTable;
import map.ComponenteMap;
import map.ModalAdicionarMembroMap;
import pagesObjects.MensagemErro;
import pagesObjects.ModalComponentePage;
import pagesObjects.sections.PesquisaSection;
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
        Utils utils = new Utils();
        List<Map<String, String>> linhas = table.asMaps(String.class, String.class);
        String usuario = "";
        for (Map<String, String> colunas : linhas) {
            String chave = colunas.get("Chave");
            if (linhas.get(0).get("Chave").equals("any")) {
                usuario = utils.getChaveAddMembro("usuario");
                chave = utils.getChaveAddMembro("chave");
            }
            addMembro(new MembroData(chave, colunas.get("Função"), usuario));
        }
    }

    public void fillFormError(MensagemErro mE) {
        assertBtnDisabled(mAMM.getBtnConfirmar());
        for (MembroData membro : membros) {
            preencherCampos(membro);
            mE.isMensagemEsperada(membros.indexOf(membro));
            assertBtnDisabled(mAMM.getBtnConfirmar());
            new Utils().capturaTela();
        }
    }

    public void fillForm() {
        assertBtnDisabled(mAMM.getBtnConfirmar());
        for (MembroData membro : membros) {
            preencherCampos(membro);
            assertBtnDisabled(mAMM.getBtnConfirmar());
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
        switch (funcao) {
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

    public boolean isMembroNaLista() {
        PesquisaSection pS = new PesquisaSection();
        String local = "modal";
        pS.pesquisar(membros.get(0).getUsuario(), local);
        return pS.resultadosContemString(membros.get(0).getUsuario(), local);
    }

    public void verificarInclusao() {
        if (isMembroNaLista()) {
            System.out.println("O membro já está na lista, excluíndo o membro para prosseguir o teste.");
            new ModalComponentePage().excluirMembro(-1);
            new Componente().deveraSerApresentadoOAlertaComAMensagem("sucesso", "Membro foi removido com sucesso!");
        }
    }
}
