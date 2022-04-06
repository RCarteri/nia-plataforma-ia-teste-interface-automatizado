package pagesObjects;

import br.com.bb.ath.ftabb.Pagina;
import br.com.bb.ath.ftabb.anotacoes.MapearElementoWeb;
import br.com.bb.ath.ftabb.elementos.Elemento;
import br.com.bb.ath.ftabb.elementos.ElementoTexto;
import br.com.bb.ath.ftabb.exceptions.ElementoNaoLocalizadoException;
import org.openqa.selenium.WebElement;
import utils.Utils;

import java.util.Arrays;
import java.util.List;

import static utils.Razoes.CARR_ELEM;
import static utils.Utils.getElements;
import static utils.Utils.rolarPaginaAteElemento;

public class PanelContentSection extends Pagina {
    @MapearElementoWeb(css = "li.ng-star-inserted:nth-child(3)")
    private Elemento segundaOpcao;

    @MapearElementoWeb(css = "li.ng-star-inserted:nth-child(4)")
    private Elemento terceiraOpcao;

    @MapearElementoWeb(css = "nia-platia-table td")
    private ElementoTexto txtNenhumResultado;

    @MapearElementoWeb(css = "nia-membros-table td")
    private ElementoTexto txtNenhumResultadoModal;

    private final ModalComponentePage mCP = new ModalComponentePage();
    private final PaginacaoSection pS = new PaginacaoSection();

    private WebElement getPrimeiraOpcao() {
        return getElements("a.ng-star-inserted").get(0);
    }

    public boolean existeOpcao(boolean esperado, String opcao) {
        List<String> opcoes = Arrays.asList("Modelos", "Notebooks", "Membros", "Detalhes", "Testar Modelo");
        PaginacaoSection pS = new PaginacaoSection();
        for (WebElement nPagina : pS.listBtnNPaginacao) {
            ProvedorPage pP = new ProvedorPage();
            for (WebElement nItem : pP.listBtnExibir) {
                avancarItem(nItem, pP.listBtnExibir);
                if (opcoes.contains(opcao)) if (acessarSubMenu(nItem, opcao)) continue;
                if (!esperado && isGetAlertDisplayed()) {
                    System.out.println("Encontrado projeto sem " + opcao + ".");
                    return false;
                } else if (esperado && mCP.isModalDisplayed()) {
                    System.out.println("Projeto com " + opcao + " encontrado.");
                    return true;
                }
            }
            pS.avancarPagina(nPagina);
        }
        return false;
    }

    private boolean isGetAlertDisplayed() {
        ModalComponentePage mCP = new ModalComponentePage();
        try {
            new ComponentePage().getAlert().isDisplayed();
        } catch (Exception e) {
            if (mCP.btnFechar.elementoExiste()) {
                System.out.println("Fechando modal");
                try {
                    mCP.btnFechar.clicar();
                } catch (ElementoNaoLocalizadoException ex) {
                    Utils.logError(ex);
                }
                return false;
            } else e.printStackTrace();
        }
        return true;
    }

    private void avancarItem(WebElement nItem, List<WebElement> listBtnExibir) {
        System.out.println("Testando o " + (listBtnExibir.indexOf(nItem) + 1) + "º projeto da lista.");
        rolarPaginaAteElemento(nItem);
        nItem.click();
    }

    private boolean acessarSubMenu(WebElement nItem, String opcao) {
        boolean btnHabilitado = clicarBotaoOpcao(opcao);
        if (!btnHabilitado) {
            nItem.click();
            return true;
        }
        new Utils().esperarQTeste(CARR_ELEM.getDelay(), CARR_ELEM.getRazao());
        return false;
    }

    private boolean clicarBotaoOpcao(String opcao) {
        try {
            switch (opcao) {
                case "Notebooks":
                    terceiraOpcao.clicar();
                    break;
                case "Membros":
                case "Testar Modelo":
                    segundaOpcao.clicar();
                    break;
                case "Modelos":
                case "Detalhes":
                    if (getPrimeiraOpcao().getAttribute("class").contains("disabled")) return false;
                    getPrimeiraOpcao().click();
                    break;
            }
        } catch (ElementoNaoLocalizadoException e) {
            Utils.logError(e);
        }
        return true;
    }

    public String getTxtNenhumResultado(String local) {
        try {
            switch (local) {
                case "componente":
                    return txtNenhumResultado.recuperarTexto();
                case "modal":
                    return txtNenhumResultadoModal.recuperarTexto();
            }
        } catch (ElementoNaoLocalizadoException e) {
            Utils.logError(e);
        }
        return null;
    }

    public boolean resultadosContemString(String palavraPesquisada, String local) {
        boolean resultadosOk = true;
        List<WebElement> listTxt;
        switch (local) {
            case "componente":
                listTxt = getElements("nia-platia-table td:first-child");
                break;
            case "modal":
                listTxt = getElements("nia-membros-table td:nth-child(2)");
                break;
            case "sigla":
                listTxt = getElements(".p-datatable-tbody td:nth-child(2)");
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + local);
        }
        for (WebElement webElement : listTxt) {
            if (!(webElement.getText().toLowerCase().contains(palavraPesquisada.toLowerCase()))) {
                resultadosOk = false;
                break;
            }
        }
        return resultadosOk;
    }
}