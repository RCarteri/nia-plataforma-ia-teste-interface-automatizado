package stepsDefinitions;

import cucumber.api.java.pt.E;
import pagesObjects.ComponentePage;
import pagesObjects.ProvedorPage;

public class Provedor {
    private final ProvedorPage pP;
    private final ComponentePage cP;

    public Provedor() {
        this.pP = new ProvedorPage();
        this.cP = new ComponentePage();
    }

    @E("^acessar a página do provedor \"([^\"]*)\"$")
    public void acessarAPaginaDoProvedor(String provedor) {
        cP.getTxtMensagemAlerta("sucesso");
        cP.clickBtnFechar(false,"alerta");
        pP.acessarProvedor(provedor);
    }
}
