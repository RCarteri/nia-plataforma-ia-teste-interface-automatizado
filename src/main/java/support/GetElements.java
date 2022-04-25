package support;

import br.com.bb.ath.ftabb.FTABBContext;
import org.openqa.selenium.*;
import support.enums.LogTypes;

import java.util.ArrayList;
import java.util.List;

import static support.enums.TimesAndReasons.LOAD_IFRAMES;

public class GetElements {
    private final Utils utils = new Utils();
    private final List<String> iframesList = new ArrayList<>();
    private short iframesCount = 0;

    public static WebDriver getDriver() {
        return (WebDriver) FTABBContext.getContext().getContextBrowserDriver().getDriver();
    }
    public static List<WebElement> getElements(String seletor) {
        return getDriver().findElements(By.cssSelector(seletor));
    }

    public static WebElement getElement(String seletor) {
        return getDriver().findElement(By.cssSelector(seletor));
    }

    public WebElement findElement(By by) {
        try {
            if (iframesList.size() == 0)
                setIframesList();
            try {
                return getDriver().findElement(by);
            } catch (NoSuchElementException nsee) {
                final String elemInfo = nsee.getMessage().split("Element info: ")[1];
                final StringBuilder logTxt = new StringBuilder("A busca com as informações ")
                        .append(elemInfo)
                        .append(", não encontrou resultado(s) no iframe \"")
                        .append(iframesList.get(iframesCount++));

                log(logTxt.toString(), LogTypes.INFO);
                logTxt.delete(0, logTxt.length());

                logTxt.append("Buscando o elemento com os parâmetros acima no iframe \"")
                        .append(iframesList.get(iframesCount));
                log(logTxt.toString(), LogTypes.INFO);

                getDriver().switchTo().frame(iframesList.get(iframesCount));
                return findElement(by);
            }
        } catch (WebDriverException | IndexOutOfBoundsException wde) {
            log("O mapeamento não iniciou no iframe inicial.", LogTypes.INFO);
            utils.esperar(LOAD_IFRAMES);
            iframesList.clear();
            return findElement(by);
        }
    }

    private void setIframesList() {
        iframesCount = 0;
        getDriver().switchTo().defaultContent();
        getDriver().findElements(By.xpath("//iframe")).forEach(elem -> iframesList.add(elem.getAttribute("id")));

        if (iframesList.size() > 0) {
            String msg = "Voltando para o \"MAIN_IFRAME (" + iframesList.get(0) + ")\"...";

            log(msg, LogTypes.INFO);
        }
    }

    public void log(String msg, LogTypes type) {
        if (type == LogTypes.INFO)
            System.out.println("\n    INFO - " + msg);
        else if (type == LogTypes.ERROR)
            System.err.println("\n    ERRO - " + msg);
    }
}
