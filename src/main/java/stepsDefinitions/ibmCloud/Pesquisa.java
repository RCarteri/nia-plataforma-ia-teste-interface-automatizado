package stepsDefinitions.ibmCloud;

import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import pagesObjects.sections.BBCardBodySection;
import pagesObjects.sections.BBCardHeaderSection;
import support.Utils;

import static java.lang.String.valueOf;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Pesquisa extends Utils {
    private final BBCardBodySection cardBS;
    private final BBCardHeaderSection cardHS;
    private String palavraPesquisada;
    private String tagSelecionada;

    public Pesquisa() {
        this.cardBS = new BBCardBodySection();
        this.cardHS = new BBCardHeaderSection();
    }

    @Quando("^pesquisar um dado \"([^\"]*)\"$")
    public void pesquisarUmDado(String dado) {
        cardHS.limparPesquisa();
        this.palavraPesquisada = cardBS.getDadoPesquisa(dado);
        cardHS.pesquisar(palavraPesquisada);
    }

    @Então("^os resultados apresentados devem conter a palavra pesquisada$")
    public void osResultadosApresentadosDevemConterAPalavraPesquisada() {
        try {
            boolean retornoOk = cardBS.resultadosContemString(palavraPesquisada);
            assertTrue("Os resultados apresentados não contem a palavra pesquisada.", retornoOk);
        } finally {
            capturaTela();
        }
    }

    @Então("^a quantidade de resultados deve ser (\\d+)$")
    public void osResultadosDevemSer(String quantResultados) {
        try {
            String quantResultadosObtida = valueOf(cardBS.getQuantResultados());
            assertEquals(printResultadoEsperadoObtido(quantResultados, quantResultadosObtida),
                    quantResultadosObtida, quantResultados);
        } finally {
            capturaTela();
        }
    }

    @Quando("^selecionar uma sigla$")
    public void selecionarUmaSigla() {
        try {
            cardHS.removerTags();
            this.tagSelecionada = cardHS.selecionarTag();
        } catch (Exception e) {
            logError(e);
        }
    }

    @Então("^deverá mostrar somente os projetos com essa sigla$")
    public void deveraMostrarSomenteOsProjetosComEssaSigla() {
        try {
            assertTrue(cardBS.siglasOk(tagSelecionada));
        } finally {
            capturaTela();
        }
    }

    @Quando("^não selecionar uma sigla$")
    public void naoSelecionarUmaSigla() {
        try {
            cardHS.removerTags();
            cardHS.filtrar();
        } catch (Exception e) {
            logError(e);
        }
    }

    @Então("^deverá mostrar todos os projetos, incluindo os sem sigla$")
    public void deveraMostrarTodosOsProjetosIncluindoOsSemSigla() {
        try {
            assertTrue("Os cards sem sigla não estão aparecendo.", cardBS.siglasOk());
        }finally {
            capturaTela();
        }
    }
}