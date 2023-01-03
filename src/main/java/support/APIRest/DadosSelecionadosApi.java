package support.APIRest;

import org.json.JSONArray;

public class DadosSelecionadosApi {
    private JSONArray listaDadosResponseComponente;
    private String papelOriginal;
    private String codComponente;
    private static DadosSelecionadosApi instance;

    public static DadosSelecionadosApi getInstanceDSApi() {
        if (instance == null) {
            instance = new DadosSelecionadosApi();
        }
        return instance;
    }

    public void setListaDadosResponseComponente(JSONArray listaDadosResponseComponente) {
        this.listaDadosResponseComponente = listaDadosResponseComponente;
    }

    public JSONArray getListaDadosResponseComponente() {
        return listaDadosResponseComponente;
    }

    public String getPapelOriginal() {
        return papelOriginal;
    }

    public void setPapelOriginal(String papelOriginal) {
        this.papelOriginal = papelOriginal;
    }

    public String getCodComponente() {
        return codComponente;
    }

    public void setCodComponente(String codComponente) {
        this.codComponente = codComponente;
    }
}
