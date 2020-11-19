package Pages;

import Support.Paths;
import Tests.AmazonTests;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import java.util.ArrayList;
import java.util.List;

public class AmazonPage extends WebPage{
    private final AmazonTests test;
    private final Paths paths;
    private WebElement element;

    public AmazonPage(WebDriver browser){
        super(browser);
        super.setUrl("https://www.amazon.com.br");
        test = new AmazonTests(browser);
        paths = new Paths();
    }

    /**
     * Method to write the product you want to search into Amazon's search bar
     * @param product searched keyword
     */
    public void searchProduct(String product){
        element = test.searchElement(paths.idAmazonTextBox(), "ID");
        test.write(element, product);
    }

    /**
     * Method to submit, after writing something at the page
     */
    public void submit(){
        test.submit(element);
    }

    /**
     * Method to get all the products into the results page at Amazon
     * after you search for a product
     * @return List of all the products found
     */
    public List<WebElement> getAllProductsFound(){
        return test.searchElements(paths.classAmazonResults(), "XPATH");
    }

    /**
     * Method to get the name of all the products from the results page of Amazon
     * after you search for a product
     * @return List of all the names from the products found
     */
    public List<String> getAllProductNames(){
        List<String> products = new ArrayList<>();
        List<WebElement> allResults = test.searchElements(paths.classAmazonProductName(), "XPATH");
        for(WebElement result : allResults){
            products.add(result.getAttribute("innerHTML"));
        }
        return products;
    }

    /**
     * Method to calculate the price of a product you specify
     * This class is important because the price, in Amazon, is separated in main value (whole price)
     * and cents (fraction price)
     * Also, in Amazon Brazil, the main value is separated by dot, which needs a logic to set as Double
     * @param element product you want to find the price
     * @return the price, formatted in Double
     */
    public Double getPrice(WebElement element) {
        String wholePriceIdAddress = paths.getPriceWhole();
        String fractionPriceAddress = paths.getPriceFraction();

        String priceWhole = test.getPrice(element, wholePriceIdAddress);
        priceWhole = priceWhole.replaceAll("\\D+","");
        String priceFraction = test.getPrice(element, fractionPriceAddress);

        String totalPrice = priceWhole + "." + priceFraction;
        return Double.parseDouble(totalPrice);
    }
}
