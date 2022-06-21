package stepsDefinitions.forms.solicitacaoDeploy;

import cucumber.api.DataTable;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import pagesObjects.MensagemErro;
import pagesObjects.ibmCloud.WatsonStudio.SolicitarDeployModeloPage;
import support.Utils;

public class SolicitarDeployModelo extends Utils {
    private SolicitarDeployModeloPage sDMP;

    public SolicitarDeployModelo() {
        this.sDMP = new SolicitarDeployModeloPage();
    }

    @Quando("^solicitar deploy modelo para Triton com os dados$")
    public void solicitarDeployModeloParaTriton(DataTable table) {
        sDMP.acessarForm();
        this.sDMP = new SolicitarDeployModeloPage();
        sDMP.fillListSolicitacao(table);
    }

    @Então("^deverá apresentar a mensagem de erro de solicitação")
    public void deveraApresentarAMensagemDeErroDeSolicitacao(DataTable table) {
        MensagemErro mE = new MensagemErro();
        mE.fillListMensagem(table);
        try {
            sDMP.fillForm(mE);
        } catch (Exception e) {
            logError(e);
        }
    }
}
