package stepsDefinitions.ibmCloud.addMembro;

public class Membro {
    private final String funcao;
    private final String chave;

    public Membro(String chave, String funcao) {
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
