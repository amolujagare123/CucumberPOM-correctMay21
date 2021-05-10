package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import org.junit.Assert;
import pages.DarkSkyHome;

import static stepdefinitions.SharedSD.getDriver;

public class DarkskySD {


    DarkSkyHome darkSkyHome = new DarkSkyHome();

    @Given("^I am on Darksky Home Page$")
    public void i_am_on_darksky_home_page()
            {

                Assert.assertEquals("this is not a dark sky page",
                        "Dark Sky - Sansad Marg, New Delhi, Delhi",
                        getDriver().getTitle());
    }

    @Then("^I verify current temp is equal to Temperature from Daily Timeline$")
    public void i_verify_current_temp_is_equal_to_temperature_from_daily_timeline()
    {
        int expected = darkSkyHome.getTimelineTemp();
        int actual = darkSkyHome.getMainTemp();

        System.out.println("Expected="+expected);
        System.out.println("Actual="+actual);


        Assert.assertEquals("teperatures are different",expected,actual);
    }
}
