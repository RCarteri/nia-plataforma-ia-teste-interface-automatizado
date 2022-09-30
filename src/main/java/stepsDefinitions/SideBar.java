package stepsDefinitions;

import cucumber.api.java.pt.E;
import pagesObjects.ComponentePage;
import pagesObjects.sections.SideBarSection;
import support.Utils;

import static support.enums.LogTypes.INFO;

public class SideBar extends Utils {
    private final SideBarSection sS;
    private final ComponentePage cP;

    public SideBar() {
        this.sS = new SideBarSection();
        this.cP = new ComponentePage();
    }

    @E("^acessar os menus \"([^\"]*)\" e \"([^\"]*)\"$")
    public void acessarOsMenus(String menu1, String menu2) {
        try {
            cP.getMensagemAlerta("sucesso");
            cP.clickBtnFechar(false, "alerta");
            sS.acessarMenus(menu1, menu2);
            printLog("Menus acessados com sucesso: " + menu1 + " " + menu2, INFO);
        } catch (Exception e) {
            logError(e);
        }
    }

    @E("^selecionar o item \"([^\"]*)\"$")
    public void selecionarOItem(String item) {
        try {
            sS.acessarItem(item);
            printLog("Item acessado com sucesso: " + item, INFO);
        } catch (Exception e) {
            logError(e);
        }
    }
}
