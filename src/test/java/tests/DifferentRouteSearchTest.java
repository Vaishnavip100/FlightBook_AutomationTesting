package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.HomePage;
import pages.FlightListPage;

public class DifferentRouteSearchTest extends BaseTest {

    @DataProvider(name="routes")
    public Object[][] getRoutes() {
        return new Object[][]{
                {"Boston", "New York"},
                {"Philadelphia", "London"},
                {"San Diego", "Berlin"}
        };
    }

    //Verify results + correct page title
    @Test(dataProvider="routes")
    public void verifyResultsForDifferentRoutes(String from, String to) {

        HomePage home=new HomePage(getDriver());
        home.selectCities(from, to);
        home.clickFindFlights();

        FlightListPage flights=new FlightListPage(getDriver());

        Assert.assertTrue(flights.getFlightCount() > 0,"No flights found for route: " + from + " → " + to);
        String title=flights.getPageTitleText().toLowerCase();

        Assert.assertTrue(title.contains("reserve"),"Incorrect page title for route: " + from + " → " + to);
    }

    //Verify flight count consistency
    @Test
    public void verifyFlightCountConsistency() {
        HomePage home=new HomePage(getDriver());

        home.selectCities("Boston", "New York");
        home.clickFindFlights();

        FlightListPage flights=new FlightListPage(getDriver());
        int count1=flights.getFlightCount();

        goToHomePage();
        
        home.selectCities("Philadelphia", "London");
        home.clickFindFlights();

        int count2=flights.getFlightCount();

        Assert.assertTrue(count1 > 0 && count2 > 0,"Flights should be available for both routes");
        Assert.assertEquals(count1, count2,"Demo app returns same number of flights");
    }
}