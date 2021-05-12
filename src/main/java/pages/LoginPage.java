package pages;

import org.openqa.selenium.By;

public class LoginPage extends Base{

    By loginBtn =By.xpath("//button[normalize-space()='Log in']");

    public void clickLoginBtn()
    {
        clickOn(loginBtn);
    }

}
