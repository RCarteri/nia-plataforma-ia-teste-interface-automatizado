package pagesObjects;

import br.com.bb.ath.ftabb.exceptions.ElementoNaoLocalizadoException;
import br.com.bb.ath.ftabb.gaw.Plataforma;
import map.LoginMap;
import org.openqa.selenium.NoSuchElementException;
import support.Razoes;
import support.Utils;

import java.util.Dictionary;

import static java.lang.Boolean.parseBoolean;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import static support.SysProps.IS_LOGGED;
import static support.Utils.getDriver;
import static support.Utils.logError;

public class LoginPage {
    private final Utils utils = new Utils();
    private short count;

    public void abrirPlataforma(){
        System.setProperty(IS_LOGGED.toString(), String.valueOf(Plataforma.estaLogado()));
        final boolean isLogged = parseBoolean(System.getProperty(IS_LOGGED.toString()));
        if (isLogged) {
            assertTrue(isLogged);
            System.out.println("\n    INFO - A Plataforma esta aberta.\n");
        } else {
            Plataforma.abrirPlataforma();
        }
    }

    public void acessarPagina(String nomePagina){
        try {
            String tituloPagina = Plataforma.recuperarTituloPagina();
            if (!(tituloPagina.intern().equals("Plataforma BB | Analytics e Inteligência Artificial"))) {
                Plataforma.selecionarAreaDeTrabalho(nomePagina);
            }
        } catch (ElementoNaoLocalizadoException e) {
            logError(e);
        }
    }

    public void acessarMenu(String nivel1, String nivel2){
        try {
            Plataforma.abrirMenu(nivel1, nivel2);
        } catch (ElementoNaoLocalizadoException e) {
            logError(e);
        }
    }

    public void logar(){
        short MAX_BOUND = 5;
        try {
            final Dictionary<String, String> datapool = utils.getDatapool();
            boolean isLogged = parseBoolean(System.getProperty(IS_LOGGED.toString()));
            if (isLogged) {
                assertTrue(isLogged);
                System.out.println("\n    INFO - Usuario " + datapool.get("chave") + " esta logado.\n");
            } else {
                new Utils().esperar(Razoes.CARR_PAG.getDelay(), Razoes.CARR_PAG.getRazao());
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
            String noSuchElement = e.getMessage().split(" ")[4];
            noSuchElement = noSuchElement.substring(0, noSuchElement.length() - 4);

            System.err.println("\n    ERRO - Um elemento não foi localizado.");
            System.err.println("    ERRO - Não foi possível localizar o elemento \"" + noSuchElement + "\"");
            getDriver().navigate().refresh();

            if (++count <= MAX_BOUND)
                logar();
            else {
                Plataforma.fecharPlataforma();
                fail("Não foi possível logar na Plataforma.");
            }
        }
    }
    public void logoutEFecharPlataforma() {
        try {
            Plataforma.encerrarSessao();
            System.out.println("Sessão encerrada");
            Plataforma.fecharPlataforma();
            System.out.println("Plataforma fechada");
        } catch (ElementoNaoLocalizadoException e) {
            logError(e);
        }
    }
}
