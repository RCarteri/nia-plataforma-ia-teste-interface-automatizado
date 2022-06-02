package support.APIRest;

public enum Host {
    //Usado para evidenciar os testes cotidianos
    DESENV("http://plat-ia-allure.nia.desenv.bb.com.br", "servidor.allure.desenv"),

    //Usado para evidenciar os testes na branch master
    HML("http://plat-ia-allure.nia.hm.bb.com.br", "servidor.allure.hml"),

    //Usado para evidenciar os testes na entrega da Sprint
    PROD("http://plat-ia-allure.nia.intranet.bb.com.br", "servidor.allure.prd");

    private final String dominio;
    private final String host;

    Host(String dominio, String servidor) {
        this.dominio = dominio + "/allure-docker-service";
        this.host = servidor;
    }

    public String getUrl (Path path) {
        return dominio + path.getSubDiretorio();
    }

    public String getHost() {
        return host;
    }
}
