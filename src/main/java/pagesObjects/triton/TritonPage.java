package pagesObjects.triton;

import map.TritonMap;

public class TritonPage {
 private final TritonMap tM = new TritonMap();

    public String getNomeModelo() {
        return tM.getTdNomeModelo().getText();
    }

    public String getPreMaisDetalhes() {
        tM.getBtnMaisDetalhes().click();
        return tM.getBtnPreMaisDetalhes().getText();
    }

    public boolean estaRetornandoInformacoes() {
        return tM.getInformacoes().stream()
                .noneMatch(info -> info.getText() == null) && tM.getRequest() != null;
    }
}
