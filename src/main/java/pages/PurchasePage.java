package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import base.BasePage;

public class PurchasePage extends BasePage {
    private By flightNumber=By.xpath("//p[contains(text(),'Flight Number')]");
    private By airline=By.xpath("//p[contains(text(),'Airline')]");
    private By price=By.xpath("//p[contains(text(),'Price')]");
    
    private By name=By.id("inputName");
    private By address=By.id("address");
    private By city=By.id("city");
    private By cardNumber=By.id("creditCardNumber");

    private By purchaseBtn=By.cssSelector("input[type='submit']");

    private By header=By.tagName("h2");
    
    public PurchasePage(WebDriver driver) {
        super(driver);
    }
    

    public boolean isFlightDetailsDisplayed() {
        return isDisplayed(flightNumber) && isDisplayed(airline) && isDisplayed(price);
    }
    
    public String getPurchasePrice() {
        String text=getText(price);  
        return text.replaceAll("[^0-9.]", "");
    }
    
    public String getPurchasePriceText() {
        return getText(By.xpath("//p[contains(text(),'Price')]"));
    }
    
    public void fillPassengerDetails(String n, String addr, String c, String card) {
        type(name, n);
        type(address, addr);
        type(city, c);
        type(cardNumber, card);
    }

    public void clickPurchase() {
        click(purchaseBtn);
    }
    
    public void submitWithoutData() {
        click(purchaseBtn);
    }

    public boolean isNameFieldInvalid() {
        WebElement element = waitForVisibility(name);
        String message = element.getAttribute("validationMessage");
        return message != null && !message.isEmpty();
    }

    public void enterInvalidCard(String card) {
        type(cardNumber, card);
    }
    
    public boolean isOnPurchasePage() {
        try {
            waitForVisibility(header);
            return driver.getCurrentUrl().contains("purchase");
        } catch (Exception e) {
            return false;
        }
    }
    
    public String getNameFieldBorderColor() {
        try {
            WebElement element = waitForVisibility(name);
            return element.getCssValue("border-color");
        } catch (Exception e) {
            return "";
        }
    }
    
    public void waitForPageToLoad() {
        waitForVisibility(By.id("inputName"));
    }
}