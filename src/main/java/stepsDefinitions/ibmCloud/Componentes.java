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
        new IBMCloud().acessarComponente(componente);
    }

    @Então("^deve apresentar o título \"([^\"]*)\"$")
    public void deveApresentarOTitulo(String titulo) throws ElementoNaoLocalizadoException {
        utils.capturaTela();
        assertEquals(titulo, ibmCloud.getTituloComponente());
    }

    @Então("^deverá apresentar o titulo \"([^\"]*)\" no modal$")
    public void deveraApresentarOTitulo(String titulo) throws ElementoNaoLocalizadoException {
        utils.capturaTela();
        assertEquals(titulo, modalComponente.getTituloModal());
    }

    @E("^deverá mostrar a lista do \"([^\"]*)\"$")
    public void deveraMostrarAListaDe(String option){
        utils.capturaTela();
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
    public void deveraApresentarAsInformaçõesSobreO() {
        ModalComponente modalComponente = new ModalComponente();
        List<String> listaInfoNomeID = modalComponente.getListaInfoNomeID();
        utils.capturaTela();
        assertEquals("Informações faltando no campo: " + listaInfoNomeID.toString(), 0, listaInfoNomeID.size());
    }
}
