package map;

import org.openqa.selenium.WebElement;
import support.annotations.FindBy;

import java.util.List;

import static support.GetElements.getElements;

public class SideBarMap extends BasePageObjects {
    @FindBy(cssSelector = ".sidebar.bb-sidebar-menu")
    private WebElement openSideBar;

    public WebElement getOpenSideBar() {
        if (openSideBar == null)
            openSideBar = setElement("openSideBar");
        return openSideBar;
    }

    public WebElement getIbm() {
        return getListMenus().get(1);
    }

    public WebElement getWatson() {
        return getListItensIbm().get(0);
    }

    protected List<WebElement> getListMenus(){
        return getElements(".sbm-items-level-1-label");
    }

    protected List<WebElement> getListItensIbm(){
        return getElements(".sbml2-items-level-2-item");
    }

    public List<WebElement> getListItens() {
        return getElements(".sbml2-items-level-3 .ng-star-inserted");
    }
}
