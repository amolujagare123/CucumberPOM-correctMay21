package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.DarkSkyAPI;
import pages.DarkSkyHome;
import pages.LoginPage;

import java.util.ArrayList;
import java.util.Collections;

import static stepdefinitions.SharedSD.getDriver;

public class DarkskySD {


    DarkSkyHome darkSkyHome = new DarkSkyHome();
    DarkSkyAPI darkSkyAPI = new DarkSkyAPI();
    LoginPage loginPage = new LoginPage();

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

    @Then("^I verify timeline is displayed with two hours incremented$")
    public void i_verify_timeline_is_displayed_with_two_hours_incremented()
            {
                ArrayList<Integer> timeList =   darkSkyHome.getTimeList();
                // [10, 12, 2, 4, 6, 8, 10, 12, 2, 4, 6] --> 11 elements
                ArrayList<Integer> timeDiffList = new ArrayList<>();

                for(int i=0;i<timeList.size()-1;i++)
                {
                    int time1 = timeList.get(i);
                    int time2 = timeList.get(i+1);
                    int timeDiff = 0;

                    if(time2>time1)
                      timeDiff = time2-time1;

                    if(time1>time2)
                        timeDiff = (time2+12) -time1;

                    timeDiffList.add(timeDiff);

                }

                System.out.println(timeDiffList);

                int size = timeDiffList.size();
                int occarance = Collections.frequency(timeDiffList,2);

                boolean result = (size==occarance); // true or false

                Assert.assertTrue("all the differences are not 2",
                                result);

    }

    @Then("^I verify today's lowest and highest temp is displayed correctly$")
    public void i_verify_todays_lowest_and_highest_temp_is_displayed_correctly()
            {
                darkSkyHome.clickTodayExpander();

                ArrayList<String> expected = darkSkyHome.getBarTempList();
                ArrayList<String> actual = darkSkyHome.getTimelineTempList();

                System.out.println("Expected"+expected);

                System.out.println("Actual"+actual);

                Assert.assertEquals("temperatures are not correct",expected,actual);
            }



    @Given("^I am on the darksky Login page$")
    public void i_am_on_the_darksky_login_page() throws Throwable {

        darkSkyHome.clickDarkSkyAPILnk();
        darkSkyAPI.clickLoginLnk();
    }

    @When("^I click on Login button$")
    public void i_click_on_login_button() throws Throwable {

        loginPage.clickLoginBtn();
    }

    @Then("^I verify I am on Login page by asserting Login page title$")
    public void i_verify_i_am_on_login_page_by_asserting_login_page_title() throws Throwable {

        String expected = "Dark Sky API: Log In";
        String actual = getDriver().getTitle();

        System.out.println("Expected = "+expected);
        System.out.println("Actual = "+actual);

        Assert.assertEquals("this is not a login page",expected,actual);

    }

}
