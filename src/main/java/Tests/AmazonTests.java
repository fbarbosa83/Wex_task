package Tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class AmazonTests {

    private WebDriver driver;

    public AmazonTests(WebDriver browser){
        driver = browser;
    }

    /**
     * Method to search only one Web Element, based on the method you want to search for it
     * @param element the path for this Element
     * @param method the method you want to search for this path
     * @return Web Element found from your HTML
     */
    public WebElement searchElement(String element, String method){
        WebElement elementFound = null;
        switch (method) {
            case "NAME":
                elementFound = driver.findElement(By.name(element));
                break;
            case "CLASS":
                elementFound = driver.findElement(By.className(element));
                break;
            case "CSS":
                elementFound = driver.findElement(By.cssSelector(element));
                break;
            case "ID":
                elementFound = driver.findElement(By.id(element));
                break;
            default :
                elementFound = driver.findElement(By.xpath(element));
        }

        return elementFound;
    }

    /**
     * Method to search a list of Web Elements, based on the method you want to search for it
     * @param element the path for those Element
     * @param method the method you want to search for this path
     * @return a list of Web Elements that correspond to your criteria
     */
    public List<WebElement> searchElements(String element, String method){
        List<WebElement> allElements = null;

        switch (method) {
            case "NAME":
                allElements = driver.findElements(By.name(element));
                break;
            case "CLASS":
                allElements = driver.findElements(By.className(element));
                break;
            case "CSS":
                allElements = driver.findElements(By.cssSelector(element));
                break;
            case "ID":
                allElements = driver.findElements(By.id(element));
                break;
            default :
                allElements = driver.findElements(By.xpath(element));
        }

        return allElements;
    }

    /**
     * Method to write a text into a web element
     * @param element where you want to write
     * @param text text you want to write
     */
    public void write(WebElement element, String text){
        element.sendKeys(text);
    }

    /**
     * Method to submit the value you previosly filled into the page
     * @param element
     */
    public void submit(WebElement element){
        element.submit();
    }

    /**
     * Method needed in order to wait until the page loads
     * A criteria is expected, in order to be sure that the page is fully loaded
     * @param className element that should be visible to ensure that the page is loaded
     * @return validation if the page is loaded correctly
     */
    public Boolean waitForResult(String className){
        Boolean isElementShown = false;
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement resultsConfirm = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(className)));

        if (resultsConfirm != null) {
            isElementShown = true;
        }

        return isElementShown;
    }


    /**
     * Method to get the element equivalent to the price from the web element
     * this web element is one of the results found after searching for a product
     * @param element
     * @param text
     * @return
     */
    public String getPrice(WebElement element, String text) {
        return element.findElement(By.xpath(text)).getText();
    }
}
