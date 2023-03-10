package pagesObjects;

import br.com.bb.ath.ftabb.exceptions.ElementoNaoLocalizadoException;
import map.LoginMap;
import support.Utils;

import static br.com.bb.ath.ftabb.FTABB.abrirUrl;
import static br.com.bb.ath.ftabb.gaw.Plataforma.*;
import static java.lang.String.valueOf;
import static java.lang.System.setProperty;
import static support.GetElements.getDriver;
import static support.Utils.*;
import static support.enums.Ambiente.DESENV;
import static support.enums.Cookie.isLoggedIntranet;
import static support.enums.LogTypes.*;
import static support.enums.SelectorsDelays.SPINNER;
import static support.enums.SysProps.IS_LOGGED;
import static support.enums.SysProps.isLoggedPlataforma;
import static support.enums.User.*;

public class LoginPage extends LoginMap {
    private final Utils utils;

    public LoginPage() {
        this.utils = new Utils();
    }

    public void abraPlataforma() {
        if (!(isLoggedIntranet() || isLoggedPlataforma())) {
            abrirPlataforma();
            printLog("A Plataforma foi aberta.", INFO);
        } else {
            printLog("A Plataforma já foi aberta.", INFO);
        }
    }

    public static boolean isPagGestaoCloud() {
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
        utils.setDatapool(ambiente);
        if (isLoggedPlataforma() || isLoggedIntranet()) {
            printLog("O Usuário '" + getUser() + "' - " + getChave() + " esta logado.", INFO);
        } else {
            if (ambiente.equals("homologação"))
                loginPlataforma();
            else
                loginIntranet();
            printLog("Login realizado com o usuário: " + getUser() + " chave: " + getChave(), INFO);
        }
    }

    private void loginIntranet() {
        printLog("Abrindo página da intranet.", INFO);
        abrirUrl(DESENV.getUrl());
        printLog("Preenchendo formulário de login.", INFO);
        getInputChave().sendKeys(getChave());
        getInputSenha().sendKeys(getSenha());
        getBtnEntrar().click();
    }

    private void atualizarPagina() {
        printLog("Não foi possível realizar o login. A página será atualizada.", ERROR);
        getDriver().navigate().refresh();
        loginPlataforma();
    }

    private void loginPlataforma() {
        fillForm();
        if (utils.isElementoNotNull(getAlert())) atualizarPagina();
        if (utils.isElementoNotNull(getInputCodConf())) fillFormCodConf();
        if (utils.isElementoNotNull(getReloginLink())) relogin();
        setProperty(IS_LOGGED.toString(), valueOf(estaLogado()));
    }

    private void relogin() {
        getReloginLink().click();
        printLog("Preenchendo formulário de login.", INFO);
        if (utils.isElementoNotNull(getInputCodConf()))
            fillFormCodConf();
        else
            getBtnLogin().click();
        setProperty(IS_LOGGED.toString(), valueOf(estaLogado()));
    }

    private void fillForm() {
        printLog("Preenchendo formulário de login.", INFO);
        getInputUsername().sendKeys(getChave());
        getInputPassword().sendKeys(getSenha());
        getBtnLogin().click();
        waitInvisibility(SPINNER);
    }

    private void fillFormCodConf() {
        getInputCodConf().sendKeys(getCodConf());
        getBtnLogin().click();
        waitInvisibility(SPINNER);
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
