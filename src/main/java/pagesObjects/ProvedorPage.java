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
                pM.getListProvedor().get(0).click();
                break;
            case "Triton":
                pM.getListProvedor().get(1).click();
                break;
        }
    }
}
