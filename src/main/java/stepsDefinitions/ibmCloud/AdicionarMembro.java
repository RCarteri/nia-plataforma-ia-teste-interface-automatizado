package stepsDefinitions.ibmCloud;

import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Então;
import pagesObjects.ibmCloud.ModalAdicionarMembroPage;
import support.Utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AdicionarMembro {
    private final ModalAdicionarMembroPage aMP = new ModalAdicionarMembroPage();

    @E("^adicionar membro com os dados (.*) '(.*)'$")
    public void adicionarMembroComAChave(String funcao, String chave) {
        aMP.acessarAdicionarMembro();
        assertFalse("O botão confirmar está ativo", aMP.isBtnConfirmarAtivo());
        aMP.adicionarMembro(funcao, chave);
    }

    @Então("^deverá apresentar a mensagem '(.*)' de erro$")
    public void deveraApresentarAMensagemMensagemDeErro(String mensagem) {
        new Utils().capturaTela();
        assertTrue("A mensagem esperada é diferente da mensagem recebida.\nEsperada: " + mensagem + "\nRecebida: " + aMP.getMensagem(),
                aMP.getMensagem().contains(mensagem));
        assertFalse("O botão confirmar está ativo", aMP.isBtnConfirmarAtivo());
    }
}
