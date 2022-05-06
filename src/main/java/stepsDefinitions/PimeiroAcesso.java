package stepsDefinitions;

import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import org.jetbrains.annotations.NotNull;
import pagesObjects.primeiroAcesso.PrimeiroAcessoPage;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static support.Utils.printResultadoEsperadoObtido;

public class PimeiroAcesso {
    private final PrimeiroAcessoPage pAP;

    public PimeiroAcesso() {
        this.pAP = new PrimeiroAcessoPage();
    }

    @Dado("^que apresente a mensagem \"([^\"]*)\"$")
    public void queApresenteAMensagem(@NotNull String mensagem) {
        assertTrue(printResultadoEsperadoObtido(mensagem, pAP.getMensagem()),
                pAP.getMensagem().contains(mensagem));
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
