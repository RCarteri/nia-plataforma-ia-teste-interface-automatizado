package pagesObjects.primeiroAcesso;

import br.com.bb.ath.ftabb.Pagina;
import br.com.bb.ath.ftabb.anotacoes.MapearElementoWeb;
import br.com.bb.ath.ftabb.elementos.ElementoTexto;
import br.com.bb.ath.ftabb.exceptions.ElementoNaoLocalizadoException;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import utils.Utils;

import java.util.List;

import static org.junit.Assert.*;
import static utils.Razoes.CARR_ELEM;
import static utils.Utils.*;

public class PrimeiroAcessoPage<nMaxTentativas> extends Pagina {
    @MapearElementoWeb(css = "span.p-inline-message-text")
    private ElementoTexto txtMensagem;

    private int posicao = 0;
    private WebElement btnFinalizar;
    private int nPaginaFalha;
    private int nTentativas;

    private List<WebElement> getStepsItens() {
        return getElements(".p-steps-item");
    }

    private void voltarPagina() {
        rolarPaginaAteElemento(getBtnVoltar());
        getBtnVoltar().click();
    }

    private void avancarPagina() {
        rolarPaginaAteElemento(getBtnAvancar());
        getBtnAvancar().click();
    }

    private WebElement getBtnVoltar() {
        return getElement(".p-button-secondary");
    }

    private WebElement getMensagemConvite() {
        return getElement(".p-toast-message-success .p-toast-summary");
    }

    private WebElement getBtnAvancar() {
        return getElement(".p-ml-auto.p-button-primary");
    }

    private void getAcao() {
        getElement(".p-text-center .p-button-primary").click();
    }

    public String getMensagem() {
        try {
            return txtMensagem.recuperarTexto();
        } catch (ElementoNaoLocalizadoException e) {
            Utils.logError(e);
        }
        return null;
    }

    public void paginaOK() {
        boolean stepItemOk;
        for (WebElement stepItem : getStepsItens()) {
            if (getStepsItens().indexOf(stepItem) != this.posicao)
                stepItemOk = stepItem.getAttribute("class").contains("p-disabled");
            else
                stepItemOk = !stepItem.getAttribute("class").contains("p-disabled");
            if (!stepItemOk) this.nPaginaFalha = getStepsItens().indexOf(stepItem) + 1;
        }
        this.posicao++;
    }

    public void seguirTutorial() {
        getAcao();
        segundaPagina();
        for (int i = 0; i < getStepsItens().size(); ++i) {
            if (i == getStepsItens().size() - 1) {
                this.btnFinalizar = getBtnAvancar();
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
        Utils utils = new Utils();
        try{
            utils.esperar(2, CARR_ELEM.getRazao());
            assertEquals("Mensagem de convite enviado com sucesso não apareceu.",
                    "Convite enviado com sucesso.", getMensagemConvite().getText());
            rolarPaginaAteElemento(getMensagemConvite());
            utils.capturaTela();
        }catch (NoSuchElementException e){
            int nMaxTentativas = 2;
            if (++nTentativas <= nMaxTentativas) {
                System.out.println("    O elemento não foi apresentado na tela tentando localizar novamente " +
                        nTentativas + "\\" + nMaxTentativas);
                isMensagemOK();
            }
        }
    }

    private boolean isBotaoAvancarDesabilitado() {
        return getBtnAvancar().getAttribute("disabled") != null;
    }
}
