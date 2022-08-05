package stepsDefinitions;

import br.com.bb.ath.ftabb.exceptions.ElementoNaoLocalizadoException;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import pagesObjects.LoginPage;
import support.Utils;

import static br.com.bb.ath.ftabb.gaw.Plataforma.recuperarTituloPagina;
import static org.junit.Assert.assertEquals;
import static support.enums.SelectorsDelays.CARR_PAG;

public class Login extends Utils{
    private final LoginPage lP;

    public Login() {
        this.lP = new LoginPage();
    }

    @Dado("^que a Plataforma esteja fechada, abra a Plataforma$")
    public void queAPlataformaEstejaFechadaAbraAPlataforma() {
        try {
            lP.abraPlataforma();
        } catch (Exception e) {
            logError(e);
        }
    }

    @E("^se não estiver logado, realiza o login em \"([^\"]*)\"$")
    public void realizarLogin(String ambiente) {
        try {
            lP.logar(ambiente);
        } catch (Exception e) {
            logError(e);
        }
    }

    @Quando("^acessar a página \"([^\"]*)\"$")
    public void acessarAPagina(String nomePagina) {
        try {
            lP.acessarPagina(nomePagina);
        } catch (Exception e) {
            logError(e);
        }
    }

    @Então("^a página \"([^\"]*)\" deverá ser carregada com sucesso$")
    public void aPaginaDeveraSerCarregadaComSucesso(String titulo) throws ElementoNaoLocalizadoException {
        try {
            waitLoadPage(CARR_PAG);
            assertEquals("A página não foi carregada.", recuperarTituloPagina(), titulo);
        } finally {
            capturaTela();
        }
    }

    @E("^acessar o menu \"([^\"]*)\" e \"([^\"]*)\"$")
    public void acessarMenu(String nivel1, String nivel2) {
        try {
            lP.acessarMenu(nivel1, nivel2);
        } catch (Exception e) {
            logError(e);
        }
    }
}
