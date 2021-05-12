package pages;

import org.openqa.selenium.By;

public class DarkSkyAPI extends Base {

    By loginLink =By.xpath("//a[normalize-space()='Log In']");

    public void clickLoginLnk()
    {
        clickOn(loginLink);
    }
}
