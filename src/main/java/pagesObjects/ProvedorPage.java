package pagesObjects;

import br.com.bb.ath.ftabb.Pagina;
import map.ProvedorMap;
import org.jetbrains.annotations.NotNull;

public class ProvedorPage extends Pagina {
    public void acessarProvedor(@NotNull String provedor) {
        ProvedorMap pM = new ProvedorMap();
        switch (provedor) {
            case "IBM Cloud":
                pM.getBtnIBMCloud().click();
                break;
            case "Triton":
                pM.getBtnTriton().click();
                break;
        }
    }
}
