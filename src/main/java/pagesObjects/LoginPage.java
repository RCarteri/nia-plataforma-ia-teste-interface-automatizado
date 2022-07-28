package pagesObjects;

import br.com.bb.ath.ftabb.exceptions.ElementoNaoLocalizadoException;
import br.com.bb.ath.ftabb.gaw.Plataforma;
import map.LoginMap;
import org.openqa.selenium.TimeoutException;
import support.Utils;

import static br.com.bb.ath.ftabb.FTABB.abrirUrl;
import static br.com.bb.ath.ftabb.gaw.Plataforma.*;
import static java.lang.System.setProperty;
import static support.GetElements.getDriver;
import static support.Utils.printLog;
import static support.Utils.waitLoadPage;
import static support.enums.Ambiente.DESENV;
import static support.enums.LogTypes.*;
import static support.enums.SelectorsDelays.LOGIN;
import static support.enums.SysProps.IS_LOGGED;
import static support.enums.SysProps.isLogged;
import static support.enums.User.*;

public class LoginPage {
    private final Utils utils;
    private int tentativa;

    public LoginPage() {
        this.utils = new Utils();
    }

    public void abrirPlataforma() {
        setProperty(IS_LOGGED.toString(), String.valueOf(estaLogado()));
        if (isLogged()) {
            printLog("A Plataforma esta aberta.", INFO);
        } else {
            Plataforma.abrirPlataforma();
        }
    }

    public void acessarPagina(String nomePagina) {
        try {
            String tituloPagina = recuperarTituloPagina();
            if (!(tituloPagina.intern().equals("Plataforma BB | Analytics e Inteligência Artificial"))) {
                selecionarAreaDeTrabalho(nomePagina);
            }
        } catch (ElementoNaoLocalizadoException e) {
            utils.logError(e);
        }
    }

    public void acessarMenu(String nivel1, String nivel2) {
        try {
            abrirMenu(nivel1, nivel2);
        } catch (ElementoNaoLocalizadoException e) {
            utils.logError(e);
        }
    }

    public void logar(String ambiente) {
        utils.setDatapool(ambiente);
        if (isLogged()) {
            printLog("O Usuário '" + getUser() + "' - " + getChave() + " esta logado.", INFO);
        } else {
            LoginMap lM = new LoginMap();
            switch (ambiente) {
                case "homologacao":
                    try {
                        loginPlataforma(lM);
                        try {
                            waitLoadPage(LOGIN);
                        } catch (TimeoutException e) {
                            novaTentativa(ambiente);
                        }
                    } catch (Exception e) {
                        atualizarPagina(ambiente);
                    }
                    break;
                case "desenvolvimento":
                    loginIntranet(ambiente, lM);
                    break;
            }
            printLog("Login realizado com o usuário '" + getUser() + "' chave: " + getChave(), INFO);
        }
    }

    private void loginIntranet(String ambiente, LoginMap lM) {
        abrirUrl(DESENV.getUrl());
        utils.setDatapool(ambiente);
        lM.getInputChave().sendKeys(getChave());
        lM.getInputSenha().sendKeys(getSenha());
        System.out.println(getSenha());
        lM.getBtnEntrar().click();
    }

    private void atualizarPagina(String ambiente) {
        printLog("Não foi possível realizar o login. A página será atualizada.", ERROR);
        getDriver().navigate().refresh();
        logar(ambiente);
    }

    private void novaTentativa(String ambiente) {
        if (++tentativa == 3) {
            printLog("Não foi possível realizar o login pois não saiu da tela de login. A plataforma será fechada.", ERROR);
            fecharPlataforma();
        }
        getDriver().navigate().refresh();
        logar(ambiente);
    }

    private void loginPlataforma(LoginMap lM) {
        lM.getInputUsername().sendKeys(getChave());
        lM.getInputPassword().sendKeys(getSenha());
        lM.getBtnLogin().click();
    }

    public void logoutEFecharPlataforma() {
        try {
            encerrarSessao();
            printLog("Sessão encerrada.", NULL);
            fecharPlataforma();
            printLog("Plataforma fechada.", NULL);
        } catch (ElementoNaoLocalizadoException e) {
            utils.logError(e);
        }
    }
}
