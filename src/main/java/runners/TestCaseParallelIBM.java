package runners;


import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;
import runners.suites.*;
import support.parallel.FTABBCucumberConcurrentRunner;
import support.parallel.FTABBCucumberConcurrentRunner.AppConfig;

@RunWith(FTABBCucumberConcurrentRunner.class)
@SuiteClasses({
        TestSuiteS002CloudObjectStorage.class,
        TestSuiteS003GruposDeAcesso.class,
        TestSuiteS004Modelos.class,
        TestSuiteS005WatsonAssistant.class,
        TestSuiteS006WatsonKnowledgeCatalog.class,
        TestSuiteS007WatsonStudio.class
})
@AppConfig(value = {"", "", "", "", "", "", "", ""}, novasTentativasAposFalharTeste = 1)
public class TestCaseParallelIBM {}