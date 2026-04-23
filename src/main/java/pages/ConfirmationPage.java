package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BasePage;

public class ConfirmationPage extends BasePage {
    private By thankYouMsg=By.tagName("h1");
    private By bookingId=By.xpath("//table//tr[1]/td[2]");

    public ConfirmationPage(WebDriver driver) {
        super(driver);
    }
    
    public boolean isThankYouDisplayed() {
        return getText(thankYouMsg).contains("Thank you");
    }

    public String getBookingId() {
        return getText(bookingId);
    }

    public boolean isConfirmationPageLoaded() {
        return isDisplayed(thankYouMsg);
    }
}