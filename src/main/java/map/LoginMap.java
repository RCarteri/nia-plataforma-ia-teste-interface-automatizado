package map;

import org.openqa.selenium.WebElement;
import pagesObjects.BasePageObjects;
import static support.GetElements.*;
import static support.enums.SelectorsDelays.LOGIN;

public class LoginMap extends BasePageObjects {
    public WebElement getAlert() {
        return getSeElementoVisivel(".alert");
    }

    public WebElement getInputCodConf() {
        return getSeElementoVisivel(".form-group:nth-child(5) input");
    }

    public WebElement getInputUsername() {
        return waitElement(LOGIN);
    }

    public WebElement getInputPassword() {
        return getElement(".form-group:nth-child(3) input");
    }

    public WebElement getBtnLogin() {
        return getElement("#loginButton_0");
    }

    public WebElement getInputChave() {
        return getElement("#chave");
    }

    public WebElement getInputSenha() {
        return getElement("#senha");
    }

    public WebElement getBtnEntrar() {
        return getElement("#entrar");
    }
}
