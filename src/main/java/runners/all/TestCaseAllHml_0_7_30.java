package runners.all;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import runners.ConfRunner;

import static support.APIRest.Host.HML;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = { "pretty", "io.qameta.allure.cucumber2jvm.AllureCucumber2Jvm" },
		features = {"classpath:features/gestao"},
		glue = "classpath:stepsDefinitions",
		tags = { "" }
)
public class TestCaseAllHml_0_7_30 extends ConfRunner {
	@BeforeClass
	public static void init(){
		confInit();
	}

	@AfterClass
	public static void finish(){
		confFinish(HML);
	}
}
