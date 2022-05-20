package runners.testCases;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import runners.ConfRunner;

import static support.APIRest.Dominio.DESENV;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = { "pretty", "io.qameta.allure.cucumber2jvm.AllureCucumber2Jvm" },
		features = "classpath:features/",
		glue = "classpath:stepsDefinitions",
		snippets = SnippetType.CAMELCASE,
		tags = { "@CT004" },
		monochrome = true
)
public class TestCase004Pesquisar extends ConfRunner{
	@BeforeClass
	public static void init(){
		confInit();
	}

	@AfterClass
	public static void finish(){
		confFinish(DESENV);
	}
}
