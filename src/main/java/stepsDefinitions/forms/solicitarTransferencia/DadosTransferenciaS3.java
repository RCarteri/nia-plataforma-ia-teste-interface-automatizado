package stepsDefinitions.forms.solicitarTransferencia;

public class DadosTransferenciaS3 {
    private final String nomeVolume;
    private final String codAcesso;
    private final String codSeguranca;

    public DadosTransferenciaS3(String nomeVolume, String codAcesso, String codSeguranca) {
        this.nomeVolume = nomeVolume;
        this.codAcesso = codAcesso;
        this.codSeguranca = codSeguranca;
    }

    public String getNomeVolume() {
        return nomeVolume;
    }

    public String getCodAcesso() {
        return codAcesso;
    }

    public String getCodSeguranca() {
        return codSeguranca;
    }
}
