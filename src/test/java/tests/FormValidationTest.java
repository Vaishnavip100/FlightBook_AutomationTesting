package tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import base.BaseTest;
import pages.*;

public class FormValidationTest extends BaseTest {

    private PurchasePage navigateToPurchasePage() {
        HomePage home=new HomePage(getDriver());
        home.selectCities("Boston", "New York");
        home.clickFindFlights();

        FlightListPage flights=new FlightListPage(getDriver());
        flights.selectFlightByAirline("United Airlines");

        PurchasePage purchase=new PurchasePage(getDriver());
        purchase.waitForPageToLoad();
        return purchase;
    }

    //Empty Name validation
    @Test
    public void verifyEmptyNameBehavior() {
        PurchasePage purchase=navigateToPurchasePage();

        purchase.fillPassengerDetails("", "Street 1", "Vijayawada", "123456");
        purchase.clickPurchase();

        ConfirmationPage confirm=new ConfirmationPage(getDriver());

        Assert.assertTrue(confirm.isConfirmationPageLoaded(),"Booking should succeed even with empty name (demo behavior)");
    }

    //Invalid card behavior
    @Test
    public void verifyInvalidCardValidation() {
        PurchasePage purchase=navigateToPurchasePage();

        purchase.fillPassengerDetails("Vaishnavi", "Street 1", "Vijayawada", "abc123");
        purchase.clickPurchase();

        ConfirmationPage confirm=new ConfirmationPage(getDriver());
        Assert.assertTrue(confirm.isConfirmationPageLoaded(),"Flow should proceed even with invalid card");
    }

    //Mandatory fields validation
    @Test
    public void verifyMandatoryFieldValidation() {
        PurchasePage purchase=navigateToPurchasePage();
        purchase.submitWithoutData();

        ConfirmationPage confirm=new ConfirmationPage(getDriver());
        Assert.assertTrue(confirm.isConfirmationPageLoaded(),"Form should submit even without mandatory fields (demo behavior)");
    }
}