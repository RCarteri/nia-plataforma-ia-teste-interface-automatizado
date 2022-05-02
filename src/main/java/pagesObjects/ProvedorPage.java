package pagesObjects;

import map.ProvedorMap;
import org.jetbrains.annotations.NotNull;

public class ProvedorPage {
    private final ProvedorMap pM;

    public ProvedorPage() {
       this.pM = new ProvedorMap();
    }

    public void acessarProvedor(@NotNull String provedor) {
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
