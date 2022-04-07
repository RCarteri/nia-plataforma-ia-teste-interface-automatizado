package pagesObjects;

import br.com.bb.ath.ftabb.Pagina;
import br.com.bb.ath.ftabb.anotacoes.MapearElementoWeb;
import br.com.bb.ath.ftabb.elementos.ElementoBotao;
import br.com.bb.ath.ftabb.elementos.ElementoInput;
import br.com.bb.ath.ftabb.exceptions.ElementoNaoLocalizadoException;
import org.jetbrains.annotations.NotNull;
import org.openqa.selenium.WebElement;
import utils.Utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static utils.Utils.getElements;

public class PesquisaSection extends Pagina {

    @MapearElementoWeb(css = "nia-platia-table th input.p-inputtext")
    private ElementoInput inputPesquisa;

    @MapearElementoWeb(css = "nia-membros-table thead .p-inputtext")
    private ElementoInput inputPesquisaModal;

    @MapearElementoWeb(css = "nia-platia-table .deleteicon span")
    private ElementoBotao btnLimparPesquisa;

    @MapearElementoWeb(css = "nia-membros-table .deleteicon span")
    private ElementoBotao btnLimparFiltroPesquisa;

    private final Map<String, Boolean> validacaoPesquisa = new HashMap<>();
    private String mensagemPesquisaInvalida = "";

    public void pesquisar(String palavra, String local) {
        try {
            switch (local) {
                case "componente":
                    inputPesquisa.escrever(palavra);
                    break;
                case "modal":
                    inputPesquisaModal.escrever(palavra);
                    break;
            }
        } catch (ElementoNaoLocalizadoException e) {
            Utils.logError(e);
        }
    }

    public String getTxtInputFiltro(String local) {
        try {
            switch (local) {
                case "componente":
                    return inputPesquisa.recuperarTexto();
                case "modal":
                    return inputPesquisaModal.recuperarTexto();
            }
        } catch (ElementoNaoLocalizadoException e) {
            Utils.logError(e);
        }
        return null;
    }

    public void limparPesquisa(String local) {
        try {
            switch (local) {
                case "componente":
                    btnLimparPesquisa.clicar();
                    break;
                case "modal":
                    btnLimparFiltroPesquisa.clicar();
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
                return getListaNomesComponente().get(0).getText();
            case "modal":
                return getListaNomesModal().get(0).getText();
            default:
                return null;
        }
    }

    private List<WebElement> getListaNomesComponente() {
        return getElements("nia-platia-table td:first-child");
    }

    private List<WebElement> getListaNomesModal() {
        return getElements("nia-membros-table td:nth-child(2)");
    }

    private List<WebElement> getListaSigla() {
        return getElements(".p-datatable-tbody td:nth-child(2)");
    }

    public boolean resultadosContemString(String palavraPesquisada, @NotNull String local) {
        boolean resultadosOk = true;
        List<WebElement> listTxt;
        switch (local) {
            case "componente":
                listTxt = getListaNomesComponente();
                break;
            case "modal":
                listTxt = getListaNomesModal();
                break;
            case "sigla":
                listTxt = getListaSigla();
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
