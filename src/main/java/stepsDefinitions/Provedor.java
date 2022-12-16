package stepsDefinitions;

import cucumber.api.java.pt.Então;
import pagesObjects.ProvedorPage;
import support.Utils;

import static org.junit.Assert.assertTrue;

public class Provedor extends Utils {
//    @E("^acessar a página do provedor \"([^\"]*)\"$")
//    public void acessarAPaginaDoProvedor(String provedor) {
//        try {
//            waitInvisibility(CIRCLE);
//            cP.getMensagemAlerta("sucesso");
//            cP.clickBtnFechar(false, "alerta");
//            pP.acessarProvedor(provedor);
//        } catch (Exception e) {
//            logError(e);
//        }
//    }

    @Então("^deverá ser apresentados os provedores de serviços de cloud$")
    public void deveraSerApresentadosOsProvedoresDeServicosDeCloud() {
        capturaTela();
        assertTrue(new ProvedorPage().getProvedores().size() > 0);
    }
}
