package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static stepdefinitions.SharedSD.getDriver;

public class BookingSearchResult extends  Base {

    By starRating = By.xpath("//span[contains(@aria-label,'out of')]");

    public ArrayList<String> getStarList()
    {

        getDriver().manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);

        List<WebElement> starListElement =  getDriver().findElements(starRating);

        ArrayList<String> starList = new ArrayList<>();

        for(int i=0;i<starListElement.size();i++)
        {
            String starRow = starListElement.get(i).getAttribute("aria-label"); //5 out of 5
            System.out.println(starRow);

            String star = starRow.split(" ")[0];
            starList.add(star);
        }

        return starList;
    }



    public void clickStarRatings(String star) // 5, 4, 3, 2
    {
        By starElement =  By.xpath("//div[@class='filteroptions']//a[@data-id='class-"+star+"' and @data-value='"+star+"']");

        getDriver().findElement(starElement).click();
    }

}
