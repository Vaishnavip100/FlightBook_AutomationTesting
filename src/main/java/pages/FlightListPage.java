package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import base.BasePage;

import java.util.List;

public class FlightListPage extends BasePage {
    private By flightRows=By.xpath("//table[@class='table']//tbody/tr");
    private By airline=By.xpath("//table//tr/td[3]");
    private By flightNumber=By.xpath("//table//tr/td[2]");
    private By departure=By.xpath("//table//tr/td[4]");
    private By arrival=By.xpath("//table//tr/td[5]");
    
    private By firstRowPrice = By.xpath("(//table[@class='table']//tbody/tr)[1]/td[6]");
    private By firstRowChooseBtn = By.xpath("(//table[@class='table']//tbody/tr)[1]/td[1]/input");
    
    public FlightListPage(WebDriver driver) {
        super(driver);
    }

    public boolean isFlightListDisplayed() {
        try {
            waitForVisibility(flightRows);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public boolean verifyFlightDetailsPresent() {
        return driver.findElements(airline).size() > 0 &&
               driver.findElements(flightNumber).size() > 0 &&
               driver.findElements(departure).size() > 0 &&
               driver.findElements(arrival).size() > 0;
    }
    
    public String getFirstFlightPrice() {
        String price = getText(firstRowPrice);
        return price.replace("$", "").trim();
    }

    public void chooseFirstFlight() {
        click(firstRowChooseBtn);
    }
    
//    public String selectFirstFlightAndGetPrice() {
//        waitForVisibility(By.xpath("//table[@class='table']//tbody/tr"));
//        List<WebElement> rows = driver.findElements(By.xpath("//table[@class='table']//tbody/tr"));
//        WebElement row=rows.get(0);
//        String price=row.findElement(By.xpath("td[6]")).getText();
//        price=price.replace("$", "").trim();
//
//        row.findElement(By.xpath("td[1]/input")).click();
//
//	     wait.until(driver -> driver.getCurrentUrl().contains("purchase") && driver.getPageSource().contains("Your flight"));
//
//     return price;
//    }
    
    public String selectFlightByAirline(String airlineName) {
        waitForVisibility(flightRows);
        List<WebElement> rows=driver.findElements(flightRows);

        for (WebElement row : rows) {

            String airlineText=row.findElement(By.xpath("td[3]")).getText();
            if (airlineText.equalsIgnoreCase(airlineName)) {
                String price=row.findElement(By.xpath("td[6]")).getText().replace("$", "").trim();
                row.findElement(By.xpath("td[1]/input")).click();
                wait.until(d -> d.getCurrentUrl().contains("purchase"));
                return price;
            }
        }

        throw new RuntimeException("Airline not found: " + airlineName);
    }
    
    public int getFlightCount() {
        waitForVisibility(By.xpath("//table[@class='table']//tbody/tr"));
        return driver.findElements(By.xpath("//table[@class='table']//tbody/tr")).size();
    }

    public String getPageTitleText() {
        return getPageTitle();
    }    
}