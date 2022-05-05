package runners;

import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
		plugin = { "pretty", "support.parallel.AllureCucumber2Jvm" },
		features = "classpath:features/",
		glue = "classpath:stepsDefinitions",
		snippets = SnippetType.CAMELCASE,
		tags = { "@Smoke" },
		monochrome = true
)
public class TestCaseSmoke extends ConfRunner{
	@BeforeClass
	public static void init(){
		confInit();
	}

	@AfterClass
	public static void finish(){
		confFinish();
	}
}
