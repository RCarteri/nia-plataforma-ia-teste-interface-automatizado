package stepsDefinitions;

import cucumber.api.java.pt.Quando;
import pagesObjects.ProvedorPage;

public class Provedor {
    @Quando("^acessar a pagina do provedor \"([^\"]*)\"$")
    public void acessarAPaginaDoProvedor(String provedor) {
        new ProvedorPage().acessarProvedor(provedor);
    }

    @Quando("^escolher \"([^\"]*)\"$")
    public void escolher(String opcao) {
        new ProvedorPage().clicarBotaoOpcao(opcao);
    }

    @Quando("^exibir \"([^\"]*)\"$")
    public void exibir(String opcao) {
        int localizacao = 0;
        switch (opcao) {
            case "instância":
                localizacao = 4; // Está sendo sitado 4 pois os anteriores estão retornando erro ao abrir
                break;
            case "projeto":
            case "grupo":
            case "catálogo":
            case "storage":
            case "modelo":
                localizacao = 1;
                break;
        }
        new ProvedorPage().clicarBotaoLista(--localizacao); // sem criar uma nova instância ele não retorna a lista de botoes
    }
}
