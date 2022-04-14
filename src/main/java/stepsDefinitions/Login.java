package stepsDefinitions;

import br.com.bb.ath.ftabb.exceptions.ElementoNaoLocalizadoException;
import br.com.bb.ath.ftabb.gaw.Plataforma;
import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import map.LoginMap;
import org.openqa.selenium.NoSuchElementException;
import pagesObjects.PlataformaIncialPage;
import support.Razoes;
import support.Utils;

import java.util.Dictionary;

import static java.lang.Boolean.parseBoolean;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static support.SysProps.IS_LOGGED;
import static support.Utils.getDriver;
import static support.Utils.logError;

public class Login {
    private final Utils utils = new Utils();
    private short count;

    @Dado("^que a Plataforma esteja fechada, abra a Plataforma$")
    public void queAPlataformaEstejaFechadaAbraAPlataforma() {
        System.setProperty(IS_LOGGED.toString(), String.valueOf(Plataforma.estaLogado()));
        final boolean isLogged = parseBoolean(System.getProperty(IS_LOGGED.toString()));
        if (isLogged) {
            assertTrue(isLogged);
            System.out.println("\n    INFO - A Plataforma esta aberta.\n");
        } else {
            Plataforma.abrirPlataforma();
        }
    }

    public void realizarOLogOutNaPlataformaEFechaLa() {
        utils.fecharSitema(new PlataformaIncialPage().btnPerfil);
    }

    @Quando("^acessar a pagina \"([^\"]*)\"$")
    public void acessarApagina(String nomePagina) {
        try {
            String tituloPagina = Plataforma.recuperarTituloPagina();
            if (!(tituloPagina.intern().equals("Plataforma BB | Analytics e Inteligência Artificial"))) {
                Plataforma.selecionarAreaDeTrabalho(nomePagina);
            }
        } catch (ElementoNaoLocalizadoException e) {
            logError(e);
        }
    }

    @Então("^verficar se a pagina \"([^\"]*)\" foi carregada com sucesso$")
    public void verficar_se_a_pagina_foi_carregada_com_sucesso(String titulo) {
        utils.capturaTela();
        assertTrue(utils.oTituloEigual(titulo));
        System.out.println("Página carregada com sucesso");
    }

    @E("^acessar a tela \"([^\"]*)\" e \"([^\"]*)\"$")
    public void acessarATelaE(String nivel1, String nivel2) {
        try {
            Plataforma.abrirMenu(nivel1, nivel2);
        } catch (ElementoNaoLocalizadoException e) {
            logError(e);
        }
    }

    @E("^se não estiver logado, realiza o login no Sistema$")
    public void logar() {
        short MAX_BOUND = 5;
        try {
            final Dictionary<String, String> datapool = utils.getDatapool();
            boolean isLogged = parseBoolean(System.getProperty(IS_LOGGED.toString()));
            if (isLogged) {
                assertTrue(isLogged);
                System.out.println("\n    INFO - Usuario " + datapool.get("chave") + " esta logado.\n");
            } else {
                utils.esperar(Razoes.CARR_PAG.getDelay(), Razoes.CARR_PAG.getRazao());
                LoginMap lM = new LoginMap();
                lM.getInputUsername().sendKeys(datapool.get("chave"));
                lM.getInputPassword().sendKeys(datapool.get("senha"));
                lM.getBtnLogin().click();
                int count = 0;
                while (!isLogged) {
                    if (++count == MAX_BOUND) {
                        Plataforma.fecharPlataforma();
                        fail("Não foi possível carregar a Plataforma após o login.");
                        System.exit(0);
                    }
                    System.setProperty(IS_LOGGED.toString(), String.valueOf(Plataforma.estaLogado()));
                    isLogged = parseBoolean(System.getProperty(IS_LOGGED.toString()));
                }
                System.out.println("\n    INFO - Login realizado com a chave: " + datapool.get("chave") + "\n");
            }
        } catch (NoSuchElementException e) {
            logError(e);
            getDriver().navigate().refresh();

            if (++count <= MAX_BOUND)
                logar();
            else {
                Plataforma.fecharPlataforma();
                fail("Não foi possível logar na Plataforma.");
            }
        }
    }
}
