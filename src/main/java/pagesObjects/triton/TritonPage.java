package pagesObjects.triton;

import map.TritonMap;

public class TritonPage extends TritonMap{
    public String getNomeModelo() {
        return getTdNomeModelo().getText();
    }

    public String getPreMaisDetalhes() {
        getBtnMaisDetalhes().click();
        return getBtnPreMaisDetalhes().getText();
    }

    public boolean estaRetornandoInformacoes() {
        return getInformacoes().stream()
                .noneMatch(info -> info.getText() == null) && getRequest() != null;
    }
}
