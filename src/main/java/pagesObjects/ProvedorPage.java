package pagesObjects;

import br.com.bb.ath.ftabb.exceptions.ElementoNaoLocalizadoException;
import map.ProvedorMap;
import org.jetbrains.annotations.NotNull;

public class ProvedorPage {
    public void acessarProvedor(@NotNull String provedor) {
        final ProvedorMap pM = new ProvedorMap();
        try {
            switch (provedor) {
                case "IBM Cloud":
                    pM.getBtnIBMCloud().clicar();
                    break;
                case "Triton":
                    pM.getBtnTriton().clicar();
                    break;
            }
        } catch (ElementoNaoLocalizadoException e) {
            e.printStackTrace();
        }
    }
}
