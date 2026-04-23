package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.*;

public class PurchaseTest extends BaseTest {

    @Test
    public void verifyPurchaseFlow() {
        HomePage home = new HomePage(getDriver());
        home.selectCities("Boston", "New York");
        home.clickFindFlights();

        FlightListPage flights=new FlightListPage(getDriver());
        flights.selectFlightByAirline("Lufthansa");
        
        PurchasePage purchase=new PurchasePage(getDriver());

        purchase.fillPassengerDetails("Vaishnavi Perumalla","Street 1","Vijayawada","123456789");

        purchase.clickPurchase();

        ConfirmationPage confirm=new ConfirmationPage(getDriver());
        Assert.assertTrue(confirm.isConfirmationPageLoaded(),"Confirmation page not loaded");
        Assert.assertTrue(confirm.isThankYouDisplayed(),"Thank you message not displayed");

        String id = confirm.getBookingId();
        Assert.assertTrue(id != null && !id.isEmpty(),"Booking ID not generated");
    }
}