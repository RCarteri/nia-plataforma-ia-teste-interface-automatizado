package support.APIRest;

public enum Dominio {
    GERAR_RELATORIO("/generate-report"),
    LIMPAR_HISTORICO("/clean-history"),
    LIMPAR_RESULTADOS("/clean-results");

    private final String dominio;

    Dominio(String dominio) {
        this.dominio = dominio;
    }
}
