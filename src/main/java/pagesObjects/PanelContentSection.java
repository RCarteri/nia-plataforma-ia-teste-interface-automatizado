package pagesObjects;

import br.com.bb.ath.ftabb.Pagina;
import br.com.bb.ath.ftabb.exceptions.ElementoNaoLocalizadoException;
import org.openqa.selenium.WebElement;
import utils.Utils;

import java.util.Arrays;
import java.util.List;

import static utils.Razoes.CARR_ELEM;
import static utils.Utils.rolarPaginaAteElemento;

public class PanelContentSection extends Pagina {
    public boolean existeOpcao(boolean esperado, String opcao) {
        PaginacaoSection pS = new PaginacaoSection();
        for (WebElement nPagina : pS.listBtnNPaginacao) {
            ProvedorPage pP = new ProvedorPage();
            for (WebElement nItem : pP.listBtnExibir) {
                avancarItem(nItem, pP.listBtnExibir, opcao);
                if(acessarSubMenu(nItem, opcao)) continue;
                if (esperado) new Utils().esperarQTeste(CARR_ELEM.getDelay(), CARR_ELEM.getRazao());
                if (!esperado && isGetAlertDisplayed()) {
                    System.out.println("Encontrado projeto sem " + opcao + ".");
                    return false;
                } else if (esperado && isModalDisplayed()) {
                    System.out.println("Projeto com " + opcao + " encontrado.");
                    return true;
                }
            }
            avancarPagina(nPagina);
        }
        return false;
    }

    private boolean isModalDisplayed(){
        return new ModalComponentePage().getCountLinhas() >= 1;
    }

    private boolean isGetAlertDisplayed(){
        ModalComponentePage mCP = new ModalComponentePage();
        try{
            new IBMCloudPage().getAlert().isDisplayed();
        } catch (Exception e) {
            if (mCP.btnFechar.elementoExiste()){
                System.out.println("Fechando modal");
                try {
                    mCP.btnFechar.clicar();
                } catch (ElementoNaoLocalizadoException ex) {
                    Utils.logError(ex);
                }
                return false;
            }else e.printStackTrace();
        }
        return true;
    }

    private void avancarPagina(WebElement nPagina) {
        System.out.println("Avançando para a página " + nPagina.getText());
        rolarPaginaAteElemento(nPagina);
        nPagina.click();
    }

    private void avancarItem(WebElement nItem, List<WebElement> listBtnExibir, String opcao) {
        System.out.println("Testando o " + (listBtnExibir.indexOf(nItem) + 1) + "º projeto da lista.");
        rolarPaginaAteElemento(nItem);
        nItem.click();
    }

    private boolean acessarSubMenu(WebElement nItem, String opcao){
        List<String> opcoes = Arrays.asList("Modelos", "Notebooks", "Membros");
        if (opcoes.contains(opcao)){
            boolean btnHabilitado = new ProvedorPage().clicarBotaoOpcao(opcao);
            if (!btnHabilitado) {
                nItem.click();
                return true;
            }
        }
        return false;
    }
}