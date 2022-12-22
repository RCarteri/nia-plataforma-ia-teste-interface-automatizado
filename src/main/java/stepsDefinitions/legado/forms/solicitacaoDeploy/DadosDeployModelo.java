package stepsDefinitions.legado.forms.solicitacaoDeploy;

public class DadosDeployModelo {
    private final String nome;
    private final String instancia;
    private final String notebook;
    private final String dataAsset;

    public DadosDeployModelo(String nome, String instancia, String notebook, String dataAsset) {
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
