package stepsDefinitions;

import br.com.bb.ath.ftabb.exceptions.ElementoNaoLocalizadoException;
import br.com.bb.ath.ftabb.gaw.Plataforma;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import pagesObjects.LoginPage;
import support.Utils;

import static org.junit.Assert.assertEquals;

public class Login {
    private final LoginPage lP;

    public Login() {
        this.lP = new LoginPage();
    }

    @Dado("^que a Plataforma esteja fechada, abra a Plataforma$")
    public void queAPlataformaEstejaFechadaAbraAPlataforma() {
        lP.abrirPlataforma();
    }

    @E("^se não estiver logado, realiza o login no Sistema$")
    public void realizarLogin() {
        lP.logar();
    }

    @Quando("^acessar a página \"([^\"]*)\"$")
    public void acessarAPagina(String nomePagina) {
        lP.acessarPagina(nomePagina);
    }

    @Então("^a página \"([^\"]*)\" deverá ser carregada com sucesso$")
    public void aPaginaDeveraSerCarregadaComSucesso(String titulo) throws ElementoNaoLocalizadoException {
        try {
            assertEquals("A página não foi carregada.", Plataforma.recuperarTituloPagina(), titulo);
        } finally {
            new Utils().capturaTela();
        }
    }

    @E("^acessar o menu \"([^\"]*)\" e \"([^\"]*)\"$")
    public void acessarMenu(String nivel1, String nivel2) {
        lP.acessarMenu(nivel1, nivel2);
    }
}
