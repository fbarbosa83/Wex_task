package Pages;

import org.openqa.selenium.WebDriver;

public class WebPage {
    protected static WebDriver browser;
    protected static String url;

    public WebPage(){
    }

    public WebPage(WebDriver page){
        this.browser = page;
    }

    /**
     * Set URL parameter
     * @param url the URL that you'll want to navigate to
     */
    public void setUrl(String url){
        this.url = url;
    }

    /**
     * Method to navigate to a specific page, determined by the attribute URL
     */
    public void navigateToPage(){
        browser.get(url);
    }

    /**
     * Method to get the current URL that the browser is
     * for validation if you are in the correct page
     * @return
     */
    public String getCurrentUrl(){
        return this.browser.getCurrentUrl();
    }

    /**
     * Method to finish the test and close the browser
     */
    public void closeBrowser(){
        browser.quit();
    }
}
