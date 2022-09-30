package map;

import org.openqa.selenium.WebElement;
import pagesObjects.BasePageObjects;
import support.GetElements;
import support.annotations.FindBy;

import static org.openqa.selenium.By.xpath;

public class SideBarMap extends BasePageObjects {
    @FindBy(cssSelector = ".sidebar.bb-sidebar-menu")
    private WebElement openSideBar;

    public WebElement getOpenSideBar() {
        if (openSideBar == null)
            openSideBar = setElement("openSideBar");
        return openSideBar;
    }

    public WebElement getMenuSideBar(String menu) {
         String seletor = "//span[contains(text(), '" + menu + "')]";
         return new GetElements().findElement(xpath(seletor));
    }
}
