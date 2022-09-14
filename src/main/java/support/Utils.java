package support;

import br.com.bb.ath.ftabb.utilitarios.FTABBUtils;
import cucumber.api.DataTable;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.yaml.snakeyaml.Yaml;
import support.enums.LogTypes;
import support.enums.SelectorsDelays;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static br.com.bb.ath.ftabb.FTABBContext.getContext;
import static br.com.bb.ath.ftabb.enums.OrigemExecucao.QTESTE;
import static io.qameta.allure.Allure.addAttachment;
import static java.lang.System.*;
import static java.nio.file.Paths.get;
import static java.util.Arrays.asList;
import static java.util.Comparator.comparing;
import static java.util.UUID.randomUUID;
import static java.util.concurrent.ThreadLocalRandom.current;
import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.OutputType.BYTES;
import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOfElementLocated;
import static support.GetElements.getDriver;
import static support.enums.LogTypes.*;
import static support.enums.User.*;

public class Utils extends FTABBUtils {
    public void esperar(SelectorsDelays tar) {
        printLog("Aguardando " + tar.getDelay() + " segundo(s) para " + tar.getSelector() + "...", NULL);
        sleep(esperarQTeste(tar.getDelay()));
    }

    private long esperarQTeste(long segundos) {
        if (isQteste()) {
            segundos /= 2L;
        }
        return segundos;
    }

    public static boolean isQteste() {
        return getContext().getOrigemExecucao().equals(QTESTE);
    }

    public static void waitLoadPage(SelectorsDelays locator) {
        WebDriverWait wait = new WebDriverWait(getDriver(), locator.getDelay());
        try {
            wait.until(invisibilityOfElementLocated(cssSelector(locator.getSelector())));
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
        Path dirPath = get("./target/allure-results");
        if (dirPath.toFile().exists()) {
            try {
                Files.walk(dirPath)
                        .map(Path::toFile)
                        .sorted(comparing(File::isDirectory))
                        .forEach(File::delete);
                printLog("Diretório " + dirPath + " deletado com sucesso.", INFO);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else
            printLog("Diretório " + dirPath + " não existe, não precisa ser deletado.", INFO);
    }

    public void setDatapool() {
        List<Map<String, String>> yamlMap = getYamlMap("login", "chaveF");
        setProperty(USER.toString(), getValueMapYaml(yamlMap, "usuario"));
        setProperty(CHAVE.toString(), getValueMapYaml(yamlMap, "chave"));
        setProperty(SENHA.toString(), getValueMapYaml(yamlMap, "senha"));
    }

    public String getChaveAddMembro(String opcao) {
        List<Map<String, String>> yamlMap = getYamlMap("login", "chaveTeste");
        return getValueMapYaml(yamlMap, opcao);
    }

    public DataTable createDataTable() {
        List<List<String>> dtList = new ArrayList<>();
        List<String> columns = asList("Chave", "Função");
        List<String> dataRow1 = asList(getChaveAddMembro("chave"), "any");
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
        return current().nextInt(size);
    }

    public static boolean checkBtnDisabled(WebElement webElement, String local) {
        return (local.equals("class")) ? webElement.getAttribute(local).contains("disabled") : !webElement.isEnabled();
    }

    public static void assertBtnDisabled(WebElement... webElements) {
        for (WebElement webElement : webElements) {
            assertTrue("O botão confirmar está ativo", checkBtnDisabled(webElement, "btn"));
        }
    }

    public static List<Map<String, String>> getYamlMap(String yamlFile, String chave) {
        String opcao = (yamlFile.equals("api")) ? "payloads.yml" : "login_plataforma.yml";
        Reader reader = null;
        try {
            printLog("get caminho absoluto.", INFO);
            String rootPath = new File("").getAbsolutePath();
            printLog("caminho absoluto: " + rootPath, INFO);
            List<Path> dirs = Files.walk(Paths.get(rootPath), 10)
                    .filter(Files::isDirectory)
                    .collect(Collectors.toList());
            System.out.println("Diretorios");
            System.out.println(dirs);
            reader = new FileReader(rootPath + "/src/main/resources/datapools/" + opcao);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        Map<String, Object> yamlMaps = new Yaml().load(reader);
        return (List<Map<String, String>>) yamlMaps.get(chave);
    }

    public static String getValueMapYaml(List<Map<String, String>> lista, String chave) {
        for (Map<String, String> map : lista) {
            for (Map.Entry<String, String> entry : map.entrySet()) {
                if (chave.equals(entry.getKey())) {
                    return entry.getValue();
                }
            }
        }
        return null;
    }
}
