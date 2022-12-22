//package pagesObjects.legado.primeiroAcesso;
//
//import br.com.bb.ath.ftabb.exceptions.ElementoNaoLocalizadoException;
//import map.legado.PrimeiroAcessoMap;
//import org.openqa.selenium.WebElement;
//import support.Utils;
//
//import static org.junit.Assert.*;
//import static support.Utils.rolarPaginaAteElemento;
//
//public class PrimeiroAcessoPage {
//    private int posicao;
//    private WebElement btnFinalizar;
//    private int nPaginaFalha;
//    private final PrimeiroAcessoMap pAM;
//    private final Utils utils;
//
//    public PrimeiroAcessoPage() {
//        this.posicao = 0;
//        this.pAM = new PrimeiroAcessoMap();
//        this.utils = new Utils();
//    }
//
//    private void voltarPagina() {
//        rolarPaginaAteElemento(pAM.getBtnVoltar());
//        pAM.getBtnVoltar().click();
//    }
//
//    private void avancarPagina() {
//        rolarPaginaAteElemento(pAM.getBtnAvancar());
//        pAM.getBtnAvancar().click();
//    }
//
//    private void getAcao() {
//        pAM.getBtnAcao().click();
//    }
//
//    public String getMensagem() {
//        try {
//            return pAM.getTxtMensagem().recuperarTexto();
//        } catch (ElementoNaoLocalizadoException e) {
//            utils.logError(e);
//            return null;
//        }
//    }
//
//    public void paginaOK() {
//        boolean stepItemOk;
//        for (WebElement stepItem : pAM.getStepsItens()) {
//            if (pAM.getStepsItens().indexOf(stepItem) != this.posicao)
//                stepItemOk = stepItem.getAttribute("class").contains("p-disabled");
//            else
//                stepItemOk = !stepItem.getAttribute("class").contains("p-disabled");
//            if (!stepItemOk) this.nPaginaFalha = pAM.getStepsItens().indexOf(stepItem) + 1;
//        }
//        this.posicao++;
//    }
//
//    public void seguirTutorial() {
//        getAcao();
//        segundaPagina();
//        for (int i = 0; i < pAM.getStepsItens().size(); ++i) {
//            if (i == pAM.getStepsItens().size() - 1) {
//                utils.capturaTela();
//                this.btnFinalizar = pAM.getBtnAvancar();
//                break;
//            }
//            percorrerPaginas();
//        }
//    }
//
//    private void segundaPagina() {
//        paginaOK();
//        assertTrue("O botão avançar está habilitado.", isBotaoDesabilitado(pAM.getBtnAvancar()));
//        assertTrue("O botão voltar está habilitado.", isBotaoDesabilitado(pAM.getBtnVoltar()));
//        getAcao();
//        isMensagemOK();
//        assertFalse("O botão avançar está desabilitado.", isBotaoDesabilitado(pAM.getBtnAvancar()));
//    }
//
//    private void percorrerPaginas() {
//        avancarPagina();
//        paginaOK();
//        voltarPagina();
//        avancarPagina();
//    }
//
//    public WebElement getUtlimoBotao() {
//        return this.btnFinalizar;
//    }
//
//    public int getNPaginaFalha() {
//        return this.nPaginaFalha;
//    }
//
//    private void isMensagemOK() {
//        ComponenteMap cM = new ComponenteMap();
//        rolarPaginaAteElemento(cM.getAlertSuccess());
//        assertEquals("Mensagem de convite enviado com sucesso não apareceu.",
//                "Convite enviado com sucesso.", cM.getAlertSuccess().getText());
//        new Utils().capturaTela();
//    }
//
//    private boolean isBotaoDesabilitado(WebElement botao) {
//        return botao.getAttribute("disabled") != null;
//    }
//}
