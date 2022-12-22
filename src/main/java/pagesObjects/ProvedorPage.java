package pagesObjects;

import map.ProvedorMap;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebElement;

import java.util.List;

import static br.com.bb.ath.ftabb.utilitarios.Utils.sleep;
import static support.Utils.printLog;
import static support.enums.LogTypes.INFO;

public class ProvedorPage extends ProvedorMap {
    private final ProvedorMap pM;

    public ProvedorPage() {
        this.pM = new ProvedorMap();
    }

    public List<WebElement> getListProvedores() {
        return pM.getListaProvedores();
    }

    public void fecharAlerta() {
        try {
            pM.getAlertSuccess();
            sleep(1);
            pM.getListBtnFecharAlerta().forEach(WebElement::click);
            printLog("O alerta presente na página foi fechado.", INFO);
        } catch (NoSuchElementException | StaleElementReferenceException e) {
            printLog("Não existe alerta presente na página para ser fechado.", INFO);
        }
    }
}
