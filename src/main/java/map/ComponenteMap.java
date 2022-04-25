package map;

import org.openqa.selenium.WebElement;
import support.annotations.FindBy;

import java.util.List;

import static support.GetElements.getElements;
import static support.Utils.waitElement;

public class ComponenteMap extends BasePageObjects {
    @FindBy(cssSelector = "#p-panel-1-titlebar")
    private WebElement tituloComponente;

    @FindBy(cssSelector = ".p-dropdown-trigger span")
    private WebElement dropComponente;

    public WebElement getTituloComponente() {
        if (tituloComponente == null)
            tituloComponente = setElement("tituloComponente");
        return tituloComponente;
    }

    public WebElement getDropComponente() {
        if (dropComponente == null)
            dropComponente = setElement("dropComponente");
        return dropComponente;
    }

    public WebElement getAlert() {
        return waitElement("div .p-toast-detail");
    }

    public List<WebElement> getListComponente() {
        return getElements(".p-dropdown-items-wrapper span");
    }
}
