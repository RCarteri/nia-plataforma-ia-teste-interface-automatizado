package utils.APIRest;

public enum Urls {
    ATUALIZAR_ALLURE("/generate-report");

    private final String url;

    Urls(String url) {
        this.url = url;
    }

    public String getUrl() {
        String dominio = "http://plataforma-ia-teste-interface-automatizado-allure-arq3.nia.desenv.bb.com.br/allure-docker-service";
        return dominio + url;
    }
}
