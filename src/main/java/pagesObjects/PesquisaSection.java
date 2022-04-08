package pagesObjects;

import br.com.bb.ath.ftabb.exceptions.ElementoNaoLocalizadoException;
import map.PesquisaMap;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.openqa.selenium.WebElement;
import support.Utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PesquisaSection {
    private final PesquisaMap pM = new PesquisaMap();
    private final Map<String, Boolean> validacaoPesquisa = new HashMap<>();
    private String mensagemPesquisaInvalida = "";

    public void pesquisar(String palavra, @NotNull String local) {
        try {
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
        StringBuilder strBuilder = new StringBuilder(this.mensagemPesquisaInvalida);
        for (Map.Entry<String, Boolean> entry : validacaoPesquisa.entrySet()) {
            if (!entry.getValue()) {
                strBuilder.append(entry.getKey()).append("\n");
            }
        }
        this.mensagemPesquisaInvalida = strBuilder.toString();
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
        boolean resultadosOk = true;
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
                throw new IllegalStateException("Unexpected value: " + local);
        }
        for (WebElement webElement : listTxt) {
            if (!(webElement.getText().toLowerCase().contains(palavraPesquisada.toLowerCase()))) {
                resultadosOk = false;
                break;
            }
        }
        return resultadosOk;
    }
}
