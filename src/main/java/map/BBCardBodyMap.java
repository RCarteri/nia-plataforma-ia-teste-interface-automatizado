package map;

import org.openqa.selenium.WebElement;
import pagesObjects.BasePageObjects;

import java.util.List;

import static support.GetElements.getElements;

public class BBCardBodyMap extends BasePageObjects {
    public List<WebElement> getListNomeComponente() {
        return getElements("bb-card-body h6");
    }

    public List<WebElement> getListSigla() {
        return getElements("bb-card bb-card span");
    }

    public List<WebElement> getListCards() {
        return getElements("bb-card bb-card");
    }

    public List<WebElement> getListCardsInfo() {
        return getElements("nia-management-info-card");
    }

    public List<WebElement> getListGraficos() {
        return getElements("bb-card.card");
    }
}