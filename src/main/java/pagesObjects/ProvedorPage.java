package pagesObjects;

import map.ProvedorMap;
import org.jetbrains.annotations.NotNull;

public class ProvedorPage extends ProvedorMap {
    public void acessarProvedor(@NotNull String provedor) {
        switch (provedor) {
            case "IBM Cloud":
                getListProvedor().get(0).click();
                break;
            case "Triton":
                getListProvedor().get(1).click();
                break;
        }
    }
}
