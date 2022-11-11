package stepsDefinitions;

import br.com.bb.ath.ftabb.exceptions.ElementoNaoLocalizadoException;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import pagesObjects.sections.SideBarSection;
import support.Utils;

import static br.com.bb.ath.ftabb.gaw.Plataforma.fecharPaginaAtual;
import static org.junit.Assume.assumeTrue;
import static pagesObjects.LoginPage.isPagGestaoCloud;
import static support.enums.SysProps.isLoggedPlataforma;

public class Hooks {
    @Before("@ignore")
    public void skip_scenario(Scenario scenario) {
        System.out.println("Scenário ignorado: " + scenario.getName());
        assumeTrue("O cenário " + scenario.getName() + " está definido para ser ignorado.", false);
    }

    @After
    public void tearDown() {
        try {
            if (isLoggedPlataforma())
                if (isPagGestaoCloud())
                    new SideBarSection().acessarMenu("Inicio");
                else
                    fecharPaginaAtual();
        } catch (ElementoNaoLocalizadoException e) {
            new Utils().logError(e);
        }
    }
}
