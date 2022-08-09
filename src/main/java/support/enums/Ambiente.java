package support.enums;

public enum Ambiente {
        DESENV("https://plataforma.desenv.bb.com.br/nia-plat-ia-api/v3/api/swagger.json"),
        API("https://plataforma.desenv.bb.com.br/gaw-pequi-api/v3/swagger-ui/2.2.10/dist/index.html?url=/nia-plat-ia-api/v3/api/swagger.json#!/SERVICOS32NIA");

    private final String url;

    Ambiente(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
