package pagesObjects;

import br.com.bb.ath.ftabb.Pagina;
import br.com.bb.ath.ftabb.anotacoes.MapearElementoWeb;
import br.com.bb.ath.ftabb.elementos.Elemento;
import br.com.bb.ath.ftabb.elementos.ElementoTexto;
import br.com.bb.ath.ftabb.exceptions.ElementoNaoLocalizadoException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static utils.Utils.getDriver;

public class ModalComponentePage extends Pagina {

    private final List<WebElement> listInfoNomeID = getDriver().findElements(By.cssSelector(".ng-trigger h4"));
    private final List<WebElement> listCabecalhoNomeID = getDriver().findElements(By.cssSelector("div[class*='p-ai-center ng-tns-c47'] span"));

    @MapearElementoWeb(css="div .p-dialog-header")
    private static ElementoTexto spanTituloModal;

    @MapearElementoWeb(css="div[class*='ng-tns-c4'] tbody tr")
    private Elemento listaElementosModal;

    public int getCountLinhas(){
        return listaElementosModal.quantosExistem();
    }

    public List<String> getListaInfoNomeID(){
        List<String> listaElementosVazios = new ArrayList<>();
        int index = 0;
        for (WebElement info : listInfoNomeID) {
            if (info.getText().length() == 0) {
                listaElementosVazios.add(listCabecalhoNomeID.get(index).getText());
            }
            index++;
        }
        return listaElementosVazios;
    }

    public String getTituloModal() throws ElementoNaoLocalizadoException {
        return spanTituloModal.recuperarTexto();
    }
}
