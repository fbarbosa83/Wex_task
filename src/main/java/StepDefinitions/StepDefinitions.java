package StepDefinitions;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import BrowsingSettings.Setup;
import Pages.AmazonPage;
import Pages.GooglePage;
import Pages.WebPage;
import Support.RestRequests;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import sun.font.TrueTypeFont;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class StepDefinitions {

    private WebDriver wd;
    private WebPage wp;

    @Before
    public void setUp() throws Exception {
        Setup chromeSetup = new Setup();
        wd = chromeSetup.getBrowser();
    }


    @Given("^I open browser on (.*?)$")
    public void openBrowser(String url) throws Throwable {
        switch (url) {
            case "Google":
                wp = new GooglePage(wd);
                break;
            case "Amazon":
                wp = new AmazonPage(wd);
                break;
            default :
                wp = new WebPage(wd);
                wp.setUrl("https://" + url);
        }
        wp.navigateToPage();
    }


    @When("^I search for \"([^\"]*)\"$")
    public void searchFor(String keyword) throws Throwable {

        GooglePage gp = (GooglePage) wp;
        gp.selectSearchBar();
        gp.writeKeywordToSearch(keyword);
        Boolean hasResults = gp.confirmSearch();
        if(!hasResults){
            throw new Exception("No matches");
        }
    }


    @Then("^I navigate to \"([^\"]*)\"$")
    public void navigateTo(String searchedURL) throws Throwable {

        GooglePage gp = (GooglePage) wp;
        List<WebElement> googleResults = gp.getResults();

        WebElement chosenResult = null;
        searchedURL = "https://" + searchedURL;
        for (WebElement result : googleResults)
        {
            if(result.getAttribute("href").contains(searchedURL)){
                chosenResult = result;
                break;
            }
        }

        gp.clickOnResult(chosenResult);
        Boolean isValid = false;
        if(gp.getCurrentUrl().contains(searchedURL)){
            isValid = true;
        }

        assertTrue(isValid);
        wp.closeBrowser();
    }

    @Then("^I search \"([^\"]*)\"$")
    public void searchIphone(String keyword) throws Throwable{

        AmazonPage ap = (AmazonPage) wp;
        ap.searchProduct(keyword);
        ap.submit();
    }

    @Then("^I count the total list of found products$")
    public void countProducts() throws Throwable{

        AmazonPage amazonPage = (AmazonPage) wp;
        List<WebElement> allResults = amazonPage.getAllProductsFound();

        if(allResults.size() == 0){
            throw new Exception("No matches");
        } else {
            String quantityOfResults = String.valueOf(allResults.size());
            System.out.println("Total list of found products: " + quantityOfResults);
            System.out.println("------------------------------------------------------------------");
        }
        wp.closeBrowser();
    }

    @Then("^I count the items which its name starts with \"([^\"]*)\"$")
    public void countItemsStarted(String keyword) throws Throwable{

        AmazonPage ap = (AmazonPage) wp;
        List<String> allResults = ap.getAllProductNames();
        int counter = 0;
        for(String productName : allResults){
            if(productName.toLowerCase().startsWith(keyword.toLowerCase())){
                counter++;
            }
        }

        if(counter == 0){
            throw new Exception("No matches");
        } else {

        }
    }

    @Then("^I make sure \"([^\"]*)\"% or more of the result list contains the keyword \"([^\"]*)\"$")
    public void countContains(int percentage, String keyword) throws Throwable{

        AmazonPage ap = (AmazonPage) wp;
        List<String> allResults = ap.getAllProductNames();
        int counter = 0;
        for(String productName : allResults){
            if(productName.toLowerCase().contains(keyword.toLowerCase())){
                counter++;
            }
        }

        int targetSize = (allResults.size() * percentage)/100;
        System.out.println(percentage + "% quantity is: " + targetSize);
        System.out.println("Number of items which contains the keyword " + keyword + ": " + counter);
        System.out.println("------------------------------------------------------------------");
        Boolean isValid = false;
        if(counter >= targetSize){
            isValid = true;
        }
        assertTrue(isValid);

    }

    @Then("^I make sure \"([^\"]*)\"% or more of Items found has its name starting with \"([^\"]*)\"$")
    public void countStartedWith(int percentage, String keyword) throws Throwable{

        AmazonPage ap = (AmazonPage) wp;
        List<String> allResults = ap.getAllProductNames();
        int counter = 0;
        for(String productName : allResults){
            if(productName.toLowerCase().startsWith(keyword.toLowerCase())){
                counter++;
            }
        }

        int targetSize = (allResults.size() * percentage)/100;
        System.out.println(percentage + "% quantity is: " + targetSize);
        System.out.println("Number of items which its name starts with " + keyword + ": " + counter);
        System.out.println("------------------------------------------------------------------");
        Boolean isValid = false;
        if(counter >= targetSize){
            isValid = true;
        }
        assertTrue(isValid);


    }


    @Then("^I find the most expensive item which its name starts with \"([^\"]*)\" and converted value must not be greater than US\"([^\"]*)\"$")
    public void findMoreExpensiveStartedWith(String keyword, double targetPrice) throws Throwable{

        AmazonPage ap = (AmazonPage) wp;
        List<WebElement> allResults = ap.getAllProductsFound();
        List<String> allProductsName = ap.getAllProductNames();

        double iphonePrice = 0.00;
        String pn = "";
        for(int i = 0; i < allResults.size(); i++){
            String productName = allProductsName.get(i);
            WebElement product = allResults.get(i);

            if(productName.toLowerCase().startsWith(keyword.toLowerCase())){

                try {
                    Double price = ap.getPrice(product);
                    if (price > iphonePrice) {
                        iphonePrice = price;
                        pn = productName;
                    }

                } catch (org.openqa.selenium.NoSuchElementException exception) {
                }

            }
        }
        System.out.println("------------------------------------------------------------------");
        System.out.println("Most expensive item which its name starts with Iphone is: ");
        System.out.println(pn + ". It costs R$" + iphonePrice);
        System.out.println("------------------------------------------------------------------");


        Boolean isValid = false;
        if(iphonePrice > 0.00){
            isValid = true;
        }

        assertTrue(isValid);
        RestRequests rest = new RestRequests();
        double convertedValue = rest.convertPriceToDollar(iphonePrice);
        DecimalFormat numberFormat = new DecimalFormat("#.00");
        System.out.println("Converted value: US$ " + numberFormat.format(convertedValue));
        System.out.println("------------------------------------------------------------------");
        if (convertedValue > targetPrice){
            isValid = false;
        }
        assertTrue(isValid);
        wp.closeBrowser();
    }


    @Then("^I find items which its name doesn't start with \"([^\"]*)\" and make sure the most expensive is cheaper than the cheapest item which its name starts with \"([^\"]*)\"$")
    public void findProductsNotStartedWithIphone(String keyword, String keyword2) throws Throwable{

        AmazonPage ap = (AmazonPage) wp;
        List<WebElement> allResults = ap.getAllProductsFound();
        List<String> allProductsName = ap.getAllProductNames();

        double cheapestIphonePrice = 999999.9;
        double nonIphoneMostExpensivePrice = 0.0;
        List<WebElement> nonStartedIphoneElements = new ArrayList<>();

        for(int i = 0; i < allResults.size(); i++){
            String productName = allProductsName.get(i);
            WebElement product = allResults.get(i);

            if(productName.toLowerCase().startsWith(keyword.toLowerCase())){

                try {
                    Double price = ap.getPrice(product);

                    if (price < cheapestIphonePrice) {
                        cheapestIphonePrice = price;
                    }

                } catch (org.openqa.selenium.NoSuchElementException exception) {
                }

            } else {
                nonStartedIphoneElements.add(product);
                try {
                    Double price = ap.getPrice(product);

                    if (price > nonIphoneMostExpensivePrice) {
                        nonIphoneMostExpensivePrice = price;
                    }
                } catch (org.openqa.selenium.NoSuchElementException exception) {
                }
            }

        }

        Boolean isValid = true;
        if(nonIphoneMostExpensivePrice < cheapestIphonePrice){
            isValid = true;
        }
        System.out.println("------------------------------------------------------------------");
        System.out.println("Cheapest item started with Iphone is: " + cheapestIphonePrice);
        System.out.println("------------------------------------------------------------------");
        System.out.println("Most expensive item not started with Iphone is: " + nonIphoneMostExpensivePrice);
        assertTrue(isValid);
        wp.closeBrowser();
    }

}
