package runners;


import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;
import runners.suites.*;
import support.parallel.FTABBCucumberConcurrentRunner;
import support.parallel.FTABBCucumberConcurrentRunner.AppConfig;

import static runners.ConfRunner.confFinish;
import static runners.ConfRunner.confInit;

@RunWith(FTABBCucumberConcurrentRunner.class)
@SuiteClasses({
        TestSuiteS002CloudObjectStorage.class,
        TestSuiteS003GruposDeAcesso.class,
        TestSuiteS004Modelos.class,
        TestSuiteS005WatsonAssistant.class,
        TestSuiteS006WatsonKnowledgeCatalog.class,
        TestSuiteS007WatsonStudio.class,
        TestSuiteS008ModelosTriton.class,
        TestSuiteS009PrimeiroAcesso.class
})
@AppConfig(value = {"", "", "", "", "", "", "", ""}, novasTentativasAposFalharTeste = 1)
public class TestCaseParalelo {
    @BeforeClass
    public static void init() {
        confInit();
    }

    @AfterClass
    public static void finish() {
        confFinish();
    }
}
