package support.APIRest;

public enum Ambiente {
    //Usado para evidenciar os testes cotidianos
    DESENV("http://plataforma-ia-teste-interface-automatizado-allure-arq3.nia.desenv.bb.com.br/allure-docker-service", "servidor.allure.docker.ulr1"),

    //Usado para evidenciar os testes na branch master
    HML("http://plataforma-ia-teste-interface-automatizado-allure-arq3.nia.hm.bb.com.br/allure-docker-service", "servidor.allure.docker.ulr2"),

    //Usado para evidenciar os testes na entrega da Sprint
    PROD("http://plataforma-ia-teste-interface-automatizado-allure-arq3.nia.prd.bb.com.br/allure-docker-service", "servidor.allure.docker.ulr3");

    private final String url;
    private final String servidor;

    Ambiente(String url, String servidor) {
        this.url = url;
        this.servidor = servidor;
    }

    public String getUrl (Dominio dominio) {
        return dominio + url;
    }

    public String getServidor () {
        return servidor;
    }
}
