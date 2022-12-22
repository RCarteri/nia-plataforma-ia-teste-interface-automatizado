package pagesObjects.legado.ibmCloud.WatsonStudio;

import cucumber.api.DataTable;
import map.legado.SolicitarDeployModeloMap;
import org.openqa.selenium.WebElement;
import pagesObjects.legado.MensagemErro;
import stepsDefinitions.legado.forms.solicitacaoDeploy.DadosDeployModelo;
import support.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static support.Utils.*;
import static support.enums.LogTypes.ERROR;
import static support.enums.SelectorsDelays.CIRCLE;

public class SolicitarDeployModeloPage extends SolicitarDeployModeloMap {
    private final List<DadosDeployModelo> dadosDeployModelo = new ArrayList<>();

    public void acessarForm() {
        getListBtnDeploy().get(getRandom(getListBtnDeploy().size())).click();
        waitInvisibility(CIRCLE);
    }

    public void addSolicitacao(DadosDeployModelo dadoDeployModelo) {
        dadosDeployModelo.add(dadoDeployModelo);
    }

    public void fillListSolicitacao(DataTable table) {
        List<Map<String, String>> linhas = table.asMaps(String.class, String.class);
        for (Map<String, String> colunas : linhas) {
            addSolicitacao(new DadosDeployModelo(colunas.get("Nome"), colunas.get("Instância"), colunas.get("Notebook"), colunas.get("Data Asset")));
        }
    }

    public void fillForm(MensagemErro mE) {
        assertBtnDisabled(new SolicitarDeployModeloMap().getBtnSolicitar());
        for (DadosDeployModelo dadoDeployModelo : dadosDeployModelo) {
            preencherCampos(dadoDeployModelo);
            mE.isMensagemEsperada(dadosDeployModelo.indexOf(dadoDeployModelo));
            assertBtnDisabled(new SolicitarDeployModeloMap().getBtnSolicitar());
            new Utils().capturaTela();
        }
    }

    private void preencherCampos(DadosDeployModelo dadoDeployModelo) {
        preencherNome(dadoDeployModelo);
        preencherInstancia(dadoDeployModelo);
        preencherNotebook(dadoDeployModelo);
        preencherDataAsset(dadoDeployModelo);
    }

    private void preencherDataAsset(DadosDeployModelo dadoDeployModelo) {
        getDropDownDataAsset().click();
        getListDataAssetsSelecionados().forEach(WebElement::click);
        if (dadoDeployModelo.getDataAsset().equals("")) {
            getDropDownDataAsset().click();
        } else if (dadoDeployModelo.getDataAsset().equals("any"))
            getListElemDropDown().get(getRandom(getListElemDropDown().size())).click();
        else {
            getListDataAssets().get(getRandom(getListDataAssets().size())).click();
        }
        getInputNome().click();
    }

    private void preencherNotebook(DadosDeployModelo dadoDeployModelo) {
        getDropDownNotebook().click();
        if (dadoDeployModelo.getNotebook().equals("")) {
            getDropDownNotebook().click();
        } else if (dadoDeployModelo.getNotebook().equals("any"))
            getListElemDropDown().get(getRandom(getListElemDropDown().size())).click();
        else
            getListElemDropDown().get(getIndexDadoDropDown(dadoDeployModelo.getNotebook())).click();
        getInputNome().click();
    }

    private void preencherInstancia(DadosDeployModelo dadoDeployModelo) {
        getDropDownInstancia().click();
        if (dadoDeployModelo.getInstancia().equals("")) {
            if (!getListElemDropDown().isEmpty())
                getDropDownInstancia().click();
        } else if (dadoDeployModelo.getInstancia().equals("any"))
            getListElemDropDown().get(getRandom(getListElemDropDown().size())).click();
        else
            getListElemDropDown().get(getIndexDadoDropDown(dadoDeployModelo.getInstancia())).click();
    }

    private void preencherNome(DadosDeployModelo dadoDeployModelo) {
        getInputNome().clear();
        getInputNome().sendKeys(dadoDeployModelo.getNome());
        if (dadoDeployModelo.getNome().equals(""))
            getDropDownInstancia().click();
    }

    private int getIndexDadoDropDown(String dado) {
        for (WebElement wE : getListElemDropDown()) {
            if (wE.getText().equals(dado))
                return getListElemDropDown().indexOf(wE);
        }
        printLog("Item '" + dado + "' não listado no dropdown.", ERROR);
        return -1;
    }
}
