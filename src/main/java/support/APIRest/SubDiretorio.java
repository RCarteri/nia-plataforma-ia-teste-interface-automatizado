package support.APIRest;

public enum SubDiretorio {
    GERAR_RELATORIO("/generate-report"),
    LIMPAR_HISTORICO("/clean-history"),
    LIMPAR_RESULTADOS("/clean-results");

    private final String subDiretorio;

    SubDiretorio(String subDiretorio) {
        this.subDiretorio = subDiretorio;
    }

    public String getSubDiretorio(){
        return subDiretorio;
    }
}
