package stepsDefinitions.forms.addMembro;

import cucumber.api.DataTable;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import pagesObjects.MensagemErro;
import pagesObjects.ibmCloud.ModalAdicionarMembroPage;
import support.Utils;

public class AdicionarMembro extends Utils {
    private final ModalAdicionarMembroPage mAMP;

    public AdicionarMembro() {
        this.mAMP = new ModalAdicionarMembroPage();
    }

    @Quando("^adicionar membro com os dados$")
    public void adicionarMembroComOsDados(DataTable table) {
        mAMP.fillMapMembro(table);
    }

    @Então("^deverá apresentar a mensagem de erro de inclusão$")
    public void deveraApresentarAMensagemDeErro(DataTable table) {
        MensagemErro mE = new MensagemErro();
        mE.fillListMensagem(table);
        try {
            mAMP.acessarForm();
            mAMP.fillForm(mE);
        } catch (Exception e) {
            logError(e);
        }
    }
}