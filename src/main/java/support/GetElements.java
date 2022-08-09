package support;

import br.com.bb.ath.ftabb.FTABBContext;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import support.enums.SelectorsDelays;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.fail;
import static support.Utils.printLog;
import static support.enums.LogTypes.ERROR;
import static support.enums.LogTypes.INFO;

public class GetElements {
    private final List<String> iframesList = new ArrayList<>();
    private short iframesCount = 0;
    private short tentativa;

    public static WebDriver getDriver() {
        return (WebDriver) FTABBContext.getContext().getContextBrowserDriver().getDriver();
    }

    public static WebElement waitElement(SelectorsDelays locator) {
        WebDriverWait wait = new WebDriverWait(getDriver(), locator.getDelay());
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(locator.getSelector())));
        return getElement(locator.getSelector());
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

                printLog(logTxt.toString(), INFO);
                logTxt.delete(0, logTxt.length());

                logTxt.append("Buscando o elemento com os parâmetros acima no iframe \"")
                        .append(iframesList.get(iframesCount));
                printLog(logTxt.toString(), INFO);
                getDriver().switchTo().frame(iframesList.get(iframesCount));
                return findElement(by);
            }
        } catch (WebDriverException | IndexOutOfBoundsException e) {
            short maxTentativas = 20;
            if (++tentativa == maxTentativas) {
                printLog("O elemento " + by + " não foi encontrado no iframe. Tentativa: " + tentativa + "/" + maxTentativas, ERROR);
                fail("O elemento " + by + " não foi encontrado no iframe. Tentativa: " + tentativa + "/" + maxTentativas);
            }
            printLog("O mapeamento não iniciou no iframe inicial. Tentativa: " + tentativa + "/" + maxTentativas, INFO);
            iframesList.clear();
            return findElement(by);
        }
    }

    private void setIframesList() {
        iframesCount = 0;
        getDriver().switchTo().defaultContent();
        getDriver().findElements(By.xpath("//iframe")).forEach(elem -> iframesList.add(elem.getAttribute("id")));
        //getDriver().switchTo().frame(iframesList.get(1));
        if (iframesList.size() > 0)
            printLog("Voltando para o 'iframe (" + iframesList.get(0) + ")'...", INFO);
    }
}