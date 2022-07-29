package support.enums;

public enum Ambiente {
        DESENV("https://plataforma.desenv.bb.com.br/nia-plat-ia-api/v3/api/swagger.json");

    private final String url;

    Ambiente(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
