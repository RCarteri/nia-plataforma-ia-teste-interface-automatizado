package stepsDefinitions;

import br.com.bb.ath.ftabb.exceptions.ElementoNaoLocalizadoException;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import support.Utils;

import static br.com.bb.ath.ftabb.gaw.Plataforma.fecharPaginaAtual;
import static br.com.bb.ath.ftabb.gaw.Plataforma.recuperarTituloPagina;
import static org.junit.Assume.assumeTrue;
import static support.enums.Cookie.isLoggedIntranet;
import static support.enums.SysProps.isLoggedPlataforma;

public class Hooks {
    @Before("@ignore")
    public void skip_scenario(Scenario scenario){
        System.out.println("Scenário ignorado: " + scenario.getName());
        assumeTrue("O cenário " + scenario.getName() + " está definido para ser ignorado.", false);
    }

    @After
    public void tearDown() {
        try {
            if (!isLoggedIntranet() && isLoggedPlataforma()) {
                if (!recuperarTituloPagina().intern().equals("Plataforma BB | Analytics e Inteligência Artificial"))
                    fecharPaginaAtual();
            }
        } catch (ElementoNaoLocalizadoException e) {
            new Utils().logError(e);
        }
    }
}
