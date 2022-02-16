package runners;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.runner.RunWith;
import stepsDefinitions.Hooks;
import utils.Utils;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = { "pretty", "io.qameta.allure.cucumber2jvm.AllureCucumber2Jvm" },
		features = "classpath:features/",
		glue = "classpath:stepsDefinitions",
		snippets = SnippetType.CAMELCASE,
		tags = { "" },
		monochrome = true,
		dryRun = false
)
public class PlatIARunner {

	@AfterClass
	public static void finish() {
		Hooks hooks = new Hooks();
		hooks.fecharPlataforma();
	}
}
