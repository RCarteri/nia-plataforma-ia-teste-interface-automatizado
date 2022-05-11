package pagesObjects;

import br.com.bb.ath.ftabb.exceptions.ElementoNaoLocalizadoException;
import br.com.bb.ath.ftabb.gaw.Plataforma;
import map.LoginMap;
import org.openqa.selenium.TimeoutException;
import support.Utils;

import static support.GetElements.getDriver;
import static support.Utils.printLog;
import static support.Utils.waitLoadPage;
import static support.enums.LogTypes.*;
import static support.enums.SelectorsDelays.LOGIN;
import static support.enums.SysProps.IS_LOGGED;
import static support.enums.SysProps.isLogged;
import static support.enums.User.*;

public class LoginPage extends LoginMap {
    private final Utils utils;
    int tentativa;

    public LoginPage() {
        this.utils = new Utils();
    }

    public void abrirPlataforma() {
        System.setProperty(IS_LOGGED.toString(), String.valueOf(Plataforma.estaLogado()));
        if (isLogged()) {
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
        utils.setDatapool();
        if (isLogged()) {
            printLog("O Usuário '" +  getUser() + "' - " + getChave() + " esta logado.", INFO);
        } else {
            try {
                getInputUsername().sendKeys(getChave());
                getInputPassword().sendKeys(getSenha());
                getBtnLogin().click();
                try {
                    waitLoadPage(LOGIN);
                } catch (TimeoutException e) {
                    if (++tentativa == 3) {
                        printLog("Não foi possível realizar o login pois não saiu da tela de login. A plataforma será fechada.", ERROR);
                        Plataforma.fecharPlataforma();
                    }
                    getDriver().navigate().refresh();
                    logar();
                }
            } catch (Exception e) {
                printLog("Não foi possível realizar o login. A página será atualizada.", ERROR);
                getDriver().navigate().refresh();
            }
            printLog("Login realizado com o usuário '" + getUser() + "' chave: " + getChave(), INFO);
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
