package runners;


import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;
import runners.suites.TestSuiteS001AcessarPlataforma;
import runners.testCases.TestCase002ValidarComponente;
import runners.testCases.TestCase003ExibirComponente;
import support.parallel.FTABBCucumberConcurrentRunner;
import support.parallel.FTABBCucumberConcurrentRunner.AppConfig;

@RunWith(FTABBCucumberConcurrentRunner.class)
@SuiteClasses({
        TestSuiteS001AcessarPlataforma.class,
        TestCase002ValidarComponente.class,
        TestCase003ExibirComponente.class
//        TestSuiteS005WatsonAssistant.class,
//        TestSuiteS006WatsonKnowledgeCatalog.class,
//        TestSuiteS007WatsonStudio.class,
//        TestSuiteS008ModelosTriton.class,
//        TestSuiteS009PrimeiroAcesso.class
})
@AppConfig(value={"", ""})
public class TestCaseParalelo {
}
