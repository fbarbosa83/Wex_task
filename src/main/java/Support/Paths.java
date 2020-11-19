package Support;

public class Paths {
    public Paths(){
    }

    public String nameGoogleSearchBar(){
        return "q";
    }

    public String idGoogleResults(){
        return "result-stats";
    }

    public String xpathGoogleEachResult(){
        return "//*[@class='r']/a";
    }

    public String idAmazonTextBox(){
        return "twotabsearchtextbox";
    }

    public String idAmazonResultsPage(){
        return "s-refinements";
    }

    public String classAmazonResults(){
//        return "//*[@cel_widget_id ='MAIN-SEARCH_RESULTS-0']";
        return "//*[@class = 's-expand-height s-include-content-margin s-border-bottom s-latency-cf-section']";
    }

    public String classAmazonProductName(){
        return "//*[@class ='a-size-base-plus a-color-base a-text-normal']";
    }

    public String getPriceWhole(){
        return ".//*[@class='a-price-whole']";
    }

    public String getPriceFraction(){
        return ".//*[@class='a-price-fraction']";
    }
}
