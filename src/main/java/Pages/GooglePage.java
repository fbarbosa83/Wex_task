package Pages;

import Support.Paths;
import Tests.GoogleTests;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class GooglePage extends WebPage{

    private GoogleTests test;
    private Paths paths;
    private WebElement element;

    public GooglePage(WebDriver browser){
        super(browser);
        super.setUrl("https://www.google.com");
        test = new GoogleTests(browser);
        paths = new Paths();
    }

    /**
     * Method to select the search bar from Google page
     */
    public void selectSearchBar(){
        element = test.searchElement(paths.nameGoogleSearchBar(), "NAME");
        test.clickElement(element);
    }

    /**
     * Method to write something at the search bar of Google page
     * @param keyword
     */
    public void writeKeywordToSearch(String keyword){
        test.write(element, keyword);
    }

    /**
     * Method to submit the keyword and wait until the results show up
     * @return hasFoundResults
     */
    public Boolean confirmSearch(){
        test.submit(element);
        element = test.searchElement(paths.idGoogleResults(), "ID");
        Boolean hasFoundResults = test.waitElement(element);
        return hasFoundResults;
    }

    /**
     * Method to get all the results from Google Results
     * @return allResults
     */
    public List<WebElement> getResults(){
        List<WebElement> allResults = test.searchElements(paths.xpathGoogleEachResult(), "XPATH");
        return allResults;
    }

    /**
     * Method to click in an element into Google Results
     * @param element that should be clicked
     */
    public void clickOnResult(WebElement element){
        test.clickElement(element);
    }
}
