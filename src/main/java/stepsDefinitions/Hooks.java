package stepsDefinitions;

import br.com.bb.ath.ftabb.exceptions.ElementoNaoLocalizadoException;
import br.com.bb.ath.ftabb.gaw.Plataforma;
import cucumber.api.java.After;
import support.Utils;

import static support.enums.Cookie.isLoggedIntranet;
import static support.enums.SysProps.isLoggedPlataforma;

public class Hooks {
    @After
    public void tearDown() {
        try {
            if (!isLoggedIntranet() & isLoggedPlataforma()) {
                if (!Plataforma.recuperarTituloPagina().intern().equals("Plataforma BB | Analytics e Inteligência Artificial"))
                    Plataforma.fecharPaginaAtual();
            }
        } catch (ElementoNaoLocalizadoException e) {
            new Utils().logError(e);
        }
    }
}
