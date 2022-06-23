package stepsDefinitions.ibmCloud;

import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Então;
import pagesObjects.sections.PaginacaoSection;
import pagesObjects.sections.PanelContentSection;
import pagesObjects.sections.PesquisaSection;
import support.Utils;

import static org.junit.Assert.assertTrue;

public class Pesquisa extends Utils{
    private final PesquisaSection pqS;
    private final PaginacaoSection pS;
    private final PanelContentSection pCS;
    private String quantResultadosAntes;
    private String palavraPesquisada;
    private String local;
    private Boolean validacao;
    private Boolean resultadosSoContemPalavraPesquisada = false;

    public Pesquisa() {
        this.pqS = new PesquisaSection();
        this.pS = new PaginacaoSection();
        this.pCS = new PanelContentSection();
    }

    @Então("^deverá apresentar um total de resultados diferente do anterior$")
    public void deveraApresentarUmTotalDeResultadosDiferenteDoAnterior() {
        try {
            String quantResultadosObtida = (resultadosSoContemPalavraPesquisada) ? "-1" : pS.getQuantResultados(this.local);
            this.validacao = !quantResultadosAntes.equals(quantResultadosObtida);
            pqS.validarPesquisa(printResultadoEsperadoObtido(this.quantResultadosAntes, quantResultadosObtida), validacao);
        } finally {
            capturaTela();
        }
    }

    @E("^os resultados apresentados devem conter a palavra pesquisada$")
    public void osResultadosApresentadosDevemConterAPalavraPesquisada() {
        try {
            this.validacao = pqS.resultadosContemString(palavraPesquisada, this.local);
            pqS.validarPesquisa("Os resultados apresentados não contem a palavra pesquisada.", validacao);
        } catch (Exception e) {
            logError(e);
        }
    }

    @E("^a quantidade de resultados deve ser (\\d+)$")
    public void osResultadosDevemSer(String quantResultados) {
        try {
            String quantResultadosObtida = pS.getQuantResultados(this.local);
            this.validacao = quantResultados.equals(quantResultadosObtida);
            pqS.validarPesquisa(printResultadoEsperadoObtido(quantResultados, quantResultadosObtida), validacao);
        } catch (Exception e) {
            logError(e);
        }
    }

    @E("^limpar pesquisa$")
    public void limparPesquisa() {
        try {
            pqS.limparPesquisa(this.local);
        } catch (Exception e) {
            logError(e);
        }
    }

    @Então("^o input deve estar vazio$")
    public void oInputDeveEstarVazio() {
        try {
            this.validacao = pqS.getTxtInputPesquisa(this.local).equals("");
            pqS.validarPesquisa("O input não está vazio.", validacao);
        } finally {
            capturaTela();
        }
    }

    @E("^o total de resultados deverá mostrar a quantidade anterior$")
    public void oTotalDeResultadosDeveraMostrarAQuantidadeAnterior() {
        try {
            String quantResultadosObtida = pS.getQuantResultados(this.local);
            this.validacao = this.quantResultadosAntes.equals(quantResultadosObtida);
            pqS.validarPesquisa(printResultadoEsperadoObtido(this.quantResultadosAntes, quantResultadosObtida), validacao);
        } catch (Exception e) {
            logError(e);
        }
    }

    @Então("^deverá apresentar a mensagem \"([^\"]*)\"$")
    public void deveraApresentarAMensagem(String mensagem) {
        try {
            String mensagemObtida = pCS.getTxtNenhumResultado(this.local);
            this.validacao = mensagem.equals(mensagemObtida);
            pqS.validarPesquisa(printResultadoEsperadoObtido(mensagem, mensagemObtida), validacao);
        } finally {
            capturaTela();
        }
    }

    @E("^todas as validações devem retornar sucesso$")
    public void todasAsValidacoesDevemRetornarSucesso() {
        try {
            boolean validacao = pqS.getValidacaoPesquisa();
            assertTrue(pqS.getMensagemPesquisaInvalida(), validacao);
        } catch (Exception e) {
            logError(e);
        }
    }

    @E("^pesquisar um dado \"([^\"]*)\" no \"([^\"]*)\" \"([^\"]*)\"$")
    public void pesquisarUmDado(String dado, String local, String componente) {
        try {
            if (local.equals("modal") && !componente.equals("")) new Componente().existirOpcao(componente);
            this.local = local;
            pqS.limparPesquisa(this.local);
            this.palavraPesquisada = pqS.getDadoPesquisa(this.local, dado);
            if (pqS.resultadosContemString(palavraPesquisada, this.local)) this.resultadosSoContemPalavraPesquisada = true;
            this.quantResultadosAntes = pS.getQuantResultados(local);
            pqS.pesquisar(palavraPesquisada, local);
        } catch (Exception e) {
            logError(e);
        }
    }
}