package stepsDefinitions.forms.addMembro;

public class MembroData {
    private final String funcao;
    private final String chave;

    public MembroData(String chave, String funcao) {
        this.chave = chave;
        this.funcao = funcao;
    }

    public String getFuncao() {
        return funcao;
    }

    public String getChave() {
        return chave;
    }
}
