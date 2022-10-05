package map;

import org.openqa.selenium.WebElement;
import pagesObjects.BasePageObjects;

import static support.GetElements.getElement;

public class BBCardHeaderMap extends BasePageObjects {
    public WebElement getInputPesquisa() {
       return getElement(".bb-textfield-group input");
    }
}