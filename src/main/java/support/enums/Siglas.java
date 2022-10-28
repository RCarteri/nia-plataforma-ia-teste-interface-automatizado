package support.enums;

import pagesObjects.LoginPage;
import stepsDefinitions.Api;
import support.APIRest.BaseClass;

import java.util.List;

import static support.Utils.getRandom;
import static support.Utils.printLog;
import static support.enums.LogTypes.INFO;

public final class Siglas {
    private static Siglas instance;
    public List<String> siglas;
    private String sigla;

    public static Siglas getInstance() {
        if (instance == null) {
            instance = new Siglas();
        }
        return instance;
    }

    public String getSigla() {
        if (sigla == null)
            setSigla();
        printLog("Sigla usada para o teste: " + sigla, INFO);
        return sigla;
    }

    public List<String> getSiglas() {
        return siglas;
    }

    private void initDesenv() {
        new LoginPage().abraPlataforma();
        new LoginPage().logar("desenvolvimento");
        new Api().queNaoTenhaCookiesPegueOsCookies();
    }

    private void setSigla() {
        sigla = siglas.get(getRandom(siglas.size()));
    }

    public void setSiglas() {
        BaseClass bC = new BaseClass();
        initDesenv();
        bC.setEndpoint("dpr/Op5903588-v1");
        bC.setPayload("OK");
        bC.tratarPayload("OK");
        bC.enviarPayload();
        String path = "data.listaOcorrencia.siglaSistemaSoftware";
        this.siglas = bC.response.body().jsonPath().get(path);
    }
}