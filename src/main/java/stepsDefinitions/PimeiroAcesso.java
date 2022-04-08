package stepsDefinitions;

import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import pagesObjects.primeiroAcesso.PrimeiroAcessoPage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PimeiroAcesso {
    private final PrimeiroAcessoPage pAP = new PrimeiroAcessoPage();

    @Dado("^que apresente a mensagem \"([^\"]*)\"$")
    public void queApresenteAMensagem(String mensagem) {
        assertTrue("Mensagem de que o usuário não possui cadastro na IBM Cloud não foi apresentada",
                pAP.getMensagem().toLowerCase().contains(mensagem.toLowerCase()));
    }

    @Quando("^seguir tutorial$")
    public void seguirTutorial() {
        pAP.seguirTutorial();
    }

    @E("^o ultimo botão será de finalizar$")
    public void oUltimoBotaoSeraDeFinalizar() {
        assertEquals("A última página testada não possui o botão de Finalizar.",
                "FINALIZAR", pAP.getUtlimoBotao().getText());
    }

    @Então("^a página deverá atualizar corretamente$")
    public void aPaginaDeveraAtualizarCorretamente() {
        assertEquals("O step item da página " + pAP.getNPaginaFalha() + " não foi alterado.",
                0, pAP.getNPaginaFalha());
    }
}
