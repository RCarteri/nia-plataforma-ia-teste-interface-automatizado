package map;

import org.openqa.selenium.WebElement;
import pagesObjects.BasePageObjects;
import support.GetElements;
import support.annotations.FindBy;

import static org.openqa.selenium.By.xpath;
import static support.GetElements.getElement;

public class SideBarMap extends BasePageObjects {
    @FindBy(cssSelector = ".sidebar.bb-sidebar-menu")
    private WebElement openSideBar;

    @FindBy(cssSelector = ".bbds-ui--close")
    private WebElement closeSideBar;

    public WebElement getOpenSideBar() {
        if (openSideBar == null)
            openSideBar = setElement("openSideBar");
        return openSideBar;
    }

    public WebElement getBtnFecharSidebar() {
        if (closeSideBar == null)
            closeSideBar = setElement("closeSideBar");
        return closeSideBar;
    }

    public WebElement getMenuSideBar(String menu) {
         String seletor = "//*[contains(@class, '-')]/span[contains(text(), '" + menu + "')]";
         return new GetElements().findElement(xpath(seletor));
    }

    public void getSideBarOpen() {
        getElement(".bb-sidebar-menu-opened");
    }
}
