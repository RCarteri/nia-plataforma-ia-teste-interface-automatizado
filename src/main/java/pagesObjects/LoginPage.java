package pagesObjects;

import br.com.bb.ath.ftabb.exceptions.ElementoNaoLocalizadoException;
import map.LoginMap;
import org.openqa.selenium.TimeoutException;
import support.Utils;

import static br.com.bb.ath.ftabb.FTABB.abrirUrl;
import static br.com.bb.ath.ftabb.gaw.Plataforma.*;
import static java.lang.String.valueOf;
import static java.lang.System.setProperty;
import static support.GetElements.getDriver;
import static support.Utils.printLog;
import static support.Utils.waitInvisibility;
import static support.enums.Ambiente.DESENV;
import static support.enums.Cookie.isLoggedIntranet;
import static support.enums.LogTypes.*;
import static support.enums.SelectorsDelays.LOGIN;
import static support.enums.SelectorsDelays.SPINNER;
import static support.enums.SysProps.IS_LOGGED;
import static support.enums.SysProps.isLoggedPlataforma;
import static support.enums.User.*;

public class LoginPage {
    private final Utils utils;
    private int tentativa;

    public LoginPage() {
        this.utils = new Utils();
    }

    public void abraPlataforma() {
        if (!(isLoggedIntranet() || isLoggedPlataforma())) {
            abrirPlataforma();
            printLog("A Plataforma foi aberta.", INFO);
        } else {
            printLog("A Plataforma está aberta.", INFO);
        }
    }

    public static boolean isPagGestaoCloud(){
        try {
            return recuperarTituloPagina().equals("Gestão (Cloud) NOVO");
        } catch (ElementoNaoLocalizadoException e) {
            e.printStackTrace();
            return false;
        }
    }

    public void acessarPagina(String nomePagina) {
        try {
            String tituloPagina = recuperarTituloPagina();
            if (!(tituloPagina.intern().equals("Home Tecnologia") || tituloPagina.intern().equals("Gestão (Cloud) NOVO")))
                selecionarAreaDeTrabalho(nomePagina);
        } catch (ElementoNaoLocalizadoException e) {
            utils.logError(e);
        }
    }

    public void logar(String ambiente) {
        utils.setDatapool();
        if (isLoggedPlataforma() || isLoggedIntranet()) {
            printLog("O Usuário '" + getUser() + "' - " + getChave() + " esta logado.", INFO);
        } else {
            LoginMap lM = new LoginMap();
            switch (ambiente) {
                case "homologação":
                    try {
                        loginPlataforma(lM);
                        setProperty(IS_LOGGED.toString(), valueOf(estaLogado()));
                        try {
                            waitInvisibility(LOGIN);
                        } catch (TimeoutException e) {
                            novaTentativa(ambiente);
                        }
                    } catch (Exception e) {
                        atualizarPagina(ambiente);
                    }
                    break;
                case "desenvolvimento":
                    loginIntranet(lM);
                    break;
            }
            printLog("Login realizado com o usuário '" + getUser() + "' chave: " + getChave(), INFO);
        }
    }

    private void loginIntranet(LoginMap lM) {
        printLog("Abrindo página da intranet.", INFO);
        abrirUrl(DESENV.getUrl());
        printLog("Preenchendo formulário de login.", INFO);
        lM.getInputChave().sendKeys(getChave());
        lM.getInputSenha().sendKeys(getSenha());
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
        printLog("Tentativa de login: " + tentativa + "/3", INFO);
        getDriver().navigate().refresh();
        logar(ambiente);
    }

    private void loginPlataforma(LoginMap lM) {
        printLog("Preenchendo formulário de login.", INFO);
        lM.getInputUsername().sendKeys(getChave());
        lM.getInputPassword().sendKeys(getSenha());
        lM.getBtnLogin().click();
        waitInvisibility(SPINNER);
        if (!getCodConf().equals("")) {
            lM.getInputCodConf().sendKeys(getCodConf());
            lM.getBtnLogin().click();
        }
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
