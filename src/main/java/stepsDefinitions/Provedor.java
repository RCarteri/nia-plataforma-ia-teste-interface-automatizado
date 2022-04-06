package stepsDefinitions;

import cucumber.api.java.pt.Quando;
import pagesObjects.ProvedorPage;

public class Provedor {
    @Quando("^acessar a pagina do provedor \"([^\"]*)\"$")
    public void acessarAPaginaDoProvedor(String provedor) {
        new ProvedorPage().acessarProvedor(provedor);
    }
}
