package pagesObjects;

import map.ProvedorMap;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProvedorPage extends ProvedorMap {
    public List<WebElement> getProvedores() {
        return getListProvedor();
    }

    //legado
//    public void acessarProvedor(String provedor) {
//        switch (provedor) {
//            case "IBM Cloud":
//                getListProvedor().get(0).click();
//                break;
//            case "Triton":
//                getListProvedor().get(1).click();
//                break;
//        }
//    }
}
