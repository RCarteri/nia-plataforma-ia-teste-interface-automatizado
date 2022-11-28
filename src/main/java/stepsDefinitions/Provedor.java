package stepsDefinitions;

import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Então;
import pagesObjects.ComponentePage;
import pagesObjects.ProvedorPage;
import support.Utils;

import static org.junit.Assert.assertTrue;
import static support.enums.SelectorsDelays.CIRCLE;

public class Provedor extends Utils {
    private final ProvedorPage pP;
    private final ComponentePage cP;

    public Provedor() {
        this.pP = new ProvedorPage();
        this.cP = new ComponentePage();
    }

    @E("^acessar a página do provedor \"([^\"]*)\"$")
    public void acessarAPaginaDoProvedor(String provedor) {
        try {
            waitInvisibility(CIRCLE);
            cP.getMensagemAlerta("sucesso");
            cP.clickBtnFechar(false, "alerta");
            pP.acessarProvedor(provedor);
        } catch (Exception e) {
            logError(e);
        }
    }

    @Então("^deverá ser apresentados os provedores de serviços de cloud$")
    public void deveraSerApresentadosOsProvedoresDeServicosDeCloud() {
        capturaTela();
        assertTrue(cP.getListaProvedores().size() > 0);
    }
}
