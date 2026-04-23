package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import base.BasePage;

public class HomePage extends BasePage {
    private By fromCity=By.name("fromPort");
    private By toCity=By.name("toPort");
    private By findFlightsBtn=By.cssSelector("input[type='submit']");
    
    public HomePage(WebDriver driver) {
        super(driver);
    }

    public void selectCities(String from, String to) {
        selectByVisibleText(fromCity, from);
        selectByVisibleText(toCity, to);
    }

    public void clickFindFlights() {
        click(findFlightsBtn);
    }
    
    public boolean isCityPresentInDestination(String city) {
        return driver.findElements(
            By.xpath("//select[@name='toPort']/option[text()='" + city + "']")
        ).size() > 0;
    }
}