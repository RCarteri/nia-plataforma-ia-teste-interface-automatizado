package support.APIRest;

import org.json.JSONArray;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TratarListaRetorno {
    private final JSONArray listaRetorno;
    private final String endpoint;

    public TratarListaRetorno(JSONArray listaRetorno, String endpoint) {
        this.listaRetorno = listaRetorno;
        this.endpoint = endpoint;
    }

    public JSONArray tratarListaRetorno() {
        switch (endpoint) {
            case "op5806077v2":
                return new JSONArray(listaRetorno);
            case "op5806077v3":
                return getListaQueContenhaSigla();
            case "op5839181v1":
                return getListaRefatorada();
            default:
                throw new NullPointerException("O endpoint " + endpoint + " não está configurado para ter a listaRertono tratada.");
        }
    }

    private JSONArray getListaRefatorada() {
         return new JSONArray(IntStream
                .range(0, listaRetorno.length())
                .mapToObj(i -> String.valueOf(listaRetorno.getJSONObject(i).get("nomeComponente")))
                .collect(Collectors.joining()));
    }

    private JSONArray getListaQueContenhaSigla() {
        JSONArray finalList = new JSONArray();
        IntStream.range(0, listaRetorno.length())
                .filter(i -> listaRetorno.getJSONObject(i).length() > 4)
                .mapToObj(listaRetorno::getJSONObject)
                .forEachOrdered(finalList::put);
        return finalList;
    }
}