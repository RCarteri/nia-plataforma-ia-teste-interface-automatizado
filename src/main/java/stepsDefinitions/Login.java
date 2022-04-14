package stepsDefinitions;

import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import pagesObjects.LoginPage;
import support.Utils;

import static org.junit.Assert.assertTrue;

public class Login {
    private final LoginPage lP = new LoginPage();

    @Dado("^que a Plataforma esteja fechada, abra a Plataforma$")
    public void queAPlataformaEstejaFechadaAbraAPlataforma() {
       lP.abrirPlataforma();
    }

    @E("^se não estiver logado, realiza o login no Sistema$")
    public void realizarLogin() {
        lP.logar();
    }

    @Quando("^acessar a pagina \"([^\"]*)\"$")
    public void acessarAPagina(String nomePagina) {
        lP.acessarPagina(nomePagina);
    }

    @Então("^verficar se a pagina \"([^\"]*)\" foi carregada com sucesso$")
    public void verficarSeAPaginaFoiCarregadaComSucesso(String titulo) {
        Utils utils = new Utils();
        utils.capturaTela();
        assertTrue(utils.oTituloEigual(titulo));
        System.out.println("Página carregada com sucesso");
    }

    @E("^acessar o menu \"([^\"]*)\" e \"([^\"]*)\"$")
    public void acessarMenu(String nivel1, String nivel2) {
        lP.acessarMenu(nivel1, nivel2);
    }
}
