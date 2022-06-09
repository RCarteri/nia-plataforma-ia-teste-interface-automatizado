package support.APIRest;

public enum Path {
    GERAR_RELATORIO("/generate-report"),
    LIMPAR_HISTORICO("/clean-history"),
    LIMPAR_RESULTADOS("/clean-results");

    private final String path;

    Path(String path) {
        this.path = path;
    }

    public String getSubDiretorio(){
        return path;
    }
}
