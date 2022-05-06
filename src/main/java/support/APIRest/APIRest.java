package support.APIRest;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

import static support.Utils.printLog;
import static support.enums.LogTypes.*;

public class APIRest {
    public void atualizarAllureArq3(String urlAcao){
        try{
            HttpURLConnection conn = (HttpURLConnection) new URL(urlAcao).openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() == 200) {
                printLog("Resultado: " + conn.getResponseCode() + ". Dados recebidos com sucesso para a URL: " + urlAcao, INFO);
            }else {
                printLog("Erro " + conn.getResponseCode() + " ao obter dados da URL " + urlAcao, ERROR);
            }
               conn.disconnect();
            printLog("Conexão fechada", NULL);
        } catch (IOException ex) {
            Logger.getLogger(APIRest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
