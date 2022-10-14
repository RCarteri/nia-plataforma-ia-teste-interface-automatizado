package pagesObjects.sections;

import map.SideBarMap;
import org.openqa.selenium.NoSuchElementException;

import static support.Utils.printLog;
import static support.enums.LogTypes.INFO;

public class SideBarSection {
    private final SideBarMap sM = new SideBarMap();

    public void acessarMenu(String menu) {
        if (!isSideBarOpen())
            sM.getOpenSideBar().click();
        sM.getMenuSideBar(menu).click();
    }

    private boolean isSideBarOpen() {
        try{
            sM.getSideBarOpen();
            printLog("Side bar está aberta.", INFO);
            return true;
        }catch(NoSuchElementException e){
            printLog("Side bar está fechada, abrindo sideBar.", INFO);
            return false;
        }
    }
}
