package Support;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import java.net.HttpURLConnection;
import java.net.URL;
import java.io.BufferedReader;
import java.io.InputStreamReader;


public class RestRequests {

    private String endPoint;

    public RestRequests(){
        endPoint = "https://api.exchangeratesapi.io/";
    }

    /**
     * Method to convert a price from Real to its equivallent in US Dollar
     * @param priceInReal
     * @return princeInDollar
     * @throws Throwable
     */
    public double convertPriceToDollar(double priceInReal) throws Throwable {
        double priceInDollar = priceInReal / getDollarRate();
        return priceInDollar;
    }

    /**
     * Method to make the access to the API
     * @return API response
     * @throws Throwable
     */
    public String consumeFromAPI() throws Throwable{
        endPoint = endPoint + "latest?base=USD";
        StringBuilder result = new StringBuilder();
        URL url = new URL(endPoint);
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String line;
        while ((line = rd.readLine()) != null) {
            result.append(line);
        }
        rd.close();
        return result.toString();
    }

    /**
     * Method to get the Real rate, from the API return, to later divide to get the value in dollar
     * @return double Real rate for Dollar
     * @throws Throwable
     */
    public double getDollarRate() throws Throwable{
        String apiResult = consumeFromAPI();
        JSONParser parser = new JSONParser();
        JSONObject json = (JSONObject) parser.parse(apiResult);
        JSONObject rates = (JSONObject) json.get("rates");
        Double BRL = (Double) rates.get("BRL");
        return BRL;
    }
}
