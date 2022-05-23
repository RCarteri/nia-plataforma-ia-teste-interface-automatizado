package stepsDefinitions;

import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import pagesObjects.primeiroAcesso.PrimeiroAcessoPage;
import support.Utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class PimeiroAcesso extends Utils {
    private final PrimeiroAcessoPage pAP;

    public PimeiroAcesso() {
        this.pAP = new PrimeiroAcessoPage();
    }

    @Dado("^que apresente a mensagem \"([^\"]*)\"$")
    public void queApresenteAMensagem(String mensagem) {
        try {
            assertTrue(printResultadoEsperadoObtido(mensagem, pAP.getMensagem()),
                    pAP.getMensagem().contains(mensagem));
        } catch (Exception e) {
            capturaTela();
        }
    }

    @Quando("^seguir tutorial$")
    public void seguirTutorial() {
        try {
            pAP.seguirTutorial();
        } catch (Exception e) {
            capturaTela();
        }
    }

    @E("^o ultimo botão será de finalizar$")
    public void oUltimoBotaoSeraDeFinalizar() {
        try {
            assertEquals("A última página testada não possui o botão de Finalizar.",
                    "FINALIZAR", pAP.getUtlimoBotao().getText());
        } catch (Exception e) {
            capturaTela();
        }
    }

    @Então("^a página deverá atualizar corretamente$")
    public void aPaginaDeveraAtualizarCorretamente() {
        try {
            assertEquals("O step item da página " + pAP.getNPaginaFalha() + " não foi alterado.",
                    0, pAP.getNPaginaFalha());
        } catch (Exception e) {
            capturaTela();
        }
    }
}
