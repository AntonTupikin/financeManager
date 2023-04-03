package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.Serializable;


public class Purchase implements Serializable {
    public void setTitle(String title) {
        this.title = title;
    }

    private String title;
    private String category;
    private int sum;
    private String purchaseDate;

    public Purchase(String title, int sum, String time) {
        this.title = title;
        this.sum = sum;
        this.purchaseDate = time;
    }

    public static Purchase parsePurchase(String answer) {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(answer);
            JSONObject jsonObject = (JSONObject) obj;
            String s = jsonObject.toJSONString();
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            Purchase purchase = gson.fromJson(s, Purchase.class);
            return purchase;
        } catch (ParseException e) {
            e.printStackTrace();
            Purchase purchase = new Purchase(null, 0, null);
            return purchase;
        }
    }

    public String getTitle() {
        return title;
    }

    public int getSum() {
        return sum;
    }

    public String getPurchaseDate() {
        return purchaseDate;
    }

    public String getYear() {

        String[] lines = purchaseDate.split("\\.");
        return lines[0];
    }

    public String getMonth() {

        String[] lines = purchaseDate.split("\\.");
        return lines[1];
    }

    @Override
    public String toString() {
        return title +
                "\n sum = " + sum +
                "\n purchaseDate = " + purchaseDate;
    }
}
