package runners;

import br.com.bb.ath.ftabb.runner.FTABBCucumberRunner;
import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

@RunWith(FTABBCucumberRunner.class)
@CucumberOptions(
		plugin = { "pretty", "support.parallel.AllureCucumber2Jvm" },
		features = {"classpath:features/gestao", "classpath:features/primeiroAcesso"},
		glue = "classpath:stepsDefinitions",
		snippets = SnippetType.CAMELCASE,
		tags = { "" },
		monochrome = true
)
public class TestCaseAll extends ConfRunner{
	@BeforeClass
	public static void init(){
		confInit();
	}

	@AfterClass
	public static void finish(){
		confFinish();
	}
}
