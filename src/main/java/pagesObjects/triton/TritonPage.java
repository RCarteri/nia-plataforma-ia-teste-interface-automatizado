package pagesObjects.triton;

import cucumber.api.DataTable;
import map.TritonMap;
import stepsDefinitions.triton.Dado;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static support.Utils.waitLoadPage;
import static support.enums.SelectorsDelays.CARR_PAG;

public class TritonPage extends TritonMap{
    private final List<Dado> dados = new ArrayList<>();

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

    public void addDado(Dado dado) {
        dados.add(dado);
    }

    public void executarRequest(DataTable table) {
        fillRequest(table);
        getRequest().clear();
        getRequest().sendKeys(montarRequest());
        getBtnExecutar().click();
        waitLoadPage(CARR_PAG);
    }

    private void fillRequest(DataTable table) {
        List<String> linhas = table.asList(String.class);
        for (String linha : linhas) {
            addDado(new Dado(linha));
        }
    }

    private String montarRequest() {
        StringBuilder data = new StringBuilder();
        int indexSize = this.request.indexOf("-1,");
        int indexData = this.request.indexOf("\"data\": [") + 9;
        this.request.replace(indexSize, indexSize + 2, String.valueOf(dados.size()));
        IntStream.range(0, dados.size()).forEachOrdered(i -> {
            data.append("\"")
                    .append(dados.get(i).getDado())
                    .append("\"");
            if (i != dados.size() - 1)
                data.append(",");
        });
        return String.valueOf(this.request.insert(indexData, data));
    }
}
