package stepsDefinitions.forms.addMembro;

import cucumber.api.DataTable;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import pagesObjects.MensagemErro;
import pagesObjects.ibmCloud.ModalAdicionarMembroPage;
import support.Utils;

import static org.junit.Assert.assertFalse;

public class AdicionarMembro extends Utils {
    private final ModalAdicionarMembroPage mAMP;

    public AdicionarMembro() {
        this.mAMP = new ModalAdicionarMembroPage();
    }

    @Quando("^adicionar membro com os dados$")
    public void adicionarMembroComOsDados(DataTable table) {
        mAMP.fillMapMembro(table);
    }

    @Então("^deverá apresentar a mensagem de erro$")
    public void deveraApresentarAMensagemDeErro(DataTable table) {
        MensagemErro mE = new MensagemErro();
        mE.fillListMensagem(table);
        try {
            mAMP.acessarForm();
            assertFalse("O botão confirmar está ativo", mAMP.isBtnConfirmarAtivo());
            mAMP.fillForm(mE);
        } catch (Exception e) {
            logError(e);
            capturaTela();
        }
    }
}