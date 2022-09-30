package runners.all;


import org.junit.runner.RunWith;
import org.junit.runners.Suite.SuiteClasses;
import runners.plataforma.suites.TestSuiteS002WatsonStudio;
import support.parallel.FTABBCucumberConcurrentRunner;
import support.parallel.FTABBCucumberConcurrentRunner.AppConfig;

@RunWith(FTABBCucumberConcurrentRunner.class)
@SuiteClasses({
        TestSuiteS002WatsonStudio.class
})
@AppConfig(value = {"", "", "", "", "", "", "", ""})
public class TestCaseParallel {}