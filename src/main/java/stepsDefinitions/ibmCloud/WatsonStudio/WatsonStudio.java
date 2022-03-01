package stepsDefinitions.ibmCloud.WatsonStudio;

import br.com.bb.ath.ftabb.exceptions.ElementoNaoLocalizadoException;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Mas;
import cucumber.api.java.pt.Quando;
import pagesObjects.IBMCloudPage;
import pagesObjects.WatsonStudio.WatsonStudioPage;
import utils.Utils;

import static org.junit.Assert.assertTrue;

public class WatsonStudio {
    private String sigla;
    WatsonStudioPage wSP = new WatsonStudioPage();
    Utils utils = new Utils();

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
        utils.capturaTela();
        assertTrue(new IBMCloudPage().resultadosContemString(this.sigla, "sigla"));
    }

    @Quando("^atualizar a listagem de projetos$")
    public void atualizarAListagemDeProjetos() {
        wSP.atualizarProjetos();
    }

    @Mas("^não existir notebook$")
    public void naoExistirNotebook() {
        assertTrue("Todos os projetos possuem notebooks.\nNão foi possível realizar este teste.",
                wSP.existeNotebook());
    }
}
