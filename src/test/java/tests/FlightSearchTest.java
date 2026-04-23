package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.HomePage;
import pages.FlightListPage;

public class FlightSearchTest extends BaseTest {

    //Valid Flight Search
    @Test
    public void verifyFlightSearchResults() {
        HomePage home=new HomePage(getDriver());
        home.selectCities("Boston", "New York");
        home.clickFindFlights();

        FlightListPage flights=new FlightListPage(getDriver());

        Assert.assertTrue(flights.isFlightListDisplayed(),"Flight listing page not displayed");
        Assert.assertTrue(flights.getFlightCount() > 0,"No flights found");
        Assert.assertTrue(flights.verifyFlightDetailsPresent(),"Flight details missing (airline/number/departure/arrival)");
    }

    //Verify each row has proper details
    @Test
    public void verifyFlightDetailsInEachRow() {
        HomePage home=new HomePage(getDriver());
        home.selectCities("Philadelphia", "London");
        home.clickFindFlights();

        FlightListPage flights=new FlightListPage(getDriver());

        Assert.assertTrue(flights.getFlightCount() > 0,"No flights available");
        Assert.assertTrue(flights.verifyFlightDetailsPresent(),"Some flight rows missing required details");
    }

    //Same city validation (based on actual application behavior)
    @Test
    public void verifySameCityNotAvailableInDropdown() {
        HomePage home=new HomePage(getDriver());
        boolean exists=home.isCityPresentInDestination("Boston");

        Assert.assertFalse(exists,"Same city should not be available in destination dropdown");
    }
}