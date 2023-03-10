package support.APIRest;

import org.json.JSONArray;

import java.util.HashMap;
import java.util.List;

import static support.Utils.getRandom;
import static support.Utils.jsonArraytoListHashMap;

public class DadosSelecionadosApi {
    private String papelOriginal = "viewer";
    private static DadosSelecionadosApi instance;
    private List<HashMap<String, String>> membros;
    private HashMap<String, String> membro;
    private HashMap<String, String> userInfo;
    private List<HashMap<String, String>> projetos;
    private HashMap<String, String> projeto;
    private HashMap<String, String> componenteEscolhido;
    private List<String> membroParaIncluir;

    public static DadosSelecionadosApi getInstanceDSApi() {
        if (instance == null) {
            instance = new DadosSelecionadosApi();
        }
        return instance;
    }

    public HashMap<String, String> getUserInfo() {
        return userInfo;
    }

    public HashMap<String, String> getComponenteEscolhido() {
        return componenteEscolhido;
    }

    public void setComponenteEscolhido(JSONArray listaRetorno) {
        this.componenteEscolhido = jsonArraytoListHashMap(listaRetorno).get(getRandom(listaRetorno.length()));
    }

    public void setUserInfo(JSONArray userInfo) {
        this.userInfo = jsonArraytoListHashMap(userInfo).get(0);
    }

    public List<HashMap<String, String>> getMembros() {
        return membros;
    }

    public void setMembroParaIncluir(List<String> membro){
        this.membroParaIncluir = membro;
    }

    public List<String> getMembroParaIncluir(){
        return this.membroParaIncluir;
    }

    public List<HashMap<String, String>> getProjetos() {
        return projetos;
    }

    public void setProjetos(JSONArray projetos) {
        this.projetos = jsonArraytoListHashMap(projetos);
    }

    public HashMap<String, String> getProjeto() {
        return projeto;
    }

    public void setProjeto(int indexProjetoSelecionado) {
        this.projeto = (indexProjetoSelecionado == -1) ?
                this.componenteEscolhido : projetos.get(indexProjetoSelecionado);
    }

    public HashMap<String, String> getMembro() {
        return membro;
    }

    public void setMembro(HashMap<String, String> membro) {
        this.membro = membro;
    }

    public void setMembros(JSONArray membros) {
        this.membros = jsonArraytoListHashMap(membros);
    }

    public String getPapelOriginal() {
        return papelOriginal;
    }

    public void setPapelOriginal(String papelOriginal) {
        this.papelOriginal = papelOriginal;
    }

    public String getCodComponente() {
        return getProjeto().get("codigoComponente");
    }
}
