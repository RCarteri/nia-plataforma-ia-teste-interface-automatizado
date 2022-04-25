package map;

import org.openqa.selenium.WebElement;
import support.annotations.FindBy;

public class LoginMap extends BasePageObjects{
    @FindBy(cssSelector = "#idToken1")
    private WebElement inputUsername;

    @FindBy(cssSelector = "#idToken2")
    private WebElement inputPassword;

    @FindBy(cssSelector = "#loginButton_0")
    private WebElement btnLogin;

    public WebElement getInputUsername() {
        if (inputUsername == null)
            inputUsername = setElement("inputUsername");
        return inputUsername;
    }

    public WebElement getInputPassword() {
        if (inputPassword == null)
            inputPassword = setElement("inputPassword");
        return inputPassword;
    }

    public WebElement getBtnLogin() {
        if (btnLogin == null)
            btnLogin = setElement("btnLogin");
        return btnLogin;
    }
}
