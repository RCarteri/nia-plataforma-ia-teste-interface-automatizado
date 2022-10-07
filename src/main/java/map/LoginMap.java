package map;

import org.openqa.selenium.WebElement;

import static support.GetElements.getElement;
import static support.GetElements.waitElement;
import static support.enums.SelectorsDelays.LOGIN;

public class LoginMap {
    public WebElement getInputUsername() {
        return waitElement(LOGIN);
    }

    public WebElement getInputPassword() {
        return getElement("#idToken2");
    }

    public WebElement getInputCodConf() {
        return getElement("#idToken3");
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
