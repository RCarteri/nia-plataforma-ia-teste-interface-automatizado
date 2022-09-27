package stepsDefinitions;

import cucumber.api.java.pt.E;
import pagesObjects.ComponentePage;
import pagesObjects.sections.SideBarSection;
import support.Utils;

import static support.enums.LogTypes.INFO;

public class SideBar extends Utils {
    private final SideBarSection sS;

    public SideBar() {
        this.sS = new SideBarSection();
    }

    @E("^acessar o menu \"([^\"]*)\"$")
    public void acessarOMenu(String menu) {
        try {
            ComponentePage cP = new ComponentePage();
            cP.getMensagemAlerta("sucesso");
            cP.clickBtnFechar(false, "alerta");
            sS.acessarProvedor(menu);
            printLog("Menu " + menu + " acessado com sucesso.", INFO);
        } catch (Exception e) {
            logError(e);
        }
    }

    @E("^selecionar o item \"([^\"]*)\"$")
    public void selecionarOItem(String item) {
        try {
            sS.acessarItem(item);
            printLog("Item " + item + " acessado com sucesso.", INFO);
        } catch (Exception e) {
            logError(e);
        }
    }
}
