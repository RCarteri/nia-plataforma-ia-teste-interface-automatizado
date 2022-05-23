package support.APIRest;

public enum Dominio {
    //Usado para evidenciar os testes cotidianos
    DESENV("http://plataforma-ia-teste-interface-automatizado-allure-arq3.nia.desenv.bb.com.br", "servidor.allure.desenv"),

    //Usado para evidenciar os testes na branch master
    HML("http://plat-ia-allure.nia.hm.bb.com.br", "servidor.allure.hml"),

    //Usado para evidenciar os testes na entrega da Sprint
    PROD("http://plat-ia-allure.nia.prd.bb.com.br", "servidor.allure.prd");

    private final String dominio;
    private final String servidor;

    Dominio(String dominio, String servidor) {
        this.dominio = dominio + "/allure-docker-service";
        this.servidor = servidor;
    }

    public String getUrl (SubDiretorio subDiretorio) {
        return dominio + subDiretorio.getSubDiretorio();
    }

    public String getServidor () {
        return servidor;
    }
}
