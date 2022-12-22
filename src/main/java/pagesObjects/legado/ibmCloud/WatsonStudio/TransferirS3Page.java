package pagesObjects.legado.ibmCloud.WatsonStudio;

import cucumber.api.DataTable;
import map.legado.TransferirS3Map;
import pagesObjects.legado.MensagemErro;
import stepsDefinitions.legado.forms.solicitarTransferencia.DadosTransferenciaS3;
import support.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static support.Utils.assertBtnDisabled;

public class TransferirS3Page extends TransferirS3Map {
    private final List<DadosTransferenciaS3> dadosTransferenciaS3 = new ArrayList<>();

    public void addDados(DadosTransferenciaS3 dados) {
        dadosTransferenciaS3.add(dados);
    }

    public void fillListSolicitacao(DataTable table) {
        List<Map<String, String>> linhas = table.asMaps(String.class, String.class);
        for (Map<String, String> colunas : linhas) {
            addDados(new DadosTransferenciaS3(colunas.get("Nome do Volume"), colunas.get("Código de Acesso"), colunas.get("Código de segurança")));
        }
    }

    public void fillForm(MensagemErro mE) {
        assertBtnDisabled(getBtnBuscar(), getBtnTransferir());
        for (DadosTransferenciaS3 dadoTransferenciaS3 : dadosTransferenciaS3) {
            preencherCampos(dadoTransferenciaS3);
            mE.isMensagemEsperada(dadosTransferenciaS3.indexOf(dadoTransferenciaS3));
            assertBtnDisabled(getBtnBuscar(), getBtnTransferir());
            new Utils().capturaTela();
        }
    }

    private void preencherCampos(DadosTransferenciaS3 dadoTransferenciaS3) {
        getInputNomeVolume().clear();
        if (dadoTransferenciaS3.getNomeVolume().equals(""))
            getInputNomeVolume().click();
        else
            getInputNomeVolume().sendKeys(dadoTransferenciaS3.getNomeVolume());
        if (dadoTransferenciaS3.getCodAcesso().equals(""))
            getInputCodigoAcesso().click();
        else
            getInputCodigoAcesso().sendKeys(dadoTransferenciaS3.getCodAcesso());
        if (dadoTransferenciaS3.getCodSeguranca().equals(""))
            getInputCodigoSeguranca().click();
        else
            getInputCodigoSeguranca().sendKeys(dadoTransferenciaS3.getCodSeguranca());
        getInputNomeVolume().click();
    }
}