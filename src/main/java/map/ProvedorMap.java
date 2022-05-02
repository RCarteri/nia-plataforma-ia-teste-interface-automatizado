package map;

import org.openqa.selenium.WebElement;

import java.util.List;

import static support.GetElements.getElements;

public class ProvedorMap extends BasePageObjects {
    public List<WebElement> getListProvedor(){
        return getElements("div.p-card-body");
    }

    public List<WebElement> getListBtnExibir() {
        return getElements("td button.ng-star-inserted.p-button-secondary");
    }

    public List<WebElement> getListNomes() {
        return getElements("td.ng-star-inserted:first-child");
    }
}
