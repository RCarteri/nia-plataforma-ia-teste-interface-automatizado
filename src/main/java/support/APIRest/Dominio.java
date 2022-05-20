package support.APIRest;

public enum Dominio {
    //Usado para evidenciar os testes cotidianos
    DESENV("http://plataforma-ia-teste-interface-automatizado-allure-arq3.nia.desenv.bb.com.br/allure-docker-service", "servidor.allure.desenv"),

    //Usado para evidenciar os testes na branch master
    HML("http://plataforma-ia-teste-interface-automatizado-allure-arq3.nia.hm.bb.com.br/allure-docker-service", "servidor.allure.docker.ulr2"),

    //Usado para evidenciar os testes na entrega da Sprint
    PROD("http://plataforma-ia-teste-interface-automatizado-allure-arq3.nia.prd.bb.com.br/allure-docker-service", "servidor.allure.docker.ulr3");

    private final String dominio;
    private final String servidor;

    Dominio(String dominio, String servidor) {
        this.dominio = dominio;
        this.servidor = servidor;
    }

    public String getUrl (SubDiretorio subDiretorio) {
        return dominio + subDiretorio.getSubDiretorio();
    }

    public String getServidor () {
        return servidor;
    }
}
