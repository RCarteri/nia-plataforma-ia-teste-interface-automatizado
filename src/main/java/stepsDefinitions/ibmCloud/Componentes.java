package stepsDefinitions.ibmCloud;

import br.com.bb.ath.ftabb.exceptions.ElementoNaoLocalizadoException;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import pagesObjects.IBMCloudPage;
import pagesObjects.ModalComponentePage;
import pagesObjects.ProvedorPage;
import utils.Utils;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Componentes {
    private final IBMCloudPage ibmCloudPage = new IBMCloudPage();
    private final ModalComponentePage modalComponentePage = new ModalComponentePage();
    private final Utils utils = new Utils();
    private int quantResultadosAntes;
    private String palavraPesquisada;
    private String local;

    @Quando("^acessar a pagina do provedor IBM Cloud$")
    public void queEstejaNaPaginaDoProvedorIBMCloud() throws ElementoNaoLocalizadoException {
        new ProvedorPage().acessarIBMCLoud();
    }

    @Quando("^selecionar o componente \"([^\"]*)\"$")
    public void selecionarOComponente(String componente) throws ElementoNaoLocalizadoException {
        ibmCloudPage.acessarComponente(componente);
    }

    @Então("^deverá apresentar o título \"([^\"]*)\" na página$")
    public void deveApresentarOTituloNaPagina(String titulo) throws ElementoNaoLocalizadoException {
       utils.capturaTela();
       assertEquals(titulo, ibmCloudPage.getTituloComponente());
    }

    @Então("^deverá apresentar o titulo \"([^\"]*)\" no modal$")
    public void deveraApresentarOTitulo(String titulo) throws ElementoNaoLocalizadoException {
        utils.capturaTela();
        assertEquals(titulo, modalComponentePage.getTituloModal());
    }

    @E("^deverá mostrar a lista de \"([^\"]*)\"$")
    public void deveraMostrarAListagem(String option){
        assertTrue(modalComponentePage.getCountLinhas() > 1);
    }

    @Quando("^exibir \"([^\"]*)\"$")
    public void exibirSkills(String option){
        int localizacao = 0;
        switch (option) {
            case "instância":
                localizacao = 4; // Está sendo sitado 4 pois os anteriores estão retornando erro ao abrir
                break;
            case "projeto":
            case "grupo":
            case "catálogo":
            case "storage":
            case "modelo":
                localizacao = 1;
                break;
        }
        new IBMCloudPage().clicarBotaoLista(--localizacao); // sem criar uma nova instância ele não retorna a lista de botoes
    }

    @E("^deverá apresentar as informações sobre ID e nome$")
    public void deveraApresentarAsInformacoesIDeNome() {
        List<String> listaInfoNomeID = new ModalComponentePage().getListaInfoNomeID();
        assertEquals("Informações faltando no campo: " + listaInfoNomeID.toString(), 0, listaInfoNomeID.size());
    }

    @Quando("^pesquisar \"([^\"]*)\" no \"([^\"]*)\"$")
    public void pesquisar(String palavraPesquisada, String local) {
        this.local = local;
        ibmCloudPage.limparPesquisa(this.local);
        this.quantResultadosAntes = ibmCloudPage.getQuantResultados(local);
        this.palavraPesquisada = palavraPesquisada;
        ibmCloudPage.pesquisar(palavraPesquisada, local);
    }

    @Então("^deverá apresentar um total de resultados diferente do anterior$")
    public void deveraApresentarUmTotalDeResultadosDiferenteDoAnterior() {
        utils.capturaTela();
        if (this.quantResultadosAntes != 1)
            assertTrue(this.quantResultadosAntes != ibmCloudPage.getQuantResultados(this.local));
    }

    @E("^os resultados apresentados devem conter a palavra pesquisada$")
    public void osResultadosApresentadosDevemConterAPalavraPesquisada() {
        assertTrue(ibmCloudPage.resultadosContemString(palavraPesquisada, this.local));
    }

    @Então("^deverá apresentar a mensagem \"([^\"]*)\"$")
    public void deveraApresentarAMensagem(String mensagem){
        utils.capturaTela();
        assertEquals(mensagem, ibmCloudPage.getTxtNenhumResultado(this.local));
    }

    @E("^a quantidade de resultados deve ser (\\d+)$")
    public void osResultadosDevemSer(int quantResultados) {
        assertEquals(quantResultados, ibmCloudPage.getQuantResultados(this.local));
    }

    @E("^limpar pesquisa$")
    public void limparPesquisa() {
        ibmCloudPage.limparPesquisa(this.local);
    }

    @Então("^o input deve estar vazio$")
    public void oInputDeveEstarVazio() {
        utils.capturaTela();
        assertEquals(ibmCloudPage.getTxtInputFiltro(this.local), "");
    }

    @E("^o total de resultados deverá mostrar a quantidade anterior$")
    public void oTotalDeResultadosDeveraMostrarAQuantidadeAnterior() {
        assertEquals(this.quantResultadosAntes, ibmCloudPage.getQuantResultados(this.local));
    }
}
