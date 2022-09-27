package pagesObjects.sections;

import map.SideBarMap;

import static support.Utils.printLog;
import static support.enums.LogTypes.ERROR;

public class SideBarSection {
    private final SideBarMap sM = new SideBarMap();

    public void acessarProvedor(String menu) {
        switch (menu) {
            case "IBM":
                sM.getOpenSideBar().click();
                sM.getIbm().click();
                break;
        }
    }

    public void acessarItem(String item) {
        if (item.contains("Watson")) {
            sM.getWatson().click();
            switch (item){
                case "Watson Studio":
                    sM.getListItens().get(0).click();
                    break;
                default:
                    printLog("Item não existe no sideBar: " + item, ERROR);
            }
        }
    }
}
