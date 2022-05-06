package support;

import br.com.bb.ath.ftabb.FTABBContext;
import br.com.bb.ath.ftabb.enums.OrigemExecucao;
import br.com.bb.ath.ftabb.exceptions.DataPoolException;
import br.com.bb.ath.ftabb.utilitarios.FTABBUtils;
import io.qameta.allure.Allure;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import support.enums.LogTypes;
import support.enums.TimesAndReasons;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.UUID;

import static support.GetElements.getDriver;
import static support.GetElements.getElement;
import static support.enums.LogTypes.*;

public class Utils extends FTABBUtils {
    public void esperar(@NotNull TimesAndReasons tar) {
        printLog("Aguardando " + tar.getDelay() + " segundo(s) para " + tar.getReason() + "...", NULL);
        sleep(esperarQTeste(tar.getDelay()));
    }

    private long esperarQTeste(long segundos){
        if (FTABBContext.getContext().getOrigemExecucao().equals(OrigemExecucao.QTESTE)) {
            segundos /= 2L;
        }
        return segundos;
    }

    public static void waitLoadPage(){
        WebDriverWait wait = new WebDriverWait(getDriver(), 10);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".p-progress-spinner-circle")));
    }

    public static WebElement waitElement(String seletor){
        WebDriverWait wait = new WebDriverWait(getDriver(), 2);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(seletor)));
        return getElement(seletor);
    }

    public static @NotNull String printResultadoEsperadoObtido(String esperado, String obtido){
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

    public Dictionary<String, String> getDatapool() {
        try {
            final Dictionary<String, String> result = new Hashtable<>();
            result.put("chave", $("login_plataforma.chaveF.chave"));
            result.put("senha", $("login_plataforma.chaveF.senha"));
            return result;
        } catch (DataPoolException e) {
            logError(e);
            return null;
        }
    }

    public static void rolarPaginaAteElemento(WebElement elemento) {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", elemento);
    }

    public void logError(@NotNull Exception e) {
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

    public static void printLog(String msg, @NotNull LogTypes type) {
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
}
