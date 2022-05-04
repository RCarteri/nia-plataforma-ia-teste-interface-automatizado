package runners.suites;

import br.com.bb.ath.ftabb.runner.FTABBCucumberRunner;
import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import runners.ConfRunner;

@RunWith(FTABBCucumberRunner.class)
@CucumberOptions(
		plugin = { "pretty", "support.parallel.AllureCucumber2Jvm" },
		features = "classpath:features/",
		glue = "classpath:stepsDefinitions",
		snippets = SnippetType.CAMELCASE,
		tags = { "@S003" },
		monochrome = true
)
public class TestSuiteS003GruposDeAcesso extends ConfRunner{
	@BeforeClass
	public static void init(){
		confInit();
	}

	@AfterClass
	public static void finish(){
		confFinish();
	}
}
