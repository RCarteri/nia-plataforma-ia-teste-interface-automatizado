package pagesObjects.triton;

import cucumber.api.DataTable;
import map.TritonMap;
import stepsDefinitions.triton.Dado;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

import static support.Utils.waitLoadPage;
import static support.enums.SelectorsDelays.CIRCLE;

public class TritonPage extends TritonMap{
    private final List<Dado> dados = new ArrayList<>();
    private String requestOriginal;

    public String getRequestOriginalTxt() {
        return requestOriginal;
    }

    public void setRequestOriginalTxt() {
        this.requestOriginal = getRequestTxt();
    }

    public String getNomeModelo() {
        return getTdNomeModelo().getText();
    }

    public String getPreMaisDetalhes() {
        getBtnMaisDetalhes().click();
        return getBtnPreMaisDetalhes().getText();
    }

    public boolean estaRetornandoInformacoes() {
        setRequestOriginalTxt();
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
        waitLoadPage(CIRCLE);
    }

    private void fillRequest(DataTable table) {
        List<String> linhas = table.asList(String.class);
        for (String linha : linhas) {
            addDado(new Dado(linha));
        }
    }

    private String montarRequest() {
        StringBuilder data = new StringBuilder();
        StringBuilder request = new StringBuilder(this.requestOriginal);
        int indexSize = request.indexOf("-1,");
        int indexData = request.indexOf("\"data\": [") + 9;
        request.replace(indexSize, indexSize + 2, String.valueOf(dados.size()));
        IntStream.range(0, dados.size()).forEachOrdered(i -> {
            data.append("\"")
                    .append(dados.get(i).getDado())
                    .append("\"");
            if (i != dados.size() - 1)
                data.append(",");
        });
        return String.valueOf(request.insert(indexData, data));
    }

    public void editarRequest() {
        getRequest().clear();
        getRequest().sendKeys("Texto aleatório");
    }

    public void limparRequest() {
        getBtnLimpar().click();
    }
}