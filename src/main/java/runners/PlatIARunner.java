package runners;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

import ath_allure_arq3.main.AllureARQ3;
import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import utils.Utils;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = { "pretty",
		"io.qameta.allure.cucumber2jvm.AllureCucumber2Jvm" }, features = "classpath:features/", glue = "classpath:stepsDefinitions", snippets = SnippetType.CAMELCASE, tags = {
				"" }, monochrome = true, dryRun = false)
public class PlatIARunner {

	@BeforeClass
	public static void init() {
		AllureARQ3.ConfigInicialAllureARQ3();
	}

	@AfterClass
	public static void finish() {
		new AllureARQ3().enviarRelatorio();
	}
}
