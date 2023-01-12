package support.APIRest;

import org.json.JSONArray;
import support.Utils;

import java.util.Arrays;
import java.util.List;

import static support.Utils.*;
import static support.enums.LogTypes.INFO;
import static support.enums.Siglas.getInstanceSigla;
import static support.APIRest.DadosSelecionadosApi.getInstanceDSApi;
import static support.enums.User.getChave;
import static support.enums.User.getUser;

public class TratarPayload {
    private String payload;
    private final JSONArray listaRetorno;

    public TratarPayload(String payload, JSONArray listaRetorno) {
        this.payload = payload;
        this.listaRetorno = listaRetorno;
    }

    protected String getCodComponente() {
        String nomeComponente, codComponente;
        if (listaRetorno == null) return "";
        if (listaRetorno.get(0).getClass().getSimpleName().equals("String"))
            return listaRetorno.get(0).toString();
        getInstanceDSApi().setComponenteEscolhido(listaRetorno);
//            usado para retornar as instâncias do Watson Assistant
        if (getInstanceDSApi().getComponenteEscolhido().get("nome") != null) {
            nomeComponente = getInstanceDSApi().getComponenteEscolhido().get("nome");
            codComponente = getInstanceDSApi().getComponenteEscolhido().get("id");
        } else if (getInstanceDSApi().getComponenteEscolhido().get("nomeComponente") != null){
            nomeComponente = getInstanceDSApi().getComponenteEscolhido().get("nomeComponente");
            codComponente = getInstanceDSApi().getComponenteEscolhido().get("codigoComponente");
        } else { //(getInstanceDSApi().getComponenteEscolhido().get("role") != null)
//            Usado para request que editam os papeis dos membros
            nomeComponente = getInstanceDSApi().getMembro().get("userName");
            codComponente = getInstanceDSApi().getMembro().get("id");
            getInstanceDSApi().setPapelOriginal(getInstanceDSApi().getMembro().get("role"));
        }
        printLog("Nome do componente escolhido: " + nomeComponente, INFO);
        return codComponente;
    }

    protected String getListaSiglas() {
        if (getInstanceSigla().getSiglas() == null)
            getInstanceSigla().setSiglas();
        else
            printLog("As siglas que o usuário '" + getUser() + "' possui acesso já estão em memória: " + getInstanceSigla().getSiglas(), INFO);
        getInstanceSigla().setListaSiglaTeste();
        printLog("Sigla usada para o teste: " + getInstanceSigla().getListaSiglasTeste(), INFO);
        return getInstanceSigla().getListaSiglasTeste();
    }

    private String getCodEspaco() {
        getInstanceDSApi().setComponenteEscolhido(listaRetorno);
        return getInstanceDSApi().getComponenteEscolhido().get("codigoEspaco");
    }

    private String getCodEmail() {
        return getInstanceDSApi().getUserInfo().get("email");
    }

    public String tratarPayload(String componente, String endpoint) {
        switch (endpoint) {
            case "dpr/Op5903588-v1":
            case "op5806077v2":
            case "op5806077v3":
//                usado para selecionar a sigla de acordo com o response
//                payload = payload.replaceFirst("LISTA_SIGLA", getListaSiglas());
                if (getUser() == null)
                    new Utils().setDatapool("desenvolvimento");
                payload = payload.replaceFirst("CHAVE_USUARIO", getChave());
                break;
            case "op6851522v1":
                payload = payload.replaceFirst("COD_ESPACO", getCodEspaco());
                break;
            case "op5949338v1":
                String acao = getStringByRegex("(?<=-)[A-Z]+", componente);
                boolean diferente = acao.equals("EDITAR");
                payload = payload.replaceFirst("COD_COMPONENTE", getInstanceDSApi().getCodComponente())
                        .replaceFirst("COD_EMAIL", getCodEmail())
                        .replaceFirst("COD_ID", getCodId())
                        .replaceFirst("ACAO", acao)
                        .replaceFirst("COD_PERMISSAO", getPapel(diferente));
                break;
            case "op5839181v1":
                payload = payload.replaceFirst("COD_COMPONENTE", getCodComponente());
                break;
        }
        payload = payload.replaceFirst("NOME_COMPONENTE", componente);
        payload = payload.replaceFirst("-[A-Z_]+", "");
        return payload;
    }

    private String getPapel(boolean diferente) {
        if (diferente) {
            List<String> papeis = Arrays.asList("admin", "viewer", "editor");
            String papel = papeis.get(getRandom(papeis.size()));
            return (papel.equals(getInstanceDSApi().getPapelOriginal())) ? getPapel(diferente) : papel;
        }else
            return getInstanceDSApi().getMembro().get("role");
    }

    private String getCodId() {
        return getInstanceDSApi().getUserInfo().get("iamId");
    }
}