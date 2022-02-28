package stepsDefinitions.ibmCloud.WatsonStudio;

import br.com.bb.ath.ftabb.exceptions.ElementoNaoLocalizadoException;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import org.junit.Assert;
import pagesObjects.WatsonStudio.WatsonStudioPage;

public class WatsonStudio {
    private String sigla;
    WatsonStudioPage wSP = new WatsonStudioPage();

    @E("^escolher \"([^\"]*)\"$")
    public void escolher(String opcao){
        wSP.clicarBotaoOpcao(opcao);
    }

    @Quando("^selecionar a sigla \"([^\"]*)\"$")
    public void selecionarASigla(String sigla) throws ElementoNaoLocalizadoException {
        this.sigla = sigla;
        wSP.selecionarSigla(sigla);
    }

    @Então("^deverá mostrar a lista de projetos com essa sigla$")
    public void deveraMostrarAListaDeProjetosComEssaSigla() {
        Assert.assertTrue(wSP.resultadosContemSigla(sigla));
    }
}
