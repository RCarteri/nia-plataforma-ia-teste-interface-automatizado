package stepsDefinitions.ibmCloud.addMembro;

import cucumber.api.DataTable;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import pagesObjects.ibmCloud.ModalAdicionarMembroPage;
import support.Utils;

import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertFalse;

public class AdicionarMembro extends Utils {
    private final ModalAdicionarMembroPage mAMP;

    public AdicionarMembro() {
        this.mAMP = new ModalAdicionarMembroPage();
    }

    @Quando("^adicionar membro com os dados$")
    public void adicionarMembroComOsDados(DataTable table) {
        List<Map<String, String>> linhas = table.asMaps(String.class, String.class);
        for (Map<String, String> colunas : linhas) {
            mAMP.addMembro(new Membro(colunas.get("chave"), colunas.get("função")));
        }
    }

    @Então("^deverá apresentar a mensagem de erro$")
    public void deveraApresentarAMensagemDeErro(DataTable table) {
        List<String> linhas = table.asList(String.class);
        for (String linha : linhas) {
            mAMP.addMensagem(new Mensagem(linha));
        }
        try {
            mAMP.acessarAdicionarMembro();
            assertFalse("O botão confirmar está ativo", mAMP.isBtnConfirmarAtivo());
            mAMP.adicionarMembro();
        } catch (Exception e) {
            logError(e);
            capturaTela();
        }
    }
}