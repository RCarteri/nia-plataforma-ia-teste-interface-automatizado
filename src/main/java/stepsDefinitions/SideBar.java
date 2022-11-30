package stepsDefinitions;

import cucumber.api.java.pt.E;
import cucumber.api.java.pt.Quando;
import pagesObjects.sections.SideBarSection;
import support.Utils;

import static support.enums.LogTypes.INFO;
import static support.enums.SelectorsDelays.CIRCLE;

public class SideBar extends Utils {
    private final SideBarSection sS;

    public SideBar() {
        this.sS = new SideBarSection();
    }

    @Quando("^acessar os menus \"([^\"]*)\" e \"([^\"]*)\"$")
    public void acessarOsMenus(String menu1, String menu2) {
        try {
            sS.acessarMenu(menu1);
            sS.acessarMenu(menu2);
            printLog("Menus '" + menu1 + "' e '" + menu2 + "' acessados com sucesso." , INFO);
        } catch (Exception e) {
            logError(e);
        }
    }

    @E("^selecionar o item \"([^\"]*)\"$")
    public void selecionarOItem(String item) {
        try {
            sS.acessarMenu(item);
            waitInvisibility(CIRCLE);
            printLog("Item acessado com sucesso: " + item, INFO);
            sS.fecharSideBar();
        } catch (Exception e) {
            logError(e);
        }
    }
}
