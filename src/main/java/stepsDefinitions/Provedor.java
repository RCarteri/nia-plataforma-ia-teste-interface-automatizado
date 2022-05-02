package stepsDefinitions;

import cucumber.api.java.pt.Quando;
import pagesObjects.ProvedorPage;

public class Provedor {
    private final ProvedorPage pP;

    public Provedor() {
        this.pP = new ProvedorPage();
    }

    @Quando("^acessar a pagina do provedor \"([^\"]*)\"$")
    public void acessarAPaginaDoProvedor(String provedor) {
        pP.acessarProvedor(provedor);
    }
}
