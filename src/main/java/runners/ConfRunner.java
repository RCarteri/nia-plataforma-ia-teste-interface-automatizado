package runners;

import ath_allure_arq3.main.AllureARQ3;
import pagesObjects.LoginPage;
import support.APIRest.APIRest;
import support.APIRest.Host;
import support.Utils;

import static ath_allure_arq3.main.AllureARQ3.ConfigInicialAllureARQ3;
import static support.APIRest.Path.*;
import static support.enums.SysProps.isLoggedPlataforma;

public class ConfRunner {
    private static final APIRest apiRest = new APIRest();

    public static void confInit() {
        ConfigInicialAllureARQ3();
        // Descomentar para limpar o histórico de relatóios no Allure caso esteja travando muito
        //limparRelatoriosAllure(DESENV);
        //limparRelatoriosAllure(HML);
        //limparRelatoriosAllure(PROD);
    }

    public static void confFinish(Host ambiente) {
        if (isLoggedPlataforma())
            new LoginPage().logoutEFecharPlataforma();
        new AllureARQ3().enviarRelatorio(ambiente.getHost());
        apiRest.atualizarAllureArq3(ambiente.getUrl(GERAR_RELATORIO));
    }

    private static void limparRelatoriosAllure(Host ambiente) {
        new Utils().deletarAllureResults();
        apiRest.atualizarAllureArq3(ambiente.getUrl(LIMPAR_RESULTADOS));
        apiRest.atualizarAllureArq3(ambiente.getUrl(LIMPAR_HISTORICO));
    }
}
