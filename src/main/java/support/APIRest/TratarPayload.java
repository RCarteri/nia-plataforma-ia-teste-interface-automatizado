package support.APIRest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import support.Utils;

import static support.Utils.getRandom;
import static support.Utils.printLog;
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
        String nomeComponente;
        String codComponente;
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
            }
        } catch (JSONException e) {
            nomeComponente = (String) componenteListaRetorno.get("userName");
            codComponente = (String) componenteListaRetorno.get("id");
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

    protected String getCodEspaco() {
        setComponenteListaRetorno();
        return (String) componenteListaRetorno.get("codigoEspaco");
    }

    public String tratarPayload(String componente) {
        payload = payload
                .replaceFirst("COD_COMPONENTE", getCodComponente())
                .replaceFirst("NOME_COMPONENTE", componente)
                .replaceFirst("_MEMBROS", "");

        if (payload.contains("CHAVE_USUARIO")) {
            if (getUser() == null)
                new Utils().setDatapool("desenvolvimento");
            payload = payload.replaceFirst("CHAVE_USUARIO", getChave());
        }

        if (payload.contains("LISTA_SIGLA")) {
            payload = payload.replaceFirst("LISTA_SIGLA", getListaSiglas());
        }

        if (payload.contains("COD_ESPACO")) {
            payload = payload.replaceFirst("COD_ESPACO", getCodEspaco());
        }
        return payload;
    }
}