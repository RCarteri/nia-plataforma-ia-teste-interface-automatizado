package runners.all;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import runners.ConfRunner;

import static support.APIRest.Host.PROD;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = { "pretty", "io.qameta.allure.cucumber2jvm.AllureCucumber2Jvm" },
		features = {"classpath:features/gestao", "classpath:features/primeiroAcesso"},
		glue = "classpath:stepsDefinitions",
		tags = { "" }
)
public class TestCaseAllPrd extends ConfRunner {
	@BeforeClass
	public static void init(){
		confInit();
	}

	@AfterClass
	public static void finish(){
		confFinish(PROD);
	}
}
