package pagesObjects.sections;

import map.SideBarMap;

import static support.Utils.waitLoadPage;
import static support.enums.SelectorsDelays.BLOCKUI;

public class SideBarSection {
    private final SideBarMap sM = new SideBarMap();

    public void acessarMenus(String menu1, String menu2) {
        waitLoadPage(BLOCKUI);
        sM.getOpenSideBar().click();
        sM.getMenu(menu1).click();
        sM.getMenu(menu2).click();
    }

    public void acessarItem(String item) {
        sM.getMenu(item).click();
    }
}
