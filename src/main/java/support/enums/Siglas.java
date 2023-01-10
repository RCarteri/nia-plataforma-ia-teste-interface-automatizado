package support.enums;

import pagesObjects.LoginPage;
import stepsDefinitions.Api;
import support.APIRest.BaseClass;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static org.junit.Assert.fail;
import static support.Utils.getRandom;
import static support.Utils.printLog;
import static support.enums.LogTypes.ERROR;

public final class Siglas {
    private static Siglas instance;
    public List<String> siglas;
    private String listaSiglasTeste = "COC,DSC,NIA,NXR";
    private int tamanhoListaSiglaTeste;

    public static Siglas getInstanceSigla() {
        if (instance == null) {
            instance = new Siglas();
        }
        return instance;
    }

    public String getListaSiglasTeste() {
        return listaSiglasTeste;
    }

    public List<String> getSiglas() {
        return siglas;
    }

    private void initDesenv() {
        new LoginPage().abraPlataforma();
        new LoginPage().logar("desenvolvimento");
        new Api().queNaoTenhaCookiesPegueOsCookies();
    }

    public void setListaSiglaTeste() {
        Set<String> siglasSorteadas = new HashSet<>();
        try {
            while(siglasSorteadas.size() < tamanhoListaSiglaTeste){
                siglasSorteadas.add(siglas.get(getRandom(siglas.size())));
            }
            listaSiglasTeste = siglasSorteadas.toString().replaceAll("[\\[\\]\\s]", "");
        } catch (NullPointerException e) {
            printLog("O response anterior não possui nenhum retorno com a sigla que foi usada para o teste e retornou " + e.getClass().getSimpleName(), ERROR);
            fail();
        }
    }

    public void setSiglas() {
        BaseClass bC = new BaseClass();
        initDesenv();
        bC.setEndpoint("dpr/Op5903588-v1");
        bC.setPayload("OK");
        bC.tratarPayload("OK");
        bC.enviarPayload();
        String path = "data.listaOcorrencia.siglaSistemaSoftware";
        siglas = bC.response.body().jsonPath().get(path);
        setSizeListaSigla();
    }

    private void setSizeListaSigla() {
        int QUANT_SIGLAS_PARA_TESTAR = 2;
        switch (siglas.size()) {
            case 0:
                printLog("O tamanho da lista de siglas retornado é 0, impossível continuar o teste.", ERROR);
                fail();
                break;
            case 1:
                tamanhoListaSiglaTeste = 1;
                break;
            default:
                tamanhoListaSiglaTeste = QUANT_SIGLAS_PARA_TESTAR;
        }
    }
}