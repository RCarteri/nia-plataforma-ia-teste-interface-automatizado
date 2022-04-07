package stepsDefinitions.ibmCloud.WatsonStudio;

import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import pagesObjects.PesquisaSection;
import pagesObjects.ibmCloud.WatsonStudio.WatsonStudioPage;
import utils.Utils;

import static org.junit.Assert.assertTrue;

public class WatsonStudio {
    private String sigla;
    WatsonStudioPage wSP = new WatsonStudioPage();
    Utils utils = new Utils();

    @Quando("^selecionar a sigla \"([^\"]*)\"$")
    public void selecionarASigla(String sigla) {
        this.sigla = sigla;
        wSP.selecionarSigla(sigla);
    }

    @Então("^deverá mostrar a lista de projetos com essa sigla$")
    public void deveraMostrarAListaDeProjetosComEssaSigla() {
        utils.capturaTela();
        assertTrue(new PesquisaSection().resultadosContemString(this.sigla, "sigla"));
    }

    @Quando("^atualizar a listagem de projetos$")
    public void atualizarAListagemDeProjetos() {
        wSP.atualizarProjetos();
    }
}
