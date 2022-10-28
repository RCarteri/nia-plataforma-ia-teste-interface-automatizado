package support.APIRest;

import org.json.JSONArray;

import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TratarListaRetorno {
    private final JSONArray listaRetorno;
    private final String endpointTratar;

    public TratarListaRetorno(JSONArray listaRetorno, String endpointTratar) {
        this.listaRetorno = listaRetorno;
        this.endpointTratar = endpointTratar;
    }

    public JSONArray tratarListaRetorno() {
        switch (endpointTratar) {
            case "op5806077v2":
                return new JSONArray(listaRetorno);
            case "op5806077v3":
                return getListaQueContenhaSigla();
            case "op5839181v1":
                return getListaRefatorada();
            default:
                throw new NullPointerException("O endpoint " + endpointTratar + " não está configurado para ter a listaRertono tratada.");
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