package runners.suites;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import runners.ConfRunner;

import static support.APIRest.Host.DESENV;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = { "pretty", "io.qameta.allure.cucumber2jvm.AllureCucumber2Jvm" },
		features = "classpath:features/",
		glue = "classpath:stepsDefinitions",
		tags = { "@S005" }
)
public class TestSuiteS005WatsonAssistant extends ConfRunner{
	@BeforeClass
	public static void init(){
		confInit();
	}

	@AfterClass
	public static void finish(){
		confFinish(DESENV);
	}
}
