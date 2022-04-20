package pagesObjects;

import br.com.bb.ath.ftabb.Pagina;
import br.com.bb.ath.ftabb.exceptions.ElementoNaoLocalizadoException;
import map.PesquisaMap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.openqa.selenium.WebElement;
import support.Utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PesquisaSection extends Pagina {
    private final PesquisaMap pM = new PesquisaMap();
    private final Map<String, Boolean> validacaoPesquisa = new HashMap<>();
    private String mensagemPesquisaInvalida = "";

    public void pesquisar(String palavra, @NotNull String local) {
        try {
            System.out.println("Pesquisando por: '" + palavra + "' no " + local);
            switch (local) {
                case "componente":
                    pM.getInputPesquisa().escrever(palavra);
                    break;
                case "modal":
                    pM.getInputPesquisaModal().escrever(palavra);
                    break;
            }
        } catch (ElementoNaoLocalizadoException e) {
            Utils.logError(e);
        }
    }

    public String getTxtInputFiltro(@NotNull String local) {
        try {
            switch (local) {
                case "componente":
                    return pM.getInputPesquisa().recuperarTexto();
                case "modal":
                    return pM.getInputPesquisaModal().recuperarTexto();
            }
        } catch (ElementoNaoLocalizadoException e) {
            Utils.logError(e);
        }
        return null;
    }

    public void limparPesquisa(@NotNull String local) {
        try {
            switch (local) {
                case "componente":
                    pM.getBtnLimparPesquisa().clicar();
                    break;
                case "modal":
                    pM.getBtnLimparFiltroPesquisa().clicar();
                    break;
            }
        } catch (ElementoNaoLocalizadoException e) {
            Utils.logError(e);
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
