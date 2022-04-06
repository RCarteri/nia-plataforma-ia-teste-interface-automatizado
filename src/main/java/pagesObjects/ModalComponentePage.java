package pagesObjects;

import br.com.bb.ath.ftabb.Pagina;
import br.com.bb.ath.ftabb.anotacoes.MapearElementoWeb;
import br.com.bb.ath.ftabb.elementos.ElementoBotao;
import br.com.bb.ath.ftabb.elementos.ElementoTexto;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

import static utils.Utils.getElements;

public class ModalComponentePage extends Pagina {

    private final List<WebElement> listInfoNomeID = getElements(".ng-trigger h4");
    private final List<WebElement> listaElementosModal = getElements(".p-dialog-content td");
    private final List<WebElement> listCabecalhoNomeID = getElements("div[class*='p-ai-center ng-tns-c47'] span");

    @MapearElementoWeb(css=".p-dialog-title")
    private ElementoTexto spanTituloModal;

    @MapearElementoWeb(css=".pi-times")
    public ElementoBotao btnFechar;

    public int getCountLinhas(){
        return listaElementosModal.size();
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

    public ElementoTexto getTituloModal(){
        return spanTituloModal;
    }

    protected boolean isModalDisplayed() {
        return new ModalComponentePage().getTituloModal().elementoExiste();
    }

}
