package utils.APIRest;

import utils.Utils;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class APIRest {
    public void atualizarAllureArq3(){
        realizarRequisicoes(Urls.LIMPAR_RESULTADOS.getUrl());
        realizarRequisicoes(Urls.LIMPAR_HISTORICO.getUrl());
        realizarRequisicoes(Urls.GERAR_RELATORIO.getUrl());
        new Utils().deletarAllureResults();
    }

    public void realizarRequisicoes(String urlAcao){
        try{
            HttpURLConnection conn = (HttpURLConnection) new URL(urlAcao).openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() == 200) {
                System.out.println("Resultado: " + conn.getResponseCode() + ". Dados recebidos com sucesso para a URL: " + urlAcao);
            }else {
                System.out.println("Erro " + conn.getResponseCode() + " ao obter dados da URL " + urlAcao);
            }
               conn.disconnect();
            System.out.println("Conexão fechada");
        } catch (IOException ex) {
            Logger.getLogger(APIRest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
