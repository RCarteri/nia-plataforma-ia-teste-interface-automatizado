package support;

import br.com.bb.ath.ftabb.exceptions.DataPoolException;
import cucumber.api.DataTable;
import org.json.JSONArray;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.WebDriverWait;
import support.enums.LogTypes;
import support.enums.SelectorsDelays;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static br.com.bb.ath.ftabb.FTABBContext.getContext;
import static br.com.bb.ath.ftabb.datapool.DataPoolRepo.getInstance;
import static br.com.bb.ath.ftabb.datapool.DataPoolRepo.init;
import static br.com.bb.ath.ftabb.enums.OrigemExecucao.QTESTE;
import static io.qameta.allure.Allure.addAttachment;
import static java.lang.System.*;
import static java.nio.file.Paths.get;
import static java.time.LocalTime.now;
import static java.util.Arrays.asList;
import static java.util.Comparator.comparing;
import static java.util.UUID.randomUUID;
import static java.util.concurrent.ThreadLocalRandom.current;
import static org.junit.Assert.assertTrue;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.OutputType.BYTES;
import static org.openqa.selenium.support.ui.ExpectedConditions.invisibilityOfElementLocated;
import static support.enums.LogTypes.*;
import static support.enums.SelectorsDelays.CIRCLE;
import static support.enums.User.*;

public class Utils extends BaseUtils {
    public static boolean isQteste() {
        return getContext().getOrigemExecucao().equals(QTESTE);
    }

    public static void waitInvisibility(SelectorsDelays locator) {
        WebDriverWait wait = new WebDriverWait(getDriver(), locator.getDelay());
        try {
            LocalTime antes = now();
            printLog("Agruardando o elemento '" + locator.getSelector() + " n??o estar mais na p??gina.", INFO);
            wait.until(invisibilityOfElementLocated(cssSelector(locator.getSelector())));
            printLog(locator.getSelector() + " n??o encontrado na p??gina depois de " + delay(antes) + " segundos.", INFO);
        } catch (TimeoutException e) {
            printLog("O elemento " + locator.getSelector() + " n??o desapareceu durante os " + locator.getDelay() + " segundos de espera.", ERROR);
        }
    }

    public boolean isElementoNotNull(WebElement elemento){
        return elemento != null;
    }

    private static String delay(LocalTime antes) {
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("s.SS");
        return dtf.format(now().minusSeconds(antes.getSecond()));
    }

    public static String printResultadoEsperadoObtido(String esperado, String obtido) {
        return "\nResultado esperado:\n    '" +
                esperado +
                "'.\nResultado obtido:\n    '" +
                obtido + "'.\n";
    }

    public void capturaTela() {
        waitInvisibility(CIRCLE);
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
                printLog("Diret??rio " + dirPath + " deletado com sucesso.", INFO);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else
            printLog("Diret??rio " + dirPath + " n??o existe, n??o precisa ser deletado.", INFO);
    }

    public static String getStringByRegex(String regex, String string) {
        Pattern patt = Pattern.compile(regex);
        Matcher matcher = patt.matcher(string);
        if (matcher.find())
            return matcher.group();
        else
            return matcher.group();
    }

    public void setDatapool(String ambiente) {
        datapoolInit();
        try {
            setProperty(USER.toString(), $("login_plataforma.chaveF.usuario"));
            setProperty(CHAVE.toString(), $("login_plataforma.chaveF.chave"));
            String senha = (ambiente.equals("homologa????o")) ? $("login_plataforma.chaveF.senhaHm") : $("login_plataforma.chaveF.senhaDes");
            setProperty(SENHA.toString(), senha);
            setProperty(COD_CONF.toString(),  $("login_plataforma.chaveF.codConfirmacao"));
            setProperty(USER_NAME.toString(),  $("login_plataforma.chaveF.userName"));
        } catch (DataPoolException e) {
            printLog("As informa????es do usu??rio logado n??o foram retornadas.", ERROR);
            logError(e);
        }
    }

    public String getChaveAddMembro(String opcao) {
        String param = (opcao.equals("chave")) ? "login_plataforma.chaveTeste.chave" : "login_plataforma.chaveTeste.usuario";
        try {
            return $(param);
        } catch (DataPoolException e) {
            printLog("As informa????es do usu??rio logado n??o foram retornadas.", ERROR);
            logError(e);
        }
        return null;
    }

    public DataTable createDataTable() {
        List<List<String>> dtList = new ArrayList<>();
        List<String> columns = asList("Chave", "Fun????o");
        List<String> dataRow1 = asList(getChaveAddMembro("chave"), "any");
        dtList.add(columns);
        dtList.add(dataRow1);
        return DataTable.create(dtList);
    }

    public static List<HashMap<String, String>> jsonArraytoListHashMap(JSONArray jsonArray){
        return jsonArray.toList().stream()
                .map(m -> ((HashMap<String, String>) m))
                .collect(Collectors.toList());
    }

    public static void rolarPaginaAteElemento(WebElement elemento) {
        ((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", elemento);
    }

    public void logError(Throwable e) {
        capturaTela();
        printLog("Um erro de " + e.getClass().getSimpleName() + " ocorreu.", ERROR);
        if ("NoSuchElementException".equals(e.getClass().getSimpleName())) {
            printLog("O elemento " + e.getMessage() + " n??o foi localizado.", ERROR);
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
            assertTrue("O bot??o confirmar est?? ativo", checkBtnDisabled(webElement, "btn"));
        }
    }

    public String getPayload(String endpoint, String componente) {
        String param = "payloads." + endpoint + "." + componente;
        datapoolInit();
        try {
            return $(param);
        } catch (DataPoolException e) {
            e.printStackTrace();
        }
        throw new RuntimeException("Este payload n??o est?? configurado.");
    }

    private void datapoolInit() {
        try {
            getInstance();
        } catch (DataPoolException e) {
//          Rever essa configura????o quando souber como pegar as propriedades definidas no app.config
//          String datapoolPath = getContext().getContextConfig().get("datapools.path");
            String datapoolPath = "datapools";
            try {
                init(datapoolPath);
            } catch (DataPoolException ex) {
                ex.printStackTrace();
            }
        }
    }
}
