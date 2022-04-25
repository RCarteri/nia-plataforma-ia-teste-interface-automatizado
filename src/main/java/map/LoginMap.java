package map;

import org.openqa.selenium.WebElement;

import static support.GetElements.getElement;

public class LoginMap {
    public WebElement getInputUsername() {
        return getElement("#idToken1");
    }

    public WebElement getInputPassword(){
        return getElement("#idToken2");
    }

    public WebElement getBtnLogin(){
        return getElement("#loginButton_0");
    }
}
