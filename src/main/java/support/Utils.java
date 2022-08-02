package support;

import br.com.bb.ath.ftabb.enums.OrigemExecucao;
import br.com.bb.ath.ftabb.exceptions.DataPoolException;
import br.com.bb.ath.ftabb.utilitarios.FTABBUtils;
import cucumber.api.DataTable;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import support.enums.LogTypes;
import support.enums.SelectorsDelays;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

import static br.com.bb.ath.ftabb.FTABBContext.getContext;
import static io.qameta.allure.Allure.addAttachment;
import static java.lang.System.*;
import static java.util.UUID.randomUUID;
import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.OutputType.BYTES;
import static support.GetElements.getDriver;
import static support.enums.LogTypes.*;
import static support.enums.User.*;

public class Utils extends FTABBUtils {
    public void esperar(SelectorsDelays tar) {
        printLog("Aguardando " + tar.getDelay() + " segundo(s) para " + tar.getSelector() + "...", NULL);
        sleep(esperarQTeste(tar.getDelay()));
    }

    private long esperarQTeste(long segundos) {
        if (getContext().getOrigemExecucao().equals(OrigemExecucao.QTESTE)) {
            segundos /= 2L;
        }
        return segundos;
    }

    public static void waitLoadPage(SelectorsDelays locator) {
        WebDriverWait wait = new WebDriverWait(getDriver(), locator.getDelay());
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(locator.getSelector())));
        } catch (TimeoutException e) {
            printLog("O elemento " + locator.getSelector() + " não apareceu durante os " + locator.getDelay() + " segundos de espera.", ERROR);
        }
    }

    public static String printResultadoEsperadoObtido(String esperado, String obtido) {
        return "\nResultado esperado:\n    '" +
                esperado +
                "'.\nResultado obtido:\n    '" +
                obtido + "'.\n";
    }

    public void capturaTela() {
        capturarTela();
        allureCapturarTela();
        printLog("Tela capturada.", INFO);
    }

    public void deletarAllureResults() {
        Path dirPath = Paths.get("./target/allure-results");
        if (dirPath.toFile().exists()) {
            try {
                Files.walk(dirPath)
                        .map(Path::toFile)
                        .sorted(Comparator.comparing(File::isDirectory))
                        .forEach(File::delete);
                printLog("Diretório " + dirPath + " deletado com sucesso.", INFO);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else
            printLog("Diretório " + dirPath + " não existe, não precisa ser deletado.", INFO);
    }

    public void setDatapool() {
        try {
            setProperty(USER.toString(), String.valueOf($("login_plataforma.chaveF.usuario")));
            setProperty(CHAVE.toString(), String.valueOf($("login_plataforma.chaveF.chave")));
            setProperty(SENHA.toString(), String.valueOf($("login_plataforma.chaveF.senha")));
        } catch (DataPoolException e) {
            logError(e);
        }
    }

    public String getPayload(String tipoPayload, String endpoint) {
        String param = "payloads." + endpoint + "." + tipoPayload;
        try {
            return String.valueOf($(param));
        } catch (DataPoolException e) {
            logError(e);
            return null;
        }
    }

    public String getChaveAddMembro(String opcao) {
        String param = (opcao.equals("chave")) ? "login_plataforma.chaveTeste.chave" : "login_plataforma.chaveTeste.usuario";
        try {
            return String.valueOf($(param));
        } catch (DataPoolException e) {
            logError(e);
        }
        return null;
    }

    public DataTable createDataTable() {
        List<List<String>> dtList = new ArrayList<>();
        List<String> columns = Arrays.asList("Chave", "Função");
        List<String> dataRow1 = Arrays.asList(getChaveAddMembro("chave"), "any");
        dtList.add(columns);
        dtList.add(dataRow1);
        return DataTable.create(dtList);
    }

    public static void rolarPaginaAteElemento(WebElement elemento) {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", elemento);
    }

    public void logError(Throwable e) {
        capturaTela();
        printLog("Um erro de " + e.getClass().getSimpleName() + " ocorreu.", ERROR);
        if ("NoSuchElementException".equals(e.getClass().getSimpleName())) {
            printLog("O elemento " + e.getMessage() + " não foi localizado.", ERROR);
        }
        printLog("Mensagem: " + e.getMessage(), ERROR);
        e.printStackTrace();
    }

    private void allureCapturarTela() {
        final Object driver = getContext().getContextBrowserDriver().getDriver();
        final ByteArrayInputStream byteArrInputStream = new ByteArrayInputStream(
                ((TakesScreenshot) driver).getScreenshotAs(BYTES));
        final String uuid = randomUUID().toString().substring(0, 8);
        addAttachment("Print_" + uuid + ".png", byteArrInputStream);
    }

    public static void printLog(String msg, LogTypes type) {
        switch (type) {
            case INFO:
                out.println("\nINFO - " + msg + "\n");
                break;
            case ERROR:
                err.println("\nERRO - " + msg + "\n");
                break;
            case NULL:
                err.println("\n" + msg + "\n");
                break;
        }
    }

    public static int getRandom(int size) {
        return ThreadLocalRandom.current().nextInt(size);
    }

    public static boolean checkBtnDisabled(WebElement webElement, String local) {
        return (local.equals("class")) ? webElement.getAttribute(local).contains("disabled") : !webElement.isEnabled();
    }

    public static void assertBtnDisabled(WebElement... webElements) {
        for (WebElement webElement : webElements) {
            assertTrue("O botão confirmar está ativo", checkBtnDisabled(webElement, "btn"));
        }
    }
}
