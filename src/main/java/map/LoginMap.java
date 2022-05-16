package map;

import org.openqa.selenium.WebElement;

import static support.GetElements.getElement;
import static support.GetElements.waitElement;
import static support.enums.SelectorsDelays.LOGIN;

public class LoginMap {
    public WebElement getInputUsername() {
        return waitElement("#idToken1", LOGIN);
    }

    public WebElement getInputPassword() {
        return getElement("#idToken2");
    }

    public WebElement getBtnLogin() {
        return getElement("#loginButton_0");
    }
}
