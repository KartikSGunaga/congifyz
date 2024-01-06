package task04;

import org.json.*;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;
import java.util.TimeZone;

public class CurrencyConverter {
    private final String baseCurrency, targetCurrency, key;
    static Scanner scanner = new Scanner(System.in);

    public CurrencyConverter(String key, String baseCurrency, String targetCurrency){
        this.baseCurrency = baseCurrency;
        this.targetCurrency = targetCurrency;
        this.key = key;
    }

    public String fetchCurrencyValue(double amount) throws Exception {
        String timeStamp = null;
        HttpClient client = HttpClient.newHttpClient();

        String url = "https://v6.exchangerate-api.com/v6/"
                + this.key + "/pair/" + this.baseCurrency + "/"
                + this.baseCurrency + "/"+ amount;

        URI uri = new URI(url);

        HttpRequest request = HttpRequest.newBuilder().uri(uri).GET().build();
        HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

        if(response.statusCode() == 200){
            JSONObject obj = new JSONObject(response.body());

            double rate = obj.getDouble("conversion_rate");
            double conversion_result = obj.getDouble("conversion_result");

            timeStamp = obj.getString("time_last_update_utc");

            System.out.println("The conversion rate from " +
                    this.baseCurrency + " to " + this.targetCurrency +
                    " is: " + rate);
            System.out.println(this.baseCurrency + amount + " = "
                    + this.targetCurrency+ conversion_result);
        }
        else{
            System.out.println("Error calling API. Response: " + response);
        }
        return timeStamp;
    }
    public void timeConverter(String timeStamp) {
        SimpleDateFormat utcFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z");
        utcFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
        String istTimestamp;

        try {
            Date utcDate = utcFormat.parse(timeStamp);

            SimpleDateFormat istFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z");
            istFormat.setTimeZone(TimeZone.getTimeZone("Asia/Kolkata"));

            istTimestamp = istFormat.format(utcDate);

//            System.out.println("UTC Timestamp: " + timeStamp);
            System.out.println("Currency fetched at IST Timestamp: " + istTimestamp);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws Exception {
        System.out.println("\nWelcome to Kartik's Currency converter!");
        boolean flag = true;
        System.out.println("\nEnter the key: ");
        String key = scanner.next();

        while(flag){

            System.out.println("\nEnter the base Currency: ");
            String base = scanner.next().toUpperCase();

            System.out.println("Enter the target currency: ");
            String target = scanner.next().toUpperCase();

            System.out.println("Enter the amount: ");
            double amount = scanner.nextDouble();

            CurrencyConverter exchange = new CurrencyConverter(key, base, target);
            String timeStamp = exchange.fetchCurrencyValue(amount);
            exchange.timeConverter(timeStamp);

            System.out.println("Do you want to convert another currency?(y/n): ");
            String response = scanner.next().toLowerCase();

            if(response.equals("n"))
                flag = false;

        }
    }
}