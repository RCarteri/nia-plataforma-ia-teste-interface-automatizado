package support.APIRest;

import br.com.bb.ath.ftabb.utilitarios.FTABBUtils;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.JSONArray;
import support.Utils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;

import static io.qameta.allure.Allure.descriptionHtml;
import static io.qameta.allure.Allure.link;
import static io.restassured.RestAssured.*;
import static io.restassured.http.ContentType.JSON;
import static java.lang.String.valueOf;
import static java.lang.System.setProperty;
import static java.util.regex.Pattern.compile;
import static org.junit.Assert.fail;
import static support.APIRest.DadosSelecionadosApi.getInstanceDSApi;
import static support.GetElements.getDriver;
import static support.Utils.*;
import static support.enums.Ambiente.*;
import static support.enums.Cookie.*;
import static support.enums.LogTypes.INFO;
import static support.enums.Siglas.getInstanceSigla;
import static support.enums.User.getUser;
import static support.enums.User.getUserName;

public class BaseClass extends FTABBUtils {
    private RequestSpecification request;
    private String endpoint;
    private String payload;
    public Response response;
    private JSONArray listaRetorno;
    private final Utils utils = new Utils();

    public void setEndpoint(String endpoint) {
        this.endpoint = endpoint;
    }

    public void getCookies() {
        if (isLoggedIntranet()) {
            printLog("Cookies já estão salvos.", INFO);
        } else {
            setProperty(IBBID.toString(), valueOf(getDriver().manage().getCookieNamed("IBBID").getValue()));
        }
    }

    private void setProxy() {
        if (!isQteste())
            proxy("170.66.49.180", 3128);
    }

    private void setRequest() {
        setProxy();
        if (endpoint.contains("/")) {
            baseURI = BASE_URL_INTRANET.getUrl();
            request = given()
                    .contentType(JSON)
                    .cookie("IBBID", getIBBID())
                    .relaxedHTTPSValidation();
        } else {
            baseURI = BASE_URL.getUrl();
            request = given()
                    .contentType(JSON)
                    .relaxedHTTPSValidation();
        }
    }

    public void enviarPayload() {
        setRequest();
        response = request.given()
                .body(payload)
                .when()
                .post(endpoint);
        setTable();
        printResult();
    }

    protected void enviarPayload(String endpoint) {
        setRequest();
        response = request.given()
                .body(payload)
                .when()
                .post(endpoint);
        setListaRetorno();
        printResult();
    }

    protected void printResult() {
        System.out.println("---------------------------------------------------------");
        printLog("Request enviado para a URL: " + baseURI + endpoint, INFO);
        System.out.println(payload);
        printLog("Recebeu o seguinte retorno com o status code:" + response.getStatusLine().replace("HTTP/1.1", ""), INFO);
        response.body().prettyPrint();
    }

    public void salvarListaDados(String componente) {
        switch (componente) {
            case "WATSON_STUDIO":
                getInstanceDSApi().setProjetos(listaRetorno);
            case "USER_INFO":
                getInstanceDSApi().setUserInfo(listaRetorno);
        }
    }

    public void setListaRetorno() {
        ArrayList<Map<String, String>> ArrayListListaRetorno = response.jsonPath().get("listaRetorno");
        listaRetorno = new JSONArray(ArrayListListaRetorno);
        listaRetorno = new TratarListaRetorno(listaRetorno, endpoint).tratarListaRetorno();
    }

    public void setPayload(String tipoPayload) {
        setPayload(endpoint, tipoPayload);
    }

    public void printSiglasExistentes() {
        String siglas = response.body().jsonPath().get("data.listaOcorrencia.siglaSistemaSoftware").toString();
        System.out.println("Siglas existentes para teste: " + siglas);
    }

    public void mudarProjeto(int i) {
        if (i == getInstanceDSApi().getProjetos().size())
            fail("O usuário logado '" + getUser() + "' não é administrador de nenhum projeto das siglas selecionadas '" + getInstanceSigla().getListaSiglasTeste() + "', necessário fazer um novo teste com outra sigla.");
        String codComponente = getInstanceDSApi().getProjetos().get(i).get("codigoComponente");
        payload = payload.replaceFirst("\"[a-z-\\d]+\"", "\"" + codComponente + "\"");
        enviarPayload(endpoint);
    }

    public void setMembro() {
        HashMap<String, String> membro = getInstanceDSApi()
                .getMembros().get(getRandom(getInstanceDSApi().getMembros().size()));
        if (membro.get("state").equals("ACTIVE") && membro.get("type").contains("user") && !membro.get("userName").equals(getUserName()))
            getInstanceDSApi().setMembro(membro);
        else
            setMembro();
    }

    public boolean verificarMembrosProjeto(String papel) {
        int i = -1;
        String papelEditor = "";
        if (!papel.equals("admin")) {
            papelEditor = "editor";
            papel = "viewer";
        }
        do {
            getInstanceDSApi().setProjeto(i);
            getInstanceDSApi().setMembros(listaRetorno);
            if (isUsuarioLogadoPapel(papel) || isUsuarioLogadoPapel(papelEditor) && !isUnicoMembro()) return true;
            mudarProjeto(++i);
        }
        while (i < getInstanceDSApi().getProjetos().size());
        return false;
    }

    private static boolean isUnicoMembro() {
        for (HashMap<String, String> membro_ : getInstanceDSApi().getMembros())
            if ((membro_.get("state").equals("ACTIVE") &&
                    membro_.get("type").equals("user") &&
                    !membro_.get("userName").equals(getUserName())))
                return false;
        System.out.println("O projeto '" + getInstanceDSApi().getProjeto().get("nomeComponente") + "' só possui um membro, não será possível editar o seu papel.");
        return true;
    }

    private static boolean isUsuarioLogadoPapel(String papel) {
        for (HashMap<String, String> membro : getInstanceDSApi().getMembros())
            if (membro.get("userName").equals(getUserName()) && membro.get("role").equals(papel)) {
                System.out.println("O usuário logado '" + getUser() + "' é " + papel + " do projeto: " + getInstanceDSApi().getProjeto().get("nomeComponente") + ". Seguindo com o teste.");
                return true;
            }
        System.out.println("O usuário logado '" + getUser() + "' não é " + papel + " do projeto '" + getInstanceDSApi().getProjeto().get("nomeComponente"));
        return false;
    }

    protected void setPayload(String endpoint, String componente) {
        this.endpoint = endpoint;
        payload = utils.getPayload(this.endpoint, componente);
    }

    public void tratarPayload(String componente) {
        payload = new TratarPayload(payload, listaRetorno).tratarPayload(componente, endpoint);
    }

    public void setMembroParaIncluir(List<List<String>> membros) {
        for (List<String> membroParaIncluir : membros) {
            boolean membroJaExiste = false;
            String email = membroParaIncluir.get(1);
            for (HashMap<String, String> membro : getInstanceDSApi().getMembros()) {
                if (membro.get("userName").equals(email)) {
                    membroJaExiste = true;
                    break;
                }
            }
            if (!membroJaExiste) {
                getInstanceDSApi().setMembroParaIncluir(membroParaIncluir);
                return;
            }
        }
    }

    private String getDescricao() {
        return utils.getPayload(endpoint, "descrição");
    }

    public String gerarHtmlRequisicaoPost() {
        return "<table><tr><td colspan=\"2\" style=\"padding:12px;text-align: left;background-color:#f0f2f4;font-weight:bold;\">" +
                "POST" +
                "</td></tr><tr><td style=\"min-width:100px;padding:12px;\">" +
                "Descrição</td><td>" +
                getDescricao() +
                "</td></tr><tr><td style=\"min-width:100px;padding:12px;\">URL</td><td>" +
                BASE_URL.getUrl() + endpoint +
                "</td></tr><tr><td style=\"min-width:100px;padding:12px;\">Status Code</td><td>" +
                response.getStatusLine().replace("HTTP/1.1 ", "") +
                "</td></tr><tr><td style=\"min-width:100px;padding:12px;\">Request payload</td><td>" +
                payload +
                "</td></tr><tr><td style=\\\"min-width:100px;padding:12px;\\\">Response body</td><td>\"" +
                response.body().print() +
                "</td></tr></table>";
    }

    private void setTable() {
        descriptionHtml(gerarHtmlRequisicaoPost());
        link("Logar na intranet", DESENV.getUrl());
        link("Acessar " + endpoint, getUrl());
    }

    private String getUrl() {
        String endpoint;
        Matcher matcher = compile("[a-z]{3}").matcher(this.endpoint);
        if (matcher.find()) {
            endpoint = this.endpoint.substring(0, 3).toUpperCase() + this.endpoint.substring(3).replace("-", "_");
            return API.getUrl() + endpoint;
        } else {
            endpoint = this.endpoint.substring(0, 1).toUpperCase() + this.endpoint.substring(1, 9) + "_" + this.endpoint.substring(9);
            return API.getUrl() + "NIA/" + endpoint;
        }
    }
}
