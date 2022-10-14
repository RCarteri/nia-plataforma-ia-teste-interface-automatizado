package stepsDefinitions.forms.addMembro;

import cucumber.api.DataTable;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import pagesObjects.MensagemErro;
import pagesObjects.ModalComponentePage;
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
            new ModalComponentePage().acessarForm();
            mAMP.fillFormError(mE);
        } catch (Exception e) {
            logError(e);
        }
    }

//    @Mas("^o membro a ser adicionado não exista na lista$")
//    public void oMembroASerAdicionadoNaoExistaNaLista() {
//        mAMP.verificarInclusao();
//        try {
//            new ModalComponentePage().acessarForm();
//            mAMP.fillForm();
//        } catch (Exception e) {
//            logError(e);
//        }
//    }
}