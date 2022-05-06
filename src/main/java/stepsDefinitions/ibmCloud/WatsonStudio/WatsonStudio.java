package stepsDefinitions.ibmCloud.WatsonStudio;

import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import pagesObjects.ibmCloud.WatsonStudio.WatsonStudioPage;
import pagesObjects.sections.PesquisaSection;
import support.Utils;

import static org.junit.Assert.assertTrue;

public class WatsonStudio {
    private String sigla;
    private final WatsonStudioPage wSP;
    private final Utils utils;

    public WatsonStudio() {
        this.wSP = new WatsonStudioPage();
        this.utils  = new Utils();
    }

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
