package stepsDefinitions.ibmCloud;

import cucumber.api.java.pt.Dado;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import pagesObjects.sections.BBCardBodySection;
import pagesObjects.sections.BBCardHeaderSection;
import support.Utils;

import static java.lang.String.valueOf;
import static org.junit.Assert.*;
import static support.enums.LogTypes.INFO;

public class Pesquisa extends Utils {
    private final BBCardBodySection cardBS;
    private final BBCardHeaderSection cardHS;
    private String palavraPesquisada;
    private String siglaSelecionada;
    private int totalResultados;

    public Pesquisa() {
        this.cardBS = new BBCardBodySection();
        this.cardHS = new BBCardHeaderSection();
    }

    public void setTotalResultados() {
        this.totalResultados = cardHS.getTotalResultados();
    }

    @Quando("^pesquisar um dado \"([^\"]*)\"$")
    public void pesquisarUmDado(String dado) {
        cardHS.limparPesquisa();
        this.palavraPesquisada = cardBS.getDadoPesquisa(dado);
        cardHS.pesquisar(palavraPesquisada);
        printLog("Dado pesquisado: " + palavraPesquisada, INFO);
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
            String quantResultadosObtida = valueOf(cardHS.getTotalResultados());
            assertEquals(printResultadoEsperadoObtido(quantResultados, quantResultadosObtida),
                    quantResultadosObtida, quantResultados);
        } finally {
            capturaTela();
        }
    }

    @Quando("^selecionar uma sigla$")
    public void selecionarUmaSigla() {
        try {
            cardHS.removerSigla();
            this.siglaSelecionada = cardHS.selecionarSigla();
            cardHS.filtrar();
        } catch (Exception e) {
            logError(e);
        }
    }

    @Então("^deverá mostrar somente os projetos com essa sigla$")
    public void deveraMostrarSomenteOsProjetosComEssaSigla() {
        try {
            assertTrue(cardBS.siglasOk(siglaSelecionada));
        } finally {
            capturaTela();
        }
    }

    @Quando("^não selecionar uma sigla$")
    public void naoSelecionarUmaSigla() {
        try {
            cardHS.removerSigla();
            cardHS.filtrar();
        } catch (Exception e) {
            logError(e);
        }
    }

    @Então("^deverá mostrar todos os projetos, incluindo os sem sigla$")
    public void deveraMostrarTodosOsProjetosIncluindoOsSemSigla() {
        try {
            assertTrue("Os cards sem sigla não estão aparecendo.", cardBS.siglasOk());
        } finally {
            capturaTela();
        }
    }

    @Dado("^que tenha pesquisado por um projeto$")
    public void queTenhaPesquisadoPorUmProjeto() {
        if (!cardHS.isPesquisaPreenchida())
            pesquisarUmDado("válido");
    }

    @E("^que tenha pelo menos uma sigla no filtro de siglas$")
    public void queTenhaPeloMenosUmaSiglaNoFiltroDeSiglas() {
        if (!cardHS.isSiglaSelecionada())
            cardHS.selecionarSigla();
        setTotalResultados();
    }

    @Quando("^limpar os filtros$")
    public void limparOsFiltros() {
        cardHS.limparFiltro();
        capturaTela();
    }

    @Então("^o campo de pesquisa deve estar vazio$")
    public void oCampoDePesquisaDeveEstarVazio() {
        assertFalse("Campo de pesquisa não está vazio", cardHS.isPesquisaPreenchida());
    }

    @E("^não deve ter nenhuma sigla selecionada$")
    public void naoDeveTerNenhumaSiglaSelecionada() {
        assertFalse("Há siglas selecionadas no filtro", cardHS.isSiglaSelecionada());
    }

    @E("^a quantidade de projetos apresentados deve ser alterada$")
    public void aQuantidadeDeProjetosApresentadosDeveSerAlterada() {
        assertTrue("O campo de total de resultados mostrados não foi alterado.", cardHS.getTotalResultados() != totalResultados);
    }
}