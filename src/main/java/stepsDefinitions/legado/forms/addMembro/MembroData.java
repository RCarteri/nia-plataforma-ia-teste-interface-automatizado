package stepsDefinitions.legado.forms.addMembro;

public class MembroData {
    private final String funcao;
    private final String chave;
    private final String usuario;

    public MembroData(String chave, String funcao, String usuario) {
        this.chave = chave;
        this.funcao = funcao;
        this.usuario = usuario;
    }

    public String getFuncao() {
        return funcao;
    }

    public String getChave() {
        return chave;
    }

    public String getUsuario() {
        return usuario;
    }
}
