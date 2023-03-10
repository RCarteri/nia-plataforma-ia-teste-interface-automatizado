package pagesObjects.sections;

import map.SideBarMap;
import org.openqa.selenium.NoSuchElementException;

import static support.GetElements.clicarComJS;
import static support.Utils.printLog;
import static support.Utils.waitInvisibility;
import static support.enums.LogTypes.INFO;
import static support.enums.SelectorsDelays.SPINNER;

public class SideBarSection {
    private final SideBarMap sM = new SideBarMap();

    public void acessarMenu(String menu) {
        if (!isSideBarOpen())
            clicarComJS(sM.getOpenSideBar());
        sM.getMenuSideBar(menu).click();
    }

    private boolean isSideBarOpen() {
        try {
            sM.getSideBarOpen();
            printLog("Side bar está aberta.", INFO);
            return true;
        } catch (NoSuchElementException e) {
            printLog("Side bar está fechada, abrindo sideBar.", INFO);
            return false;
        }
    }

    public void fecharSideBar() {
        if (sM.getBtnFecharSidebar().isDisplayed()) {
            waitInvisibility(SPINNER);
            sM.getBtnFecharSidebar().click();
        }
    }
}
