package utils.APIRest;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.logging.Level;
import java.util.logging.Logger;

public class APIRest {
    public void atualizarAllureArq3(){
        try{
            String url = Urls.ATUALIZAR_ALLURE.getUrl();
            HttpURLConnection conn = (HttpURLConnection) new URL(url).openConnection();
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Accept", "application/json");
            if (conn.getResponseCode() != 200) {
                System.out.println("Erro " + conn.getResponseCode() + " ao obter dados da URL " + url);
            }
               conn.disconnect();
        } catch (IOException ex) {
            Logger.getLogger(APIRest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
