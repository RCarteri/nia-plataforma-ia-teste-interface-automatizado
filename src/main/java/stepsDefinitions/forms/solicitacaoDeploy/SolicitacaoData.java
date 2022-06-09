package stepsDefinitions.forms.solicitacaoDeploy;

public class SolicitacaoData {
    private final String nome;
    private final String instancia;
    private final String notebook;
    private final String dataAsset;

    public SolicitacaoData(String nome, String instancia, String notebook, String dataAsset) {
        this.nome = nome;
        this.instancia = instancia;
        this.notebook = notebook;
        this.dataAsset = dataAsset;
    }

    public String getNome() {
        return nome;
    }

    public String getInstancia() {
        return instancia;
    }

    public String getNotebook() {
        return notebook;
    }

    public String getDataAsset() {
        return dataAsset;
    }
}
