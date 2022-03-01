package pagesObjects;

import br.com.bb.ath.ftabb.Pagina;
import br.com.bb.ath.ftabb.anotacoes.MapearElementoWeb;
import br.com.bb.ath.ftabb.elementos.Elemento;
import br.com.bb.ath.ftabb.elementos.ElementoBotao;
import br.com.bb.ath.ftabb.elementos.ElementoTexto;
import br.com.bb.ath.ftabb.exceptions.ElementoNaoLocalizadoException;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static utils.Utils.getElement;

public class ModalComponentePage extends Pagina {

    private final List<WebElement> listInfoNomeID = getElement(".ng-trigger h4");
    private final List<WebElement> listCabecalhoNomeID = getElement("div[class*='p-ai-center ng-tns-c47'] span");

    @MapearElementoWeb(css=".p-dialog-title")
    private static ElementoTexto spanTituloModal;

    @MapearElementoWeb(css=".p-dialog-content td")
    private Elemento listaElementosModal;

    @MapearElementoWeb(css=".pi-times")
    public ElementoBotao btnFechar;

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
