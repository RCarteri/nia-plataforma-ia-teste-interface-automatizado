package support;

import br.com.bb.ath.ftabb.exceptions.ElementoNaoLocalizadoException;
import br.com.bb.ath.ftabb.gaw.Plataforma;
import cucumber.api.java.After;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static support.Utils.logError;

public class Hooks {
    @After
    public void tearDown() {
        try {
            final boolean estaLogado = Plataforma.estaLogado();
            if (estaLogado) {
                assertTrue(estaLogado);
                if (!Plataforma.recuperarTituloPagina().intern().equals("Plataforma BB | Analytics e Inteligência Artificial"))
                    Plataforma.fecharPaginaAtual();
            } else
                assertFalse(estaLogado);
        } catch (ElementoNaoLocalizadoException e) {
            logError(e);
        }
    }
}
