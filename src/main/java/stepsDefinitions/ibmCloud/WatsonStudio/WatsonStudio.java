package stepsDefinitions.ibmCloud.WatsonStudio;

import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import pagesObjects.PesquisaSection;
import pagesObjects.ibmCloud.WatsonStudio.WatsonStudioPage;
import support.Utils;

import static org.junit.Assert.assertTrue;

public class WatsonStudio {
    private String sigla;
    private final WatsonStudioPage wSP = new WatsonStudioPage();

    @Quando("^selecionar a sigla \"([^\"]*)\"$")
    public void selecionarASigla(String sigla) {
        this.sigla = sigla;
        wSP.selecionarSigla(sigla);
    }

    @Então("^deverá mostrar a lista de projetos com essa sigla$")
    public void deveraMostrarAListaDeProjetosComEssaSigla() {
        new Utils().capturaTela();
        assertTrue(new PesquisaSection().resultadosContemString(this.sigla, "sigla"));
    }

    @Quando("^atualizar a listagem de projetos$")
    public void atualizarAListagemDeProjetos() {
        wSP.atualizarProjetos();
    }
}
