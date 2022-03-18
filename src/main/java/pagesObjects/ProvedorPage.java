package pagesObjects;

import br.com.bb.ath.ftabb.Pagina;
import br.com.bb.ath.ftabb.anotacoes.MapearElementoWeb;
import br.com.bb.ath.ftabb.elementos.Elemento;
import br.com.bb.ath.ftabb.elementos.ElementoTexto;
import br.com.bb.ath.ftabb.exceptions.ElementoNaoLocalizadoException;
import org.openqa.selenium.WebElement;
import utils.Utils;

import java.util.List;

import static utils.Utils.getElements;
import static utils.Utils.rolarPaginaAteElemento;

public class ProvedorPage extends Pagina {
    @MapearElementoWeb(xPath = "//h3[contains(text(), 'IBM Cloud')]")
    private ElementoTexto btnIBMCloud;

    @MapearElementoWeb(xPath = "//h3[contains(text(), 'Triton')]")
    private ElementoTexto btnTriton;

    @MapearElementoWeb(css = "li.ng-star-inserted:nth-child(3)")
    private Elemento segundaOpcao;

    @MapearElementoWeb(css = "li.ng-star-inserted:nth-child(4)")
    private Elemento terceiraOpcao;

    private WebElement getPrimeiraOpcao() {
        return getElements("a.ng-star-inserted").get(0);
    }
    public final List<WebElement> listBtnExibir = getElements("td button.ng-star-inserted.p-button-secondary");

    public void acessarProvedor(String provedor) {
        try {
            switch (provedor) {
                case "IBM Cloud":
                    btnIBMCloud.clicar();
                    break;
                case "Triton":
                    btnTriton.clicar();
                    break;
            }
        } catch (ElementoNaoLocalizadoException e) {
            e.printStackTrace();
        }
    }

    public void clicarBotaoLista(int localizacao) {
        rolarPaginaAteElemento(listBtnExibir.get(localizacao));
        listBtnExibir.get(localizacao).click();
    }

    public boolean clicarBotaoOpcao(String opcao) {
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

}
