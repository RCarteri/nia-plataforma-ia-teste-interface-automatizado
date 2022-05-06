package pagesObjects;

import br.com.bb.ath.ftabb.exceptions.ElementoNaoLocalizadoException;
import br.com.bb.ath.ftabb.gaw.Plataforma;
import map.LoginMap;
import support.Utils;

import java.util.Dictionary;

import static java.lang.Boolean.parseBoolean;
import static org.junit.Assert.assertTrue;
import static support.GetElements.getDriver;
import static support.Utils.printLog;
import static support.enums.LogTypes.*;
import static support.enums.SysProps.IS_LOGGED;

public class LoginPage extends LoginMap {
    private final Utils utils;

    public LoginPage() {
        this.utils = new Utils();
    }

    public void abrirPlataforma() {
        System.setProperty(IS_LOGGED.toString(), String.valueOf(Plataforma.estaLogado()));
        final boolean isLogged = parseBoolean(System.getProperty(IS_LOGGED.toString()));
        if (isLogged) {
            assertTrue(isLogged);
            printLog("A Plataforma esta aberta.", INFO);
        } else {
            Plataforma.abrirPlataforma();
        }
    }

    public void acessarPagina(String nomePagina) {
        try {
            String tituloPagina = Plataforma.recuperarTituloPagina();
            if (!(tituloPagina.intern().equals("Plataforma BB | Analytics e Inteligência Artificial"))) {
                Plataforma.selecionarAreaDeTrabalho(nomePagina);
            }
        } catch (ElementoNaoLocalizadoException e) {
            utils.logError(e);
        }
    }

    public void acessarMenu(String nivel1, String nivel2) {
        try {
            Plataforma.abrirMenu(nivel1, nivel2);
        } catch (ElementoNaoLocalizadoException e) {
            utils.logError(e);
        }
    }

    public void logar() {
        final Dictionary<String, String> datapool = utils.getDatapool();
        boolean isLogged = parseBoolean(System.getProperty(IS_LOGGED.toString()));
        if (isLogged) {
            printLog("Usuario " + datapool.get("chave") + " esta logado.", INFO);
        } else {
            try {
                getInputUsername().sendKeys(datapool.get("chave"));
                getInputPassword().sendKeys(datapool.get("senha"));
                getBtnLogin().click();
            } catch (Exception e) {
                printLog("Não foi possível realizar o login. A página será atualizada.", ERROR);
                getDriver().navigate().refresh();
            }
            printLog("Login realizado com a chave: " + datapool.get("chave"), INFO);
        }
    }

    public void logoutEFecharPlataforma() {
        try {
            Plataforma.encerrarSessao();
            printLog("Sessão encerrada.", NULL);
            Plataforma.fecharPlataforma();
            printLog("Plataforma fechada.", NULL);
        } catch (ElementoNaoLocalizadoException e) {
            utils.logError(e);
        }
    }
}
