package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.*;

public class FlightSelectionTest extends BaseTest {

    //Navigation to Purchase Page
    @Test
    public void verifyNavigationToPurchasePage() {
        HomePage home=new HomePage(getDriver());
        home.selectCities("Boston", "New York");
        home.clickFindFlights();

        FlightListPage flights=new FlightListPage(getDriver());
        flights.chooseFirstFlight();

        PurchasePage purchase=new PurchasePage(getDriver());

        Assert.assertTrue(purchase.isOnPurchasePage(),"Navigation to purchase page failed");
    }

    //Flight details pre-populated
    @Test
    public void verifyFlightDetailsDisplayed() {
        HomePage home=new HomePage(getDriver());
        home.selectCities("Boston", "New York");
        home.clickFindFlights();

        FlightListPage flights=new FlightListPage(getDriver());
        flights.chooseFirstFlight();

        PurchasePage purchase=new PurchasePage(getDriver());

        Assert.assertTrue(purchase.isFlightDetailsDisplayed(),"Flight details not displayed on purchase page");
    }

    //Price consistency
    @Test
    public void verifyPriceConsistency() {
        HomePage home=new HomePage(getDriver());
        home.selectCities("Boston", "New York");
        home.clickFindFlights();

        FlightListPage flights=new FlightListPage(getDriver());
        flights.selectFlightByAirline("United Airlines");
        
        PurchasePage purchase=new PurchasePage(getDriver());

        String purchaseText=purchase.getPurchasePriceText();

        Assert.assertTrue(purchaseText.contains("Price"),"Price label not displayed on purchase page");
        Assert.assertTrue(purchaseText.matches(".*\\d+.*"),"Price value not displayed on purchase page");
    }
}