package stepsDefinitions.ibmCloud;

import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import pagesObjects.PaginacaoSection;
import pagesObjects.PanelContentSection;
import pagesObjects.PesquisaSection;
import utils.Utils;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Pesquisa {
    private final PesquisaSection pqS = new PesquisaSection();
    private final PaginacaoSection pS = new PaginacaoSection();
    private final PanelContentSection pCS = new PanelContentSection();
    private final Utils utils = new Utils();
    private int quantResultadosAntes;
    private String palavraPesquisada;
    private String local;

    @Quando("^pesquisar \"([^\"]*)\" no \"([^\"]*)\"$")
    public void pesquisar(String palavraPesquisada, String local) {
        this.local = local;
        pqS.limparPesquisa(this.local);
        this.quantResultadosAntes = pS.getQuantResultados(local);
        this.palavraPesquisada = palavraPesquisada;
        pqS.pesquisar(palavraPesquisada, local);
    }

    @Então("^deverá apresentar um total de resultados diferente do anterior$")
    public void deveraApresentarUmTotalDeResultadosDiferenteDoAnterior() {
        utils.capturaTela();
        if (this.quantResultadosAntes != 1)
            assertTrue(this.quantResultadosAntes != pS.getQuantResultados(this.local));
    }

    @E("^os resultados apresentados devem conter a palavra pesquisada$")
    public void osResultadosApresentadosDevemConterAPalavraPesquisada() {
        assertTrue(pCS.resultadosContemString(palavraPesquisada, this.local));
    }

    @E("^a quantidade de resultados deve ser (\\d+)$")
    public void osResultadosDevemSer(int quantResultados) {
        assertEquals(quantResultados, pS.getQuantResultados(this.local));
    }

    @E("^limpar pesquisa$")
    public void limparPesquisa() {
        pqS.limparPesquisa(this.local);
    }

    @Então("^o input deve estar vazio$")
    public void oInputDeveEstarVazio() {
        utils.capturaTela();
        assertEquals(pqS.getTxtInputFiltro(this.local), "");
    }

    @E("^o total de resultados deverá mostrar a quantidade anterior$")
    public void oTotalDeResultadosDeveraMostrarAQuantidadeAnterior() {
        assertEquals(this.quantResultadosAntes, pS.getQuantResultados(this.local));
    }

    @Então("^deverá apresentar a mensagem \"([^\"]*)\"$")
    public void deveraApresentarAMensagem(String mensagem){
        utils.capturaTela();
        assertEquals(mensagem, pCS.getTxtNenhumResultado(this.local));
    }
}
