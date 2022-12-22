package stepsDefinitions;

import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Quando;
import pagesObjects.LoginPage;
import pagesObjects.ProvedorPage;
import support.Utils;

import static br.com.bb.ath.ftabb.gaw.Plataforma.abrirMenu;
import static pagesObjects.LoginPage.isPagGestaoCloud;
import static support.enums.LogTypes.ERROR;
import static support.enums.LogTypes.INFO;
import static support.enums.SelectorsDelays.CIRCLE;

public class Login extends Utils {
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

    @E("^se não estiver na home acessar o menu \"([^\"]*)\" e \"([^\"]*)\"$")
    public void acessarMenu(String nivel1, String nivel2) {
        try {
            if (!isPagGestaoCloud()) {
                abrirMenu(nivel1, nivel2);
                printLog("Menus acessados com sucesso: " + nivel1 + " " + nivel2, INFO);
                waitInvisibility(CIRCLE);
                new ProvedorPage().fecharAlerta();
            }
        } catch (Exception e) {
            logError(e);
        }
    }
}
