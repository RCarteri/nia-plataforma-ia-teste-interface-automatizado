package stepsDefinitions.ibmCloud;

import br.com.bb.ath.ftabb.exceptions.ElementoNaoLocalizadoException;
import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import pagesObjects.IBMCloud;
import pagesObjects.ModalComponente;
import pagesObjects.Provedor;
import utils.Utils;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class Componentes {
    private final IBMCloud ibmCloud = new IBMCloud();
    private final ModalComponente modalComponente = new ModalComponente();
    private final Utils utils = new Utils();

    @Quando("^acessar a pagina do provedor IBM Cloud$")
    public void queEstejaNaPaginaDoProvedorIBMCloud() throws ElementoNaoLocalizadoException {
        new Provedor().acessarIBMCLoud();
    }

    @Quando("^selecionar o componente \"([^\"]*)\"$")
    public void selecionarOComponente(String componente) throws ElementoNaoLocalizadoException {
        ibmCloud.acessarComponente(componente);
    }

    @Então("^deverá apresentar o título \"([^\"]*)\" na página$")
    public void deveApresentarOTituloNaPagina(String titulo) throws ElementoNaoLocalizadoException {
        assertEquals(titulo, ibmCloud.getTituloComponente());
    }

    @Então("^deverá apresentar o titulo \"([^\"]*)\" no modal$")
    public void deveraApresentarOTitulo(String titulo) throws ElementoNaoLocalizadoException {
        assertEquals(titulo, modalComponente.getTituloModal());
    }

    @E("^deverá mostrar a lista do \"([^\"]*)\"$")
    public void deveraMostrarAListagem(String option){
        assertTrue(modalComponente.getCountLinhas() > 2);
    }

    @Quando("^exibir \"([^\"]*)\"$")
    public void exibirSkills(String option){
        int localizacao = 0;
        switch (option) {
            case "skill":
                localizacao = 4; // Está sendo sitado 4 pois os anteriores estão retornando erro ao abrir
                break;
            case "membro":
            case "storage":
            case "modelo":
            case "grupo":
                localizacao = 1;
                break;
        }
        new IBMCloud().clicarBotaoLista(localizacao); // sem criar uma nova instância ele não retorna a lista de botoes
    }

    @E("^deverá apresentar as informações sobre ID e nome$")
    public void deveraApresentarAsInformacoesIDeNome() {
        List<String> listaInfoNomeID = new ModalComponente().getListaInfoNomeID();
        assertEquals("Informações faltando no campo: " + listaInfoNomeID.toString(), 0, listaInfoNomeID.size());
    }
}
