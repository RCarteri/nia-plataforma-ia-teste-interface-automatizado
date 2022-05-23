package stepsDefinitions.ibmCloud.addMembro;

import cucumber.api.DataTable;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import pagesObjects.ibmCloud.ModalAdicionarMembroPage;
import support.Utils;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertFalse;

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

//    @Então("^deverá apresentar a mensagem '(.*)' de erro$")
//    public void deveraApresentarAMensagemMensagemDeErro(String mensagem) {
//        try {
//            assertTrue(printResultadoEsperadoObtido(mensagem, mAMP.getMensagem()),
//                    mAMP.getMensagem().contains(mensagem));
//            assertFalse("O botão confirmar está ativo", mAMP.isBtnConfirmarAtivo());
//        } finally {
//            capturaTela();
//        }
//    }

    @Quando("^adicionar membro com os dados$")
    public void adicionarMembroComOsDadosDaFuncaoEChave(DataTable table) {
        List<Map<String, String>> rows = table.asMaps(String.class, String.class);
        for (Map<String, String> columns : rows) {
            mAMP.addMembro(new Membro(columns.get("chave"), columns.get("função")));
        }
    }

    @Então("^deverá apresentar a mensagem de erro$")
    public void deveraApresentarAMensagemDeErro(DataTable table) {
        List<List<String>> rows = table.asLists(String.class);
        for (List<String> columns : rows) {
            mAMP.addMensagem(new Mensagem(columns.get(0)));
        }
        try {
            mAMP.acessarAdicionarMembro();
            assertFalse("O botão confirmar está ativo", mAMP.isBtnConfirmarAtivo());
            mAMP.adicionarMembro2();
        } catch (Exception e) {
            logError(e);
            capturaTela();
        }
    }
}
