package stepsDefinitions;

import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Quando;
import pagesObjects.sections.SideBarSection;
import support.Utils;

import static support.Utils.printLog;
import static support.Utils.waitInvisibility;
import static support.enums.LogTypes.INFO;
import static support.enums.SelectorsDelays.CIRCLE;

public class SideBar extends SideBarSection {
    private final Utils utils;

    public SideBar() {
        this.utils = new Utils();
    }

    @Quando("^acessar os menus \"([^\"]*)\" e \"([^\"]*)\"$")
    public void acessarOsMenus(String menu1, String menu2) {
        try {
            acessarMenu(menu1);
            acessarMenu(menu2);
            printLog("Menus '" + menu1 + "' e '" + menu2 + "' acessados com sucesso." , INFO);
        } catch (Exception e) {
            utils.logError(e);
        }
    }

    @E("^selecionar o item \"([^\"]*)\"$")
    public void selecionarOItem(String item) {
        try {
            acessarMenu(item);
            waitInvisibility(CIRCLE);
            printLog("Item acessado com sucesso: " + item, INFO);
            fecharSideBar();
        } catch (Exception e) {
            utils.logError(e);
        }
    }
}
