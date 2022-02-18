package runners;

import ath_allure_arq3.main.AllureARQ3;
import stepsDefinitions.Hooks;
import utils.APIRest.APIRest;
import utils.APIRest.Urls;
import utils.Utils;

public class ConfRunner {
    static APIRest apiRest = new APIRest();

    public static void init(){
        new Utils().deletarAllureResults();
        AllureARQ3.ConfigInicialAllureARQ3();
        apiRest.atualizarAllureArq3(Urls.LIMPAR_RESULTADOS.getUrl());
        apiRest.atualizarAllureArq3(Urls.LIMPAR_HISTORICO.getUrl());
    }

    public static void finish() {
        new Hooks().realizarOLogOutNaPlataformaEFechaLa();
        new AllureARQ3().enviarRelatorio("servidor.allure.docker.ulr1");
        apiRest.atualizarAllureArq3(Urls.GERAR_RELATORIO.getUrl());
    }
}
