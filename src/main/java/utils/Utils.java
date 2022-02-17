package utils;

import br.com.bb.ath.ftabb.FTABBContext;
import br.com.bb.ath.ftabb.elementos.Elemento;
import br.com.bb.ath.ftabb.exceptions.DataPoolException;
import br.com.bb.ath.ftabb.exceptions.ElementoNaoLocalizadoException;
import br.com.bb.ath.ftabb.gaw.Plataforma;
import br.com.bb.ath.ftabb.utilitarios.FTABBUtils;
import io.qameta.allure.Allure;
import org.openqa.selenium.*;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.UUID;

public class Utils extends FTABBUtils {
	public static WebDriver getDriver() {
		return (WebDriver) FTABBContext.getContext().getContextBrowserDriver().getDriver();
	}

	public void esperar(long segundos, String razao) {
		System.out.println("    Aguardando " + segundos + " segundo(s) para " + razao + "...");
		sleep(segundos);
	}

	public void capturaTela() {
		esperar(Razoes.CAP_TELA.getDelay(), Razoes.CAP_TELA.getRazao());
		capturarTela();
		allureCapturarTela();
		System.out.println("    INFO - Tela capturada.\n");
	}

	public void deletarAllureResults(){
		Path dirPath = Paths.get("./target/allure-results");
		try {
			Files.walk(dirPath)
					.map(Path::toFile)
					.sorted(Comparator.comparing(File::isDirectory))
					.forEach(File::delete);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public boolean elementoExisteEstaVisivel(Elemento elem) {
		try {
			if (elem.elementoExiste()) {
				if (!elem.elementoEstaVisivel())
					throw new ElementoNaoLocalizadoException(
							"O elemento \"" + elem.getNome() + "\" existe, mas não está visível.");
				return true;
			}
			throw new ElementoNaoLocalizadoException(
					"Não foi possível localizar o elemento \"" + elem.getNome() + "\".");
		} catch (ElementoNaoLocalizadoException enl) {
			logError(enl);
		}
		return false;
	}

	public boolean oTituloEigual(String titulo) {
		try {
			esperar(Razoes.CARR_PAG.getDelay(), Razoes.CARR_PAG.getRazao());
			final String paginaTitulo = Plataforma.recuperarTituloPagina().toLowerCase();
			if (paginaTitulo.intern().equals(titulo.toLowerCase().intern()))
				return true;
			else
				throw new ElementoNaoLocalizadoException("\n\nO título buscado é: " + titulo.toLowerCase()
						+ "\nO título recuperado é: " + paginaTitulo + "\n");
		} catch (ElementoNaoLocalizadoException enl) {
			logError(enl);
		}
		return false;
	}

	public Dictionary<String, String> getDatapool() {
		try {
			final Dictionary<String, String> result = new Hashtable<>();
			result.put("chave", $("login_plataforma.chaveF.chave"));
			result.put("senha", $("login_plataforma.chaveF.senha"));
			return result;
		} catch (DataPoolException dpe) {
			logError(dpe);
			return null;
		}
	}

	public static void rolarPaginaAteElemento(WebElement elemento){
		((JavascriptExecutor) getDriver()).executeScript("arguments[0].scrollIntoView(true);", elemento);
	}

	private void logError(Exception enl) {
		System.err.println("\nAlugum erro ocorreu!");
		System.err.println("Mensagem: " + enl.getMessage() + "\n");
		enl.printStackTrace();
	}

	private void allureCapturarTela() {
		final Object driver = FTABBContext.getContext().getContextBrowserDriver().getDriver();
		final ByteArrayInputStream byteArrInputStream = new ByteArrayInputStream(
				((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES));
		final String uuid = UUID.randomUUID().toString().substring(0, 8);
		Allure.addAttachment("Print_" + uuid + ".png", byteArrInputStream);
	}
}
