package pagesObjects;

import cucumber.api.DataTable;
import map.ModalAdicionarMembroMap;
import stepsDefinitions.ibmCloud.Mensagem;
import support.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.assertTrue;

public class MensagemErro {
    private final List<Mensagem> mensagens = new ArrayList<>();

    public void addMensagem(Mensagem mensagem) {
        mensagens.add(mensagem);
    }

    public void fillListMensagem(DataTable table) {
        List<String> linhas = table.asList(String.class);
        for (String linha : linhas) {
            addMensagem(new Mensagem(linha));
        }
    }

    public void isMensagemEsperadaInvalid(int index) {
        String mensagemEsperada = mensagens.get(index).getMensagem();
        assertTrue(Utils.printResultadoEsperadoObtido(mensagemEsperada, getMensagemInvalid()),
                getMensagemInvalid().contains(mensagemEsperada));
    }

    public void isMensagemEsperadaErro(int index) {
        String mensagemEsperada = mensagens.get(index).getMensagem();
        assertTrue(Utils.printResultadoEsperadoObtido(mensagemEsperada, getMensagemErro()),
                getMensagemErro().contains(mensagemEsperada));
    }

    private String getMensagemInvalid() {
        return new ModalAdicionarMembroMap().getListSmallMsg().stream()
                .map(webElement -> webElement.getText().replaceAll("\\n", ""))
                .collect(Collectors.joining());
    }

    private String getMensagemErro() {
        return new ModalAdicionarMembroMap().getListSmallMsgError().stream()
                .map(webElement -> webElement.getText().replaceAll("\\n", ""))
                .collect(Collectors.joining());
    }
}
