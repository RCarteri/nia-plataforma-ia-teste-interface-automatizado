package stepsDefinitions.ibmCloud.WatsonStudio;

import br.com.bb.ath.ftabb.exceptions.ElementoNaoLocalizadoException;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import pagesObjects.IBMCloudPage;
import pagesObjects.WatsonStudio.WatsonStudioPage;
import utils.Utils;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class WatsonStudio {
    private String sigla;
    WatsonStudioPage wSP = new WatsonStudioPage();
    Utils utils = new Utils();

    @Quando("^escolher \"([^\"]*)\"$")
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

    @Quando("^não existir notebook$")
    public void naoExistirNotebook() {
        assertFalse("Todos os projetos possuem notebooks.\nNão foi possível realizar este teste.",
                wSP.existeNotebook(false));
    }

    @Quando("^existir notebook$")
    public void existirNotebook() {
        assertTrue("Nenhum projeto possui notebook.\nNão foi possível realizar este teste.",
                wSP.existeNotebook(true));
    }
}
