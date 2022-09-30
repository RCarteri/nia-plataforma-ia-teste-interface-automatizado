package pagesObjects.sections;

import map.SideBarMap;

public class SideBarSection {
    private final SideBarMap sM = new SideBarMap();

    public void acessarMenus(String menu1, String menu2) {
        sM.getOpenSideBar().click();
        sM.getMenuSideBar(menu1).click();
        sM.getMenuSideBar(menu2).click();
    }

    public void acessarItem(String item) {
        sM.getMenuSideBar(item).click();
    }
}
