package support;

import br.com.bb.ath.ftabb.FTABBContext;
import br.com.bb.ath.ftabb.gaw.Plataforma;
import org.openqa.selenium.*;

import java.util.ArrayList;
import java.util.List;

import static support.Utils.logError;
import static support.Utils.printLog;
import static support.enums.LogTypes.ERROR;
import static support.enums.LogTypes.INFO;
import static support.enums.TimesAndReasons.LOAD_IFRAMES;

public class GetElements {
    private final Utils utils = new Utils();
    private final List<String> iframesList = new ArrayList<>();
    private short iframesCount = 0;
    private short tentativa;

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
            if (tentativa == maxTentativas) {
                printLog("O elemento não foi encontrado no iframe.", ERROR);
                logError(e);
                Plataforma.fecharPlataforma();
                System.exit(0);
            }
            printLog("O mapeamento não iniciou no iframe inicial. Tentativa: " + tentativa + "/" + maxTentativas, INFO);
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

            printLog(msg, INFO);
        }
    }
}