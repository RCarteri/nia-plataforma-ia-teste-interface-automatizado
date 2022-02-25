package stepsDefinitions;

import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Então;
import pagesObjects.ModalAdicionarMembroPage;
import utils.Utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class AdidionarMembro {
    ModalAdicionarMembroPage adicionarMembroPage = new ModalAdicionarMembroPage();
    Utils utils = new Utils();

    @E("^adicionar membro com a chave '(.*)'$")
    public void adicionarMembroComAChave(String chave) {
        adicionarMembroPage.acessarAdicionarMembro();
        assertFalse("O botão confirmar está ativo", adicionarMembroPage.isBtnConfirmarAtivo());
        adicionarMembroPage.adicionarMembro(chave);
    }

    @Então("^deverá apresentar a mensagem '(.*)' de erro$")
    public void deveraApresentarAMensagemMensagemDeErro(String mensagem) {
        utils.capturaTela();
        assertTrue("A mensagem esperada é diferente da mensagem recebida.\nEsperada: " + mensagem + "\nRecebida: " + adicionarMembroPage.getMensagem(),
                adicionarMembroPage.getMensagem().contains(mensagem));
        assertFalse("O botão confirmar está ativo", adicionarMembroPage.isBtnConfirmarAtivo());
    }
}
