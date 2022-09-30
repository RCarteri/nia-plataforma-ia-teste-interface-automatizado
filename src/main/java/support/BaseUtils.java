package support;

import br.com.bb.ath.ftabb.utilitarios.FTABBUtils;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.NoSuchFrameException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import support.enums.LogTypes;

import java.util.ArrayList;
import java.util.List;

import static br.com.bb.ath.ftabb.FTABBContext.getContext;
import static java.lang.Boolean.parseBoolean;
import static java.lang.System.getProperty;
import static support.enums.LogTypes.*;
import static support.enums.SysProps.LOG;
import static support.enums.SysProps.MAX_SEARCH_ATTEMPTS;

public abstract class BaseUtils extends FTABBUtils {
    protected short searchAttempt, iframesCount;
    protected final List<String> iframesList;
    private final String MAIN_FRAME;
    protected final String SEPARATOR;

    public BaseUtils() {
        searchAttempt = 1;
        iframesList = new ArrayList<>();
        MAIN_FRAME = "MAIN_FRAME";
        SEPARATOR = "=>>";
    }
    protected void switchToNextIframe(String[] iframesIdsList) {
        switchTo(null);

        for (String iframeId : iframesIdsList)
            switchTo(iframeId);
    }

    public void switchTo(String iframeId) {
        if (iframeId != null)
            getDriver().switchTo().frame(iframeId);
        else
            getDriver().switchTo().defaultContent();
    }

    public static WebDriver getDriver() {
        return (WebDriver) getContext().getContextBrowserDriver().getDriver();
    }

    protected void iframesLogs(NoSuchElementException nsee) {
        final String elemInfo = nsee.getMessage().split("Element info: ")[1];
        final StringBuilder logTxt = new StringBuilder("A busca com as informações \"");
        logTxt.append(elemInfo).append("\"");

        if (searchAttempt <= MAX_SEARCH_ATTEMPTS) {
            logTxt.append(", não encontrou resultado(s) no iframe \"");

            if (iframesCount == 0)
                logTxt.append(MAIN_FRAME);
            else if (iframesCount == iframesList.size())
                logTxt.append(iframesList.get(iframesCount - 1)).append(" (último iframe)");
            else
                logTxt.append(iframesList.get(iframesCount - 1));

            logTxt.append("\"");
            log(logTxt.toString(), INFO);

            if (iframesCount < iframesList.size()) {
                logTxt.delete(0, logTxt.length());
                logTxt.append("Buscando o elemento com o(s) parâmetro(s) acima no iframe \"");
                logTxt.append(iframesList.get(iframesCount)).append("\"...");

                log(logTxt.toString(), INFO);
            }

        } else {
            logTxt.append(", foi realizada ").append(searchAttempt).append(" vezes e não encontrou resultado(s) válido(s).");

            log(logTxt.toString(), ERROR);
        }
    }

    public void log(String msg, LogTypes type) {
        final boolean isToLog = parseBoolean(getProperty(LOG));

        if (isToLog) {
            if (msg == null) {
                if (type == START) System.out.println("\n    --- Log START ---");
                else System.out.println("    --- Log END ---\n");

            } else if (type == INFO) System.out.println("\n    INFO - " + msg);
            else if (type == ERROR) System.err.println("\n    ERRO - " + msg);
            else if (type == TABLE) System.out.println("    # - " + msg);
        }
    }

    protected void treatEX(Exception ex) {
        if (searchAttempt < MAX_SEARCH_ATTEMPTS) {
            if (ex.getClass().equals(NoSuchFrameException.class))
                log("O mapeamento não iniciou no iframe inicial.", LogTypes.INFO);
            else if (ex.getClass().equals(WebDriverException.class))
                log("O mapeamento tentou mapear um elemento inválido.", LogTypes.INFO);
            else if (ex.getClass().equals(NoSuchElementException.class))
                log(ex.getMessage(), LogTypes.INFO);

        } else
            log("O limite de buscas foi atingido sem encontrar resultados.", LogTypes.ERROR);

        resetSearchParams(++searchAttempt);
    }

    public void resetSearchParams(int searchAttempt) {
        this.searchAttempt = (short) searchAttempt;
        iframesCount = 0;
        iframesList.clear();

        switchTo(null);
    }
}
