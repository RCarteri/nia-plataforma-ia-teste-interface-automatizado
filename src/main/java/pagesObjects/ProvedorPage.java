package pagesObjects;

import br.com.bb.ath.ftabb.Pagina;
import br.com.bb.ath.ftabb.anotacoes.MapearElementoWeb;
import br.com.bb.ath.ftabb.elementos.ElementoTexto;
import br.com.bb.ath.ftabb.exceptions.ElementoNaoLocalizadoException;
import org.openqa.selenium.WebElement;

import java.util.List;

import static utils.Utils.getElements;

public class ProvedorPage extends Pagina {
    @MapearElementoWeb(xPath = "//h3[contains(text(), 'IBM Cloud')]")
    private ElementoTexto btnIBMCloud;

    @MapearElementoWeb(xPath = "//h3[contains(text(), 'Triton')]")
    private ElementoTexto btnTriton;

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
}
