package pagesObjects;

import map.ProvedorMap;

public class ProvedorPage extends ProvedorMap {
    public void acessarProvedor(String provedor) {
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
