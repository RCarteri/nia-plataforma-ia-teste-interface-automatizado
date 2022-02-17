package runners;

import ath_allure_arq3.main.AllureARQ3;
import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import stepsDefinitions.Hooks;
import utils.APIRest.APIRest;
import utils.APIRest.Urls;
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
	static APIRest apiRest = new APIRest();

	@BeforeClass
	public static void init(){
		AllureARQ3.ConfigInicialAllureARQ3();
		new Utils().deletarAllureResults();
		apiRest.atualizarAllureArq3(Urls.LIMPAR_RESULTADOS.getUrl());
		apiRest.atualizarAllureArq3(Urls.LIMPAR_HISTORICO.getUrl());
	}

	@AfterClass
	public static void finish() {
		new Hooks().fecharPlataforma();
		new AllureARQ3().enviarRelatorio("servidor.allure.docker.ulr1");
		apiRest.atualizarAllureArq3(Urls.GERAR_RELATORIO.getUrl());
	}
}
