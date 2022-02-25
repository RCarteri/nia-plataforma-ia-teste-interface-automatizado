package runners;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = { "pretty", "io.qameta.allure.cucumber2jvm.AllureCucumber2Jvm" },
		features = "classpath:features/",
		glue = "classpath:stepsDefinitions",
		snippets = SnippetType.CAMELCASE,
		tags = { "@PesquisaModalComponente" },
		monochrome = true
)
public class TestCasePesquisaModalComponente {
	@BeforeClass
	public static void init(){
		ConfRunner.init();
	}

	@AfterClass
	public static void finish(){
		ConfRunner.finish();
	}
}
