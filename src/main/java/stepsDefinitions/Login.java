package stepsDefinitions;

import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import pagesObjects.ComponentePage;
import pagesObjects.LoginPage;
import support.Utils;

import static br.com.bb.ath.ftabb.gaw.Plataforma.abrirMenu;
import static br.com.bb.ath.ftabb.gaw.Plataforma.recuperarTituloPagina;
import static org.junit.Assert.assertEquals;
import static support.enums.LogTypes.ERROR;
import static support.enums.LogTypes.INFO;

public class Login extends Utils {
    private final LoginPage lP;
    private final ComponentePage cP;

    public Login() {
        this.lP = new LoginPage();
        this.cP = new ComponentePage();
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
            printLog("Não foi possível realizar o login.", ERROR);
            logError(e);
        }
    }

    @Quando("^acessar a página \"([^\"]*)\"$")
    public void acessarAPagina(String nomePagina) {
        try {
            lP.acessarPagina(nomePagina);
            printLog("Página acessada com sucesso: " + nomePagina, INFO);
        } catch (Exception e) {
            logError(e);
        }
    }

    @Então("^a página \"([^\"]*)\" deverá ser carregada com sucesso$")
    public void aPaginaDeveraSerCarregadaComSucesso(String titulo) {
        try {
            assertEquals("A página não foi carregada.", recuperarTituloPagina(), titulo);
            cP.clickBtnFechar(false, "alerta");
        } catch (Exception e) {
            logError(e);
        }
    }

    @E("^acessar o menu \"([^\"]*)\" e \"([^\"]*)\"$")
    public void acessarMenu(String nivel1, String nivel2) {
        try {
            abrirMenu(nivel1, nivel2);
            printLog("Menus acessados com sucesso: " + nivel1 + " " + nivel2, INFO);
        } catch (Exception e) {
            logError(e);
        }
    }
}
