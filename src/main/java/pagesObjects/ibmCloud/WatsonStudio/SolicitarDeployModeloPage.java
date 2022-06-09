package pagesObjects.ibmCloud.WatsonStudio;

import cucumber.api.DataTable;
import map.ComponenteMap;
import map.SolicitarDeployModeloMap;
import pagesObjects.MensagemErro;
import stepsDefinitions.forms.solicitacaoDeploy.SolicitacaoData;
import support.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static support.Utils.*;
import static support.enums.SelectorsDelays.CARR_PAG;

public class SolicitarDeployModeloPage extends SolicitarDeployModeloMap {
    private final List<SolicitacaoData> solicitacaoData = new ArrayList<>();

    public void acessarForm() {
        getListBtnDeploy().get(getRandom(getListBtnDeploy().size())).click();
        waitLoadPage(CARR_PAG);
    }

    public void addSolicitacao(SolicitacaoData solicitacao) {
        solicitacaoData.add(solicitacao);
    }

    public void fillListSolicitacao(DataTable table) {
        List<Map<String, String>> linhas = table.asMaps(String.class, String.class);
        for (Map<String, String> colunas : linhas) {
            addSolicitacao(new SolicitacaoData(colunas.get("Nome"), colunas.get("Instância"), colunas.get("Notebook"), colunas.get("Data Asset")));
        }
    }

    public void fillForm(MensagemErro mE) {
        assertBtnDisabled(new SolicitarDeployModeloMap().getBtnSolicitar());
        for (SolicitacaoData solicitacao : solicitacaoData) {
            preencherCampos(solicitacao);
            mE.isMensagemEsperadaErro(solicitacaoData.indexOf(solicitacao));
            assertBtnDisabled(new SolicitarDeployModeloMap().getBtnSolicitar());
            new Utils().capturaTela();
        }
    }

    private void preencherCampos(SolicitacaoData solicitacao) {
        ComponenteMap cM = new ComponenteMap();
        getInputNome().clear();
        getInputNome().sendKeys(solicitacao.getNome());
        cM.getForm().click();
        getDropDownInstancia().click();
        if (solicitacao.getInstancia().equals(""))
            getDropDownInstancia().click();
        else
            getListElemDropDown().get(getRandom(getListElemDropDown().size())).click();
        getDropDownNotebook().click();
        if (solicitacao.getNotebook().equals(""))
            getDropDownNotebook().click();
        else
            getListElemDropDown().get(getRandom(getListElemDropDown().size())).click();
        getDropDownDataAsset().click();
        if (solicitacao.getDataAsset().equals(""))
            getDropDownDataAsset().click();
        else
            getListDataAssets().get(getRandom(getListDataAssets().size())).click();
        getInputNome().click();
    }
}
