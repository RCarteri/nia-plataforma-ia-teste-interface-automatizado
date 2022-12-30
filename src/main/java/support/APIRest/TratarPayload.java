package support.APIRest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import support.Utils;
import support.enums.DadosSelecionadosApi;

import java.util.Arrays;
import java.util.List;

import static java.lang.System.setProperty;
import static support.Utils.getRandom;
import static support.Utils.printLog;
import static support.enums.DadosSelecionadosApi.*;
import static support.enums.LogTypes.INFO;
import static support.enums.Siglas.getInstance;
import static support.enums.User.getChave;
import static support.enums.User.getUser;

public class TratarPayload {
    private String payload;
    private final JSONArray listaRetorno;
    private JSONObject componenteListaRetorno;

    public TratarPayload(String payload, JSONArray listaRetorno) {
        this.payload = payload;
        this.listaRetorno = listaRetorno;
    }

    public JSONObject getComponente() {
        return componenteListaRetorno;
    }

    protected void setComponenteListaRetorno() {
        try {
            int index = getRandom(listaRetorno.length());
            componenteListaRetorno = listaRetorno.getJSONObject(index);
        } catch (IllegalArgumentException e) {
            throw new RuntimeException("A lista usada não possui dados com a sigla " + getInstance().getListaSiglasTeste() + " que foi selecionada.");
        }
    }

    protected String getCodComponente() {
        String nomeComponente, codComponente;
        if (listaRetorno == null) return "";
        if (listaRetorno.get(0).getClass().getSimpleName().equals("String"))
            return listaRetorno.get(getRandom(listaRetorno.length())).toString();
        setComponenteListaRetorno();
        try {
            if (componenteListaRetorno.get("nomeComponente").toString().equals("null")) {
                nomeComponente = (String) componenteListaRetorno.get("nome");
                codComponente = (String) componenteListaRetorno.get("id");
            } else {
                nomeComponente = (String) componenteListaRetorno.get("nomeComponente");
                codComponente = (String) componenteListaRetorno.get("codigoComponente");
                setProperty(COD_COMPONENTE.toString(), codComponente);
            }
        } catch (JSONException e) {
            //Usado para request que editam os papeis dos membros
            nomeComponente = (String) componenteListaRetorno.get("userName");
            codComponente = (String) componenteListaRetorno.get("id");
            setProperty(PAPEL.toString(), (String) componenteListaRetorno.get("role"));
        }
        printLog("Nome do componente escolhido: " + nomeComponente, INFO);
        return codComponente;
    }

    protected String getListaSiglas() {
        if (getInstance().getSiglas() == null)
            getInstance().setSiglas();
        else
            printLog("As siglas que o usuário '" + getUser() + "' possui acesso já estão em memória: " + getInstance().getSiglas(), INFO);
        getInstance().setListaSiglaTeste();
        return getInstance().getListaSiglasTeste();
    }

    private String getCodEspaco() {
        setComponenteListaRetorno();
        return (String) componenteListaRetorno.get("codigoEspaco");
    }

    private String getCodEmail() {
        JSONObject o = (JSONObject) listaRetorno.get(0);
        return (String) o.get("email");
    }

    public String tratarPayload(String componente, String endpoint) {
        switch (endpoint) {
            case "dpr/Op5903588-v1":
                if (getUser() == null)
                    new Utils().setDatapool("desenvolvimento");
                payload = payload.replaceFirst("CHAVE_USUARIO", getChave());
                break;
            case "op5806077v3":
            case "op5806077v2":
                payload = payload.replaceFirst("LISTA_SIGLA", getListaSiglas());
                break;
            case "op6851522v1":
                payload = payload.replaceFirst("COD_ESPACO", getCodEspaco());
                break;
            case "op5949338v1":
                payload = payload.replaceFirst("COD_COMPONENTE", DadosSelecionadosApi.getCodComponente())
                        .replaceFirst("COD_EMAIL", getCodEmail())
                        .replaceFirst("COD_ID", getCodId())
                        .replaceFirst("COD_PERMISSAO", getPapel());
                System.out.println();
                break;
            case "op5839181v1":
                payload = payload.replaceFirst("COD_COMPONENTE", getCodComponente());
                break;
        }
        payload = payload.replaceFirst("NOME_COMPONENTE", componente);
        System.out.println(payload);
        payload = payload.replaceFirst("-[A-Z_]+", "");
        return payload;
    }

    private String getPapel() {
        List<String> papeis = Arrays.asList("admin", "viewer", "editor");
        String papel = papeis.get(getRandom(papeis.size()));
        return (papel.equals(getPapelOriginal())) ? getPapel() : papel;
    }

    private String getCodId() {
        JSONObject o = (JSONObject) listaRetorno.get(0);
        return (String) o.get("iamId");
    }
}