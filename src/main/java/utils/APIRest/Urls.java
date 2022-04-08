package utils.APIRest;

import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

public enum Urls {
    GERAR_RELATORIO("/generate-report"),
    LIMPAR_HISTORICO("/clean-history"),
    LIMPAR_RESULTADOS("/clean-results");

    private final String url;

    Urls(String url) {
        this.url = url;
    }

    @Contract(pure = true)
    public @NotNull String getUrl() {
        String dominio = "http://plataforma-ia-teste-interface-automatizado-allure-arq3.nia.desenv.bb.com.br/allure-docker-service";
        return dominio + url;
    }
}
