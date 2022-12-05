package support;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import support.enums.SelectorsDelays;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import static org.junit.Assert.fail;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.By.xpath;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;
import static support.enums.LogTypes.ERROR;
import static support.enums.LogTypes.INFO;
import static support.enums.SysProps.MAX_SEARCH_ATTEMPTS;

public class GetElements extends Utils{
    private final List<String> iframesList = new ArrayList<>();
    private short iframesCount = 0;
    private short tentativa;
    public static void clicarComJS(WebElement elemento) {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].click();", elemento);
    }

    public static WebElement waitElement(SelectorsDelays locator) {
        WebDriverWait wait = new WebDriverWait(getDriver(), locator.getDelay());
        wait.until(visibilityOfElementLocated(cssSelector(locator.getSelector())));
        return getElement(locator.getSelector());
    }

    public static List<WebElement> getElements(String seletor) {
        return getDriver().findElements(cssSelector(seletor));
    }

    public static WebElement getElement(String seletor) {
        return getDriver().findElement(cssSelector(seletor));
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

    public static WebElement getSeElementoVisivel(String elemento) {
        return (isElementoVisivel(elemento)) ? getElement(elemento) : null;
    }

    private static boolean isElementoVisivel(String elemento) {
        try {
            return getElement(elemento).isDisplayed();
        }catch (Exception e) {
            printLog("O elemento " + elemento + " não está visivel.", INFO);
            return false;
        }
    }

    public List<WebElement> findElements(By by) {
        try {
            if (iframesList.size() == 0)
                setIframesList();

            final List<WebElement> resultList = getDriver().findElements(by);
            final StringBuilder txt = new StringBuilder(String.valueOf(resultList.size()));
            boolean isResultListEmpty = resultList.size() == 0;

            try {
                if (isResultListEmpty) {
                    final String message = "Element info: " + by + "Element info: ";
                    throw new NoSuchElementException(message);
                }

                if (resultList.size() > 1) txt.append(" elementos foram encontrados ");
                else txt.append(" elemento foi encontrado ");

                txt.append("com o localizador \"");
                txt.append(by);
                txt.append("\" no iframe \"");

                if (iframesList.size() > 0) txt.append(iframesList.get(iframesCount));
                else txt.append("PRINCIPAL_MAIN");

                txt.append("\".");
                log(txt.toString(), INFO);

                return resultList;

            } catch (NoSuchElementException nsee) {
                iframesLogs(nsee);
                switchToNextIframe(iframesList.get(iframesCount++).split(SEPARATOR.intern()));

                if (searchAttempt <= MAX_SEARCH_ATTEMPTS)
                    return findElements(by);
                return null;
            }
        } catch (WebDriverException | IndexOutOfBoundsException ex) {
            treatEX(ex);
            return findElements(by);
        }
    }

    protected void setIframesList() {
        iframesCount = 0;
        getDriver().switchTo().defaultContent();
        getDriver().findElements(xpath("//iframe")).forEach(elem -> iframesList.add(elem.getAttribute("id")));
        //seekIframesId(iframesList.size());

        if (iframesList.size() > 0)
            log("Voltando para o \"MAIN_FRAME\"...", INFO);
    }

    private void seekIframesId(int initialValue) {
        final AtomicInteger prevSize = new AtomicInteger();
        final AtomicInteger currSize = new AtomicInteger(initialValue);

        while (prevSize.get() != currSize.get())
            for (int iframeIndex = 0; iframeIndex < iframesList.size(); iframeIndex++) {
                final String iframeId = iframesList.get(iframeIndex);
                prevSize.set(iframesList.size());

                if (iframeId.contains(SEPARATOR))
                    switchToNextIframe(iframeId.split(SEPARATOR.intern()));
                else
                    switchTo(iframeId);

                getDriver().findElements(xpath("//iframe")).forEach(elem -> {
                    String iframeIds = iframeId + SEPARATOR + elem.getAttribute("id");
                    iframesList.add(iframeIds);
                });

                currSize.set(iframesList.size());
                switchTo(null);
            }
    }
}