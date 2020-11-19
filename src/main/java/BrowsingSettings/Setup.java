package BrowsingSettings;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;


public class Setup {

    private WebDriver browser;

    public Setup(){

        ChromeOptions options = new ChromeOptions();
        options.addArguments("start-maximized");
        options.addArguments("enable-automation");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-infobars");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--disable-browser-side-navigation");
        options.addArguments("--disable-gpu");
        browser = new ChromeDriver(options);
    }

    /**
     *
     * @return WebDriver in order to be accessed by pages and tests
     */
    public WebDriver getBrowser(){
        return browser;
    }

}
