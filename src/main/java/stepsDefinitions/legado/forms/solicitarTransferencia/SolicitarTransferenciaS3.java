package stepsDefinitions.legado.forms.solicitarTransferencia;

import cucumber.api.DataTable;
import cucumber.api.java.pt.Então;
import cucumber.api.java.pt.Quando;
import pagesObjects.legado.MensagemErro;
import pagesObjects.legado.ibmCloud.WatsonStudio.TransferirS3Page;
import support.Utils;

public class SolicitarTransferenciaS3 extends Utils {
    private final TransferirS3Page tS3P;

    public SolicitarTransferenciaS3() {
        this.tS3P = new TransferirS3Page();
    }

    @Quando("^solicitar a transferencia de arquivos S3$")
    public void solicitarATransferenciaDeArquivosS(DataTable table) {
        tS3P.fillListSolicitacao(table);
    }

    @Então("^deverá apresentar a mensagem de erro de busca$")
    public void deveraApresentarAMensagemDeErroDeBusca(DataTable table) {
        MensagemErro mE = new MensagemErro();
        mE.fillListMensagem(table);
        try {
            tS3P.fillForm(mE);
        } catch (Exception e) {
            logError(e);
        }
    }
}