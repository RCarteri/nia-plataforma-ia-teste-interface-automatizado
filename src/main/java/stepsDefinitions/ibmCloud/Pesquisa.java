package stepsDefinitions.ibmCloud;

import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import pagesObjects.sections.PaginacaoSection;
import pagesObjects.sections.PanelContentSection;
import pagesObjects.sections.PesquisaSection;
import support.Utils;

import static org.junit.Assert.assertTrue;
import static support.Utils.printResultadoEsperadoObtido;

public class Pesquisa {
    private final PesquisaSection pqS;
    private final PaginacaoSection pS;
    private final PanelContentSection pCS;
    private final Utils utils;
    private String quantResultadosAntes;
    private String palavraPesquisada;
    private String local;
    private Boolean validacao;

    public Pesquisa() {
        this.pqS = new PesquisaSection();
        this.pS = new PaginacaoSection();
        this.pCS = new PanelContentSection();
        this.utils = new Utils();
    }

    @Quando("^pesquisar um dado \"([^\"]*)\" no \"([^\"]*)\"$")
    public void pesquisar(String dado, String local) {
        this.local = local;
        pqS.limparPesquisa(this.local);
        this.quantResultadosAntes = pS.getQuantResultados(local);
        this.palavraPesquisada = pqS.getDadoPesquisa(this.local, dado);
        pqS.pesquisar(palavraPesquisada, local);
    }

    @Então("^deverá apresentar um total de resultados diferente do anterior$")
    public void deveraApresentarUmTotalDeResultadosDiferenteDoAnterior() {
        utils.capturaTela();
        String quantResultadosObtida = pS.getQuantResultados(this.local);
        //Teste para quando desde o início retornar só um resultado na lista
        String quantResultadosAntes = (this.quantResultadosAntes.equals("1")) ? "0" : this.quantResultadosAntes;
        this.validacao = !quantResultadosAntes.equals(quantResultadosObtida);
        pqS.validarPesquisa(printResultadoEsperadoObtido(this.quantResultadosAntes, quantResultadosObtida), validacao);
    }

    @E("^os resultados apresentados devem conter a palavra pesquisada$")
    public void osResultadosApresentadosDevemConterAPalavraPesquisada() {
        this.validacao = pqS.resultadosContemString(palavraPesquisada, this.local);
        pqS.validarPesquisa("Os resultados apresentados não contem a palavra pesquisada." , validacao);
    }

    @E("^a quantidade de resultados deve ser (\\d+)$")
    public void osResultadosDevemSer(String quantResultados) {
        String quantResultadosObtida = pS.getQuantResultados(this.local);
        this.validacao = quantResultados.equals(quantResultadosObtida);
        pqS.validarPesquisa(printResultadoEsperadoObtido(quantResultados, quantResultadosObtida), validacao);
    }

    @E("^limpar pesquisa$")
    public void limparPesquisa() {
        pqS.limparPesquisa(this.local);
    }

    @Então("^o input deve estar vazio$")
    public void oInputDeveEstarVazio() {
        utils.capturaTela();
        this.validacao = pqS.getTxtInputFiltro(this.local).equals("");
        pqS.validarPesquisa("O input não está vazio.", validacao);
    }

    @E("^o total de resultados deverá mostrar a quantidade anterior$")
    public void oTotalDeResultadosDeveraMostrarAQuantidadeAnterior() {
        String quantResultadosObtida = pS.getQuantResultados(this.local);
        this.validacao = this.quantResultadosAntes.equals(quantResultadosObtida);
        pqS.validarPesquisa(printResultadoEsperadoObtido(this.quantResultadosAntes, quantResultadosObtida), validacao);
    }

    @Então("^deverá apresentar a mensagem \"([^\"]*)\"$")
    public void deveraApresentarAMensagem(String mensagem){
        utils.capturaTela();
        String mensagemObtida = pCS.getTxtNenhumResultado(this.local);
        this.validacao = mensagem.equals(mensagemObtida);
        pqS.validarPesquisa(printResultadoEsperadoObtido(mensagem, mensagemObtida), validacao);
    }

    @E("^todas as validações devem retornar sucesso$")
    public void todasAsValidacoesDevemRetornarSucesso() {
        boolean validacao = pqS.getValidacaoPesquisa();
        assertTrue(pqS.getMensagemPesquisaInvalida(), validacao);
    }

    @E("^pesquisar um dado \"([^\"]*)\" no \"([^\"]*)\" \"([^\"]*)\"$")
    public void pesquisarUmDado(String dado, String local, String componente) {
        if (local.equals("modal") && !componente.equals("")) new Componente().existirOpcao(componente);
        this.local = local;
        pqS.limparPesquisa(this.local);
        this.quantResultadosAntes = pS.getQuantResultados(local);
        this.palavraPesquisada = pqS.getDadoPesquisa(this.local, dado);
        pqS.pesquisar(palavraPesquisada, local);
    }
}
