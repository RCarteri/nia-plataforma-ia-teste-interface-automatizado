package map;

import org.openqa.selenium.WebElement;

import java.util.List;

import static org.junit.Assert.fail;
import static support.GetElements.getElements;

public class ProvedorMap {
    public List<WebElement> getListProvedor() {
        return getElements(".d-flex bb-card-body");
    }

    //Legado
    public List<WebElement> getListBtnExibir() {
        List<WebElement> listBtnExibir = getElements("td button.ng-star-inserted.p-button-secondary");
        if (listBtnExibir.size() == 0)
            fail("A lista não apresenta nenhum item.");
        return listBtnExibir;
    }

    public List<WebElement> getListNomes() {
        return getElements("td.ng-star-inserted:first-child");
    }
}
