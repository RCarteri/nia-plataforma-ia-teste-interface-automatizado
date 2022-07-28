package support.enums;

public enum Ambiente {
    DESENV("https://plataforma.desenv.bb.com.br/gaw/v3/servlet/br.com.bb.customizacao.servlets.paginas.ServletPaginaInicialIntranet?ctx=https://plataforma.desenv.bb.com.br/gaw/v3");

    private final String url;

    Ambiente(String url) {
        this.url = url;
    }

    public String getUrl() {
        return url;
    }
}
