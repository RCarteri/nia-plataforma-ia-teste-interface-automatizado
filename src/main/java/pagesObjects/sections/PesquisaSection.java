package pagesObjects.sections;

import map.PesquisaMap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static support.Utils.printLog;
import static support.enums.LogTypes.INFO;

public class PesquisaSection {
    private final PesquisaMap pM;
    private final Map<String, Boolean> validacaoPesquisa;
    private String mensagemPesquisaInvalida;

    public PesquisaSection() {
        this.mensagemPesquisaInvalida = "";
        this.pM = new PesquisaMap();
        this.validacaoPesquisa = new HashMap<>();
    }

    public void pesquisar(String palavra, @NotNull String local) {
        printLog("Pesquisando por: '" + palavra + "' no " + local + ".", INFO);
        switch (local) {
            case "componente":
                pM.getInputPesquisa().sendKeys(palavra);
                break;
            case "modal":
                pM.getInputPesquisaModal().sendKeys(palavra);
                break;
        }
    }

    public String getTxtInputFiltro(@NotNull String local) {
        switch (local) {
            case "componente":
                return pM.getInputPesquisa().getText();
            case "modal":
                return pM.getInputPesquisaModal().getText();
        }
        return null;
    }

    public void limparPesquisa(@NotNull String local) {
        switch (local) {
            case "componente":
                pM.getBtnLimparPesquisa().click();
                break;
            case "modal":
                pM.getBtnLimparFiltroPesquisa().click();
                break;
        }
    }

    public void validarPesquisa(String mensagem, Boolean validacao) {
        this.validacaoPesquisa.put(mensagem, validacao);
    }

    public boolean getValidacaoPesquisa() {
        this.mensagemPesquisaInvalida = validacaoPesquisa.entrySet().stream()
                .filter(entry -> !entry.getValue())
                .map(entry -> entry.getKey() + "\n")
                .collect(Collectors.joining("", this.mensagemPesquisaInvalida, ""));
        return this.mensagemPesquisaInvalida.equals("");
    }

    public String getMensagemPesquisaInvalida() {
        return this.mensagemPesquisaInvalida;
    }

    public String getDadoPesquisa(String local, @NotNull String dado) {
        switch (dado) {
            case "inválido":
                return "#inválido";
            case "válido":
                return getDadoValido(local);
            default:
                return null;
        }
    }

    private @Nullable String getDadoValido(@NotNull String local) {
        switch (local) {
            case "componente":
                return pM.getListaNomesComponente().get(0).getText();
            case "modal":
                return pM.getListaNomesModal().get(0).getText();
            default:
                return null;
        }
    }

    public boolean resultadosContemString(String palavraPesquisada, @NotNull String local) {
        boolean resultadosOk;
        List<WebElement> listTxt;
        switch (local) {
            case "componente":
                listTxt = pM.getListaNomesComponente();
                break;
            case "modal":
                listTxt = pM.getListaNomesModal();
                break;
            case "sigla":
                listTxt = pM.getListaSigla();
                break;
            default:
                throw new IllegalStateException("Valor inesperado: " + local);
        }
        resultadosOk = listTxt.stream()
                .allMatch(webElement ->
                        webElement.getText().toLowerCase()
                                .contains(palavraPesquisada.toLowerCase()));
        return resultadosOk;
    }
}
