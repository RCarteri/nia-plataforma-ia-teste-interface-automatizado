package support;

import br.com.bb.ath.ftabb.FTABBContext;
import br.com.bb.ath.ftabb.enums.OrigemExecucao;
import br.com.bb.ath.ftabb.exceptions.DataPoolException;
import br.com.bb.ath.ftabb.utilitarios.FTABBUtils;
import io.qameta.allure.Allure;
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
import java.util.Comparator;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import static org.junit.Assert.assertTrue;
import static support.GetElements.getDriver;
import static support.enums.LogTypes.*;
import static support.enums.User.*;

public class Utils extends FTABBUtils {
    public void esperar(SelectorsDelays tar) {
        printLog("Aguardando " + tar.getDelay() + " segundo(s) para " + tar.getSelector() + "...", NULL);
        sleep(esperarQTeste(tar.getDelay()));
    }

    private long esperarQTeste(long segundos){
        if (FTABBContext.getContext().getOrigemExecucao().equals(OrigemExecucao.QTESTE)) {
            segundos /= 2L;
        }
        return segundos;
    }

    public static void waitLoadPage(SelectorsDelays locator){
        WebDriverWait wait = new WebDriverWait(getDriver(), locator.getDelay());
        try {
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(locator.getSelector())));
        }catch (TimeoutException e){
            printLog("O elemento " + locator.getSelector() + " não apareceu durante os " + locator.getDelay() + " segundos de espera.", ERROR);
        }
    }

    public static String printResultadoEsperadoObtido(String esperado, String obtido){
        return "\nResultado esperado:\n    '" +
                esperado +
                "'.\nResultado obtido:\n    '" +
                obtido + "'.";
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
            System.setProperty(USER.toString(), String.valueOf($("login_plataforma.chaveF.usuario")));
            System.setProperty(CHAVE.toString(), String.valueOf($("login_plataforma.chaveF.chave")));
            System.setProperty(SENHA.toString(), String.valueOf($("login_plataforma.chaveF.senha")));
        } catch (DataPoolException e) {
            logError(e);
        }
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
        final Object driver = FTABBContext.getContext().getContextBrowserDriver().getDriver();
        final ByteArrayInputStream byteArrInputStream = new ByteArrayInputStream(
                ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
        final String uuid = UUID.randomUUID().toString().substring(0, 8);
        Allure.addAttachment("Print_" + uuid + ".png", byteArrInputStream);
    }

    public static void printLog(String msg, LogTypes type) {
        switch (type){
            case INFO:
                System.out.println("\nINFO - " + msg);
                break;
            case ERROR:
                System.err.println("\nERRO - " + msg);
                break;
            case NULL:
                System.err.println("\n" + msg);
                break;
        }
    }

    public static int getRandom(int size){
        return ThreadLocalRandom.current().nextInt(size);
    }

    public static boolean checkBtnDisabled(WebElement webElement, String local) {
        return (local.equals("class")) ? webElement.getAttribute(local).contains("disabled") : !webElement.isEnabled();
    }

    public static void assertBtnDisabled(WebElement wE) {
        assertTrue("O botão confirmar está ativo", checkBtnDisabled(wE, "btn"));
    }
}
