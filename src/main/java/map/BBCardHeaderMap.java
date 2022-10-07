package map;

import org.openqa.selenium.WebElement;
import pagesObjects.BasePageObjects;

import java.util.List;

import static support.GetElements.getElement;
import static support.GetElements.getElements;

public class BBCardHeaderMap extends BasePageObjects {
    public WebElement getInputPesquisa() {
       return getElement(".bb-textfield-group input");
    }

    public WebElement getInputTag() {
       return getElement(".bb-tag-field-input");
    }

    public List<WebElement> getListTag() {
       return getElements(".menu-item a");
    }

    public List<WebElement> getListBtnRemoverTag() {
       return getElements(".tag button");
    }

    public WebElement getBtnFiltrar() {
       return getElement(".secondary");
    }
}