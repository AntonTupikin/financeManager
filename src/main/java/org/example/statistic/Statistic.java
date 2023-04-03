package org.example.statistic;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.MaxValues;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.io.Serializable;

public class Statistic implements Serializable {
    private MaxValues maxCategory;
    private MaxValues maxCategoryForYear;
    private MaxValues maxCategoryForMonth;
    private MaxValues maxCategoryForDay;

    public MaxValues getMaxCategory() {
        return maxCategory;
    }

    public MaxValues getMaxCategoryForYear() {
        return maxCategoryForYear;
    }

    public void setMaxCategoryForDay(MaxValues maxCategoryForDay) {
        this.maxCategoryForDay = maxCategoryForDay;
    }

    public void setMaxCategoryForMonth(MaxValues maxCategoryForMonth) {
        this.maxCategoryForMonth = maxCategoryForMonth;
    }

    public void setMaxCategoryForYear(MaxValues maxCategoryForYear) {
        this.maxCategoryForYear = maxCategoryForYear;
    }

    public void setMaxCategory(MaxValues maxCategory) {
        this.maxCategory = maxCategory;
    }

    public static Statistic parseStatistic(String answer) {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(answer);
            JSONObject jsonObject = (JSONObject) obj;
            String s = jsonObject.toJSONString();
            GsonBuilder builder = new GsonBuilder();
            Gson gson = builder.create();
            Statistic statistic = gson.fromJson(s, Statistic.class);
            return statistic;
        } catch (ParseException e) {
            e.printStackTrace();
            Statistic statistic = new Statistic();
            return statistic;
        }
    }

    @Override
    public String toString() {
        return maxCategory.toString() + "\n" +
                maxCategoryForYear.toString() + "\n" +
                maxCategoryForMonth.toString() + "\n" +
                maxCategoryForDay.toString() + "\n";
    }


}
