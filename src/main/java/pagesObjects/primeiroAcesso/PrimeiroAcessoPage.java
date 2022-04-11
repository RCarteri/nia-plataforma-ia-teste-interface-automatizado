package pagesObjects.primeiroAcesso;

import br.com.bb.ath.ftabb.exceptions.ElementoNaoLocalizadoException;
import map.PrimeiroAcessoMap;
import org.openqa.selenium.WebElement;
import support.Utils;

import static org.junit.Assert.*;
import static support.Utils.*;

public class PrimeiroAcessoPage {
    private final PrimeiroAcessoMap pAM = new PrimeiroAcessoMap();
    private int posicao = 0;
    private WebElement btnFinalizar;
    private int nPaginaFalha;

    private void voltarPagina() {
        rolarPaginaAteElemento(pAM.getBtnVoltar());
        pAM.getBtnVoltar().click();
    }

    private void avancarPagina() {
        rolarPaginaAteElemento(pAM.getBtnAvancar());
        pAM.getBtnAvancar().click();
    }

    private void getAcao() {
        getElement(".p-text-center .p-button-primary").click();
    }

    public String getMensagem() {
        try {
            return pAM.getTxtMensagem().recuperarTexto();
        } catch (ElementoNaoLocalizadoException e) {
            logError(e);
        }
        return null;
    }

    public void paginaOK() {
        boolean stepItemOk;
        for (WebElement stepItem : pAM.getStepsItens()) {
            if (pAM.getStepsItens().indexOf(stepItem) != this.posicao)
                stepItemOk = stepItem.getAttribute("class").contains("p-disabled");
            else
                stepItemOk = !stepItem.getAttribute("class").contains("p-disabled");
            if (!stepItemOk) this.nPaginaFalha = pAM.getStepsItens().indexOf(stepItem) + 1;
        }
        this.posicao++;
    }

    public void seguirTutorial() {
        getAcao();
        segundaPagina();
        for (int i = 0; i < pAM.getStepsItens().size(); ++i) {
            if (i == pAM.getStepsItens().size() - 1) {
                new Utils().capturaTela();
                this.btnFinalizar = pAM.getBtnAvancar();
                break;
            }
            percorrerPaginas();
        }
    }

    private void segundaPagina() {
        paginaOK();
        assertTrue("O botão avançar está habilitado.", isBotaoAvancarDesabilitado());
        getAcao();
        isMensagemOK();
        assertFalse("O botão avançar está desabilitado.", isBotaoAvancarDesabilitado());
    }

    private void percorrerPaginas() {
        avancarPagina();
        paginaOK();
        voltarPagina();
        avancarPagina();
    }

    public WebElement getUtlimoBotao() {
        return this.btnFinalizar;
    }

    public int getNPaginaFalha() {
        return this.nPaginaFalha;
    }

    private void isMensagemOK() {
        rolarPaginaAteElemento(pAM.getMensagemConvite());
        assertEquals("Mensagem de convite enviado com sucesso não apareceu.",
                "Convite enviado com sucesso.", pAM.getMensagemConvite().getText());
        new Utils().capturaTela();
    }

    private boolean isBotaoAvancarDesabilitado() {
        return pAM.getBtnAvancar().getAttribute("disabled") != null;
    }
}
