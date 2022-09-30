package stepsDefinitions;

import cucumber.api.java.pt.E;
import pagesObjects.ComponentePage;
import pagesObjects.ProvedorPage;
import support.Utils;

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
            waitLoadPage(CIRCLE);
            cP.getMensagemAlerta("sucesso");
            cP.clickBtnFechar(false, "alerta");
            pP.acessarProvedor(provedor);
        } catch (Exception e) {
            logError(e);
        }
    }
}
