package support;

import br.com.bb.ath.ftabb.FTABBContext;
import br.com.bb.ath.ftabb.exceptions.DataPoolException;
import br.com.bb.ath.ftabb.utilitarios.FTABBUtils;
import io.qameta.allure.Allure;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
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

public class Utils extends FTABBUtils {
    public void esperar(@NotNull TimesAndReasons tar) {
        System.out.println("    Aguardando " + tar.getDelay() + " segundo(s) para " + tar.getReason() + "...");
        sleep(tar.getDelay());
    }

    public static void waitLoadPage(){
        WebDriverWait wait = new WebDriverWait(getDriver(), 10);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".p-progress-spinner-circle")));
    }

    public static @NotNull String printResultadoEsperadoObtido(String esperado, String obtido){
        return "\n Resultado esperado:\n    '" +
                esperado +
                "'.\nResultado obtido:\n    '" +
                obtido;
    }

    public void capturaTela() {
        capturarTela();
        allureCapturarTela();
        System.out.println("        INFO - Tela capturada.");
    }

    public void deletarAllureResults() {
        Path dirPath = Paths.get("./target/allure-results");
        if (dirPath.toFile().exists()) {
            try {
                Files.walk(dirPath)
                        .map(Path::toFile)
                        .sorted(Comparator.comparing(File::isDirectory))
                        .forEach(File::delete);
                System.out.println("Diretório " + dirPath + " deletado com sucesso.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else
            System.out.println("Diretório " + dirPath + " não existe, não precisa ser deletado.");
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

    public static void logError(@NotNull Exception e) {
        System.err.println("\nAlgum erro ocorreu!");
        System.err.println("Mensagem: " + e.getMessage() + "\n");
        e.printStackTrace();
    }

    private void allureCapturarTela() {
        final Object driver = FTABBContext.getContext().getContextBrowserDriver().getDriver();
        final ByteArrayInputStream byteArrInputStream = new ByteArrayInputStream(
                ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
        final String uuid = UUID.randomUUID().toString().substring(0, 8);
        Allure.addAttachment("Print_" + uuid + ".png", byteArrInputStream);
    }
}
