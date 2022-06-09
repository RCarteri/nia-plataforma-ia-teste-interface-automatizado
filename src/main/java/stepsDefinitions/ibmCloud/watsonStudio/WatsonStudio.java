package stepsDefinitions.ibmCloud.watsonStudio;

import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import pagesObjects.ibmCloud.WatsonStudio.WatsonStudioPage;
import pagesObjects.sections.PesquisaSection;
import support.Utils;

import static org.junit.Assert.assertTrue;

public class WatsonStudio extends Utils{
    private String sigla;
    private final WatsonStudioPage wSP;

    public WatsonStudio() {
        this.wSP = new WatsonStudioPage();
    }

    @Quando("^selecionar uma sigla$")
    public void selecionarUmaSigla() {
        try {
            this.sigla = wSP.selecionarSigla();
        } catch (Exception e) {
            logError(e);
        }
    }

    @Então("^deverá mostrar a lista de projetos com essa sigla$")
    public void deveraMostrarAListaDeProjetosComEssaSigla() {
        try {
            assertTrue(new PesquisaSection().resultadosContemString(this.sigla, "sigla"));
        } finally {
            capturaTela();
        }
    }

    @Quando("^atualizar a listagem de projetos$")
    public void atualizarAListagemDeProjetos() {
        try {
            wSP.atualizarProjetos();
        } catch (Exception e) {
            logError(e);
        }
    }
}
