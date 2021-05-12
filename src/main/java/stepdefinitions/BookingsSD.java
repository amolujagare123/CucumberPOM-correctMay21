package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import pages.BookingSearchResult;

import java.util.ArrayList;
import java.util.Collections;

import static stepdefinitions.SharedSD.getDriver;

public class BookingsSD {

    BookingSearchResult bookingSearchResult = new BookingSearchResult();

    @Given("^I am on default locations search result screen$")
    public void i_am_on_default_locations_search_result_screen() throws Throwable {

        String expected ="Booking.com : Hotels in Mumbai . Book your hotel now!";

        String actual = getDriver().getTitle();
        System.out.println(actual);

        Assert.assertEquals("this is not a serarch result page",expected,actual);

    }

    @When("^I select property class (.+)$")
    public void i_select_property_class(String stars) throws Throwable {

        // stars --> 5 stars

        bookingSearchResult.clickStarRatings(stars.split(" ")[0]);

    }

    @Then("^I verify system displays only (.+) hotels on search result$")
    public void i_verify_system_displays_only_hotels_on_search_result(String stars) throws Throwable {

        ArrayList<String> starList = bookingSearchResult.getStarList();

        System.out.println(starList);

        int size = starList.size();
        int occurance = Collections.frequency(starList,stars.split(" ")[0]);


        Assert.assertTrue("All the ratings are not-"+stars,(size==occurance));

    }

}
