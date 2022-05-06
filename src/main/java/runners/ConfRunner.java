package runners;

import ath_allure_arq3.main.AllureARQ3;
import pagesObjects.LoginPage;
import support.APIRest.APIRest;
import support.APIRest.Urls;
import support.Utils;

public class ConfRunner {
    private static final APIRest apiRest = new APIRest();

    public static void confInit(){
        AllureARQ3.ConfigInicialAllureARQ3();
        // Descomentar para limpar o histórico de relatóios no Allure caso esteja travando muito
        //limparRelatoriosAllure();
    }

    public static void confFinish() {
        new LoginPage().logoutEFecharPlataforma();
        new AllureARQ3().enviarRelatorio("servidor.allure.docker.ulr1");
        apiRest.atualizarAllureArq3(Urls.GERAR_RELATORIO.getUrl());
    }

    private static void limparRelatoriosAllure(){
        new Utils().deletarAllureResults();
        apiRest.atualizarAllureArq3(Urls.LIMPAR_RESULTADOS.getUrl());
        apiRest.atualizarAllureArq3(Urls.LIMPAR_HISTORICO.getUrl());
    }
}
