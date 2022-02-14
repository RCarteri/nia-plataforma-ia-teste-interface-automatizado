package stepsDefinitions;

import br.com.bb.ath.ftabb.exceptions.ElementoNaoLocalizadoException;
import br.com.bb.ath.ftabb.gaw.Plataforma;
import cucumber.api.java.After;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import pagesObjects.PlataformaIncial;
import utils.Razoes;
import utils.Utils;

import java.util.Dictionary;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Hooks {

	private Utils utils;

	public Hooks() {
		utils = new Utils();
	}

	@Dado("^que a Plataforma esteja fechada, abra a Plataforma$")
	public void queAPlataformaEstejaFechadaAbraAPlataforma() {
		try {
			final boolean estaLogado = Plataforma.estaLogado();

			if (estaLogado) {
				assertTrue(estaLogado);
				System.out.println("\n    INFO - A Plataforma está aberta.\n");
				
			} else {
				
				Plataforma.abrirPlataforma();
				utils.esperar(Razoes.CARR_PLAT.getDelay(), Razoes.CARR_PLAT.getRazao());

				Plataforma.getVersaoContextoAtual();
			}
		} catch (ElementoNaoLocalizadoException e) {
			respostaErroElementoNaoLocalizado(e);
		}
	}

	@E("^se não estiver logado, realiza o login no Sistema$")
	public void realizeOLoginNoSistema() {
		try {
			final Dictionary<String, String> datapool = utils.getDatapool();
			final boolean estaLogado = Plataforma.estaLogado();

			if (estaLogado) {

				assertTrue(estaLogado);
				System.out.println("\n    INFO - Usuário " + datapool.get("chave") + " está logado.\n");

			} else {

				Plataforma.fazerLogin(datapool.get("chave"), datapool.get("senha"));

				int count = 0;
				while (!Plataforma.estaLogado()) {
					System.out.print("\n    INFO - ");
					utils.esperar(Razoes.LOGIN.getDelay(), Razoes.LOGIN.getRazao());

					if (++count == 3) {
						System.err.println("\n    ERRO - Não foi possivel carregar a Plataforma.");
						Plataforma.fecharPlataforma();

						assertFalse("\n    Não foi possível logar na Plataforma.", true);

						System.exit(0);
					}
				}

				System.out.println("\n    INFO - Login realizado com a chave: " + datapool.get("chave") + "\n");
			}
		} catch (ElementoNaoLocalizadoException e) {
			respostaErroElementoNaoLocalizado(e);
		}
	}

	@Então("^realizar o log out na Plataforma e fechá-la$")
	public void realizarOLogOutNaPlataformaEFecháLa() {
		try {
			final PlataformaIncial pInicial = new PlataformaIncial();

			if (utils.elementoExisteEstaVisivel(pInicial.elemFecharModal))
				pInicial.elemFecharModal.clicar();

			utils.fecharSitema(pInicial.btnPerfil);

		} catch (ElementoNaoLocalizadoException e) {
			respostaErroElementoNaoLocalizado(e);
		}
	}

	@After
	public void tearDown() {
		try {
			final boolean estaLogado = Plataforma.estaLogado();

			if (estaLogado) {
				assertTrue(estaLogado);

				if (Plataforma.recuperarTituloPagina().intern() != "Plataforma BB | Analytics e Inteligência Artificial".intern())
					Plataforma.fecharPaginaAtual();
			} else
				assertFalse(estaLogado);

		} catch (ElementoNaoLocalizadoException e) {
			respostaErroElementoNaoLocalizado(e);
		}finally {
			Utils.getDriver().close();
		}
	}

	@Quando("^acessar a página \"([^\"]*)\"$")
	public void acessarAPágina(String nomePagina){
		try {
			Plataforma.selecionarAreaDeTrabalho(nomePagina);
		} catch (ElementoNaoLocalizadoException e) {
			respostaErroElementoNaoLocalizado(e);
		}
	}

	@E("^acessar a tela \"([^\"]*)\" e \"([^\"]*)\"$")
	public void acessarATelaE(String nivel1, String nivel2){
		try {
			Plataforma.abrirMenu(nivel1, nivel2);
		} catch (ElementoNaoLocalizadoException e) {
			respostaErroElementoNaoLocalizado(e);
		}
	}

	private void respostaErroElementoNaoLocalizado(ElementoNaoLocalizadoException e){
		System.err.println("Um elemento não foi localizado.");
		System.err.println("Mensagem: " + e.getMessage());
		e.printStackTrace();
	}
}
