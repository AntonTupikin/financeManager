package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class MaxValues implements Serializable {
    private String title;
    private String category;
    private int sum;


    public MaxValues(String title, String category, int sum) {
        this.title = title;
        this.category = category;
        this.sum = sum;
    }

    public static MaxValues parseMaxValue(String answer) {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(answer);
            JSONObject jsonObject = (JSONObject) obj;
            String s = jsonObject.toJSONString();
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            MaxValues value = gson.fromJson(s, MaxValues.class);
            return value;
        } catch (ParseException e) {
            e.printStackTrace();
            MaxValues value = new MaxValues(null, null, 0);
            return value;
        }
    }

    public int getSum() {
        return sum;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return title +
                "\n category = " + category +
                "\n sum = " + sum;
    }
}
