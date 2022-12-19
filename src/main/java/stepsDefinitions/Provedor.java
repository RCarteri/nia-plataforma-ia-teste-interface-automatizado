package stepsDefinitions;

import cucumber.api.java.pt.Então;
import pagesObjects.ProvedorPage;
import support.Utils;

import static org.junit.Assert.assertTrue;

public class Provedor extends Utils {
    @Então("^deverá ser apresentados os provedores de serviços de cloud$")
    public void deveraSerApresentadosOsProvedoresDeServicosDeCloud() {
        capturaTela();
        assertTrue(new ProvedorPage().getListProvedores().size() > 0);
    }
}
