package runners;

import ath_allure_arq3.main.AllureARQ3;
import pagesObjects.LoginPage;
import support.APIRest.APIRest;
import support.APIRest.Dominio;
import support.Utils;

import static support.APIRest.SubDiretorio.*;

public class ConfRunner {
    private static final APIRest apiRest = new APIRest();

    public static void confInit(){
        AllureARQ3.ConfigInicialAllureARQ3();
        // Descomentar para limpar o histórico de relatóios no Allure caso esteja travando muito
        //limparRelatoriosAllure(DESENV);
    }

    public static void confFinish(Dominio ambiente) {
        new LoginPage().logoutEFecharPlataforma();
        new AllureARQ3().enviarRelatorio(ambiente.getServidor());
        apiRest.atualizarAllureArq3(ambiente.getUrl(GERAR_RELATORIO));
    }

    private static void limparRelatoriosAllure(Dominio ambiente){
        new Utils().deletarAllureResults();
        apiRest.atualizarAllureArq3(ambiente.getUrl(LIMPAR_RESULTADOS));
        apiRest.atualizarAllureArq3(ambiente.getUrl(LIMPAR_HISTORICO));
    }
}
