package stepsDefinitions.ibmCloud;

import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Então;
import pagesObjects.ibmCloud.ModalAdicionarMembroPage;
import support.Utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AdicionarMembro extends Utils{
    private final ModalAdicionarMembroPage mAMP;

    public AdicionarMembro() {
        this.mAMP = new ModalAdicionarMembroPage();
    }

    @E("^adicionar membro com os dados (.*) '(.*)'$")
    public void adicionarMembroComAChave(String funcao, String chave) {
        try {
            mAMP.acessarAdicionarMembro();
            assertFalse("O botão confirmar está ativo", mAMP.isBtnConfirmarAtivo());
            mAMP.adicionarMembro(funcao, chave);
        } catch (Exception e) {
            logError(e);
            capturaTela();
        }
    }

    @Então("^deverá apresentar a mensagem '(.*)' de erro$")
    public void deveraApresentarAMensagemMensagemDeErro(String mensagem) {
        try {
            assertTrue(printResultadoEsperadoObtido(mensagem, mAMP.getMensagem()),
                    mAMP.getMensagem().contains(mensagem));
            assertFalse("O botão confirmar está ativo", mAMP.isBtnConfirmarAtivo());
        } finally {
            capturaTela();
        }
    }
}
