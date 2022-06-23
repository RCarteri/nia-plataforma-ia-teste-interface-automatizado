package pagesObjects.sections;

import map.PesquisaMap;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static support.Utils.getRandom;
import static support.Utils.printLog;
import static support.enums.LogTypes.INFO;

public class PesquisaSection {
    private PesquisaMap pM;
    private final Map<String, Boolean> validacaoPesquisa;
    private String mensagemPesquisaInvalida;

    public PesquisaSection() {
        this.mensagemPesquisaInvalida = "";
        this.pM = new PesquisaMap();
        this.validacaoPesquisa = new HashMap<>();
    }

    public void pesquisar(String palavra, String local) {
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

    public String getTxtInputPesquisa(String local) {
        switch (local) {
            case "componente":
                return pM.getInputPesquisa().getText();
            case "modal":
                return pM.getInputPesquisaModal().getText();
        }
        return null;
    }

    public void limparPesquisa(String local) {
        switch (local) {
            case "componente":
                pM.getBtnLimparPesquisa().click();
                break;
            case "modal":
                pM.getBtnLimparPesquisaModal().click();
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

    public String getDadoPesquisa(String local, String dado) {
        switch (dado) {
            case "inválido":
                return "#inválido";
            case "válido":
                return getDadoValido(local);
            default:
                return null;
        }
    }

    private String getDadoValido(String local) {
        switch (local) {
            case "componente":
                return pM.getListaNomesComponente().get(
                        getRandom(pM.getListaNomesComponente().size())
                ).getText();
            case "modal":
                return pM.getListaNomesModal().get(
                        getRandom(pM.getListaNomesModal().size())
                ).getText();
            default:
                return null;
        }
    }

    public boolean resultadosContemString(String palavraPesquisada, String local) {
        boolean resultadosOk;
        List<WebElement> listTxt;
        this.pM = new PesquisaMap();
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
