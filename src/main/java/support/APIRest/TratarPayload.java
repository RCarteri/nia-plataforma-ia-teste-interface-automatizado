package support.APIRest;

import org.json.JSONArray;
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

    public TratarPayload(String payload, JSONArray listaRetorno) {
        this.payload = payload;
        this.listaRetorno = listaRetorno;
    }

    protected JSONObject getComponenteListaRetorno(JSONArray list) {
        try{
            int index = getRandom(list.length());
            return list.getJSONObject(index);
        }catch(IllegalArgumentException e){
            throw new RuntimeException("A lista usada não possui dados com a sigla " + getInstance().getListaSiglasTeste() + " que foi selecionada.");
        }
    }

    protected String getCodComponente(JSONArray listaRetorno) {
        if (listaRetorno == null) return "";
        JSONObject componente = getComponenteListaRetorno(listaRetorno);
        if (componente.get("nomeComponente").toString().equals("null")) {
            printLog("Nome do componente escolhido: " + componente.get("nome"), INFO);
            return (String) componente.get("id");
        } else {
            printLog("Nome do componente escolhido: " + componente.get("nomeComponente"), INFO);
            return (String) componente.get("codigoComponente");
        }
    }

    protected String getListaSiglas() {
        if (getInstance().getSiglas() == null)
            getInstance().setSiglas();
        else
            printLog("As siglas que o usuário '" + getUser() + "' possui acesso já estão em memória: " + getInstance().getSiglas(), INFO);
        return getInstance().getListaSiglasTeste();
    }

    protected String getCodEspaco() {
        return (String) getComponenteListaRetorno(listaRetorno).get("codigoEspaco");
    }

    public String tratarPayload(String componente) {
        payload = payload
                .replaceFirst("COD_COMPONENTE", getCodComponente(listaRetorno))
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