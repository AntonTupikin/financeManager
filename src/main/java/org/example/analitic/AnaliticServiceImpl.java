package org.example.analitic;

import org.example.Categories;
import org.example.MaxValues;
import org.example.Purchase;
import org.example.UserData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AnaliticServiceImpl implements AnaliticService {

    public MaxValues searchMaxCategory(UserData userData) {
        Map<String, Integer> map = userData.getData();

        Map.Entry<String, Integer> maxEntry = searchMax(map);
        MaxValues maxCategory = new MaxValues("maxCategory", maxEntry.getKey(), maxEntry.getValue());
        return maxCategory;
    }

    public MaxValues searchMaxCategoryForDay(UserData userData, String day) {
        Map<String, Integer> data = filter(userData, day);
        Map.Entry<String, Integer> maxEntry = searchMax(data);
        MaxValues maxCategoryDay = new MaxValues("maxCategoryDay", maxEntry.getKey(), maxEntry.getValue());
        return maxCategoryDay;
    }

    public MaxValues searchMaxCategoryForMonth(UserData userData, String month) {
        Map<String, Integer> data = filter(userData, month);
        Map.Entry<String, Integer> maxEntry = searchMax(data);
        MaxValues maxCategoryMonth = new MaxValues("maxCategoryMonth", maxEntry.getKey(), maxEntry.getValue());
        return maxCategoryMonth;
    }

    public MaxValues searchMaxCategoryForYear(UserData userData, String year) {
        Map<String, Integer> data = filter(userData, year);
        Map.Entry<String, Integer> maxEntry = searchMax(data);
        MaxValues maxCategoryYear = new MaxValues("maxCategoryYear", maxEntry.getKey(), maxEntry.getValue());
        return maxCategoryYear;
    }

    private Map.Entry<String, Integer> searchMax(Map<String, Integer> data) {
        Map.Entry<String, Integer> maxEntry = null;
        for (Map.Entry<String, Integer> entry : data.entrySet()) {
            if (maxEntry == null || entry.getValue() > maxEntry.getValue()) {
                maxEntry = entry;
            }
        }
        return maxEntry;
    }

    private Map<String, Integer> filter(UserData userData, String time) {
        Categories categories = new Categories();
        List<Purchase> purchases = userData.getPurchases();
        Map<String, Integer> data = new HashMap<>();

        Map<String, Integer> map =
                purchases.stream()
                        .filter(value -> value.getPurchaseDate().contains(time))
                        .collect(Collectors.toMap(x -> x.getTitle(),
                                x -> x.getSum(),
                                (oldValue, newValue) -> oldValue + newValue));
        for (Map.Entry<String, Integer> entry : map.entrySet()) {


            if (categories.getCategories().get(entry.getKey()) != null) {
                data.put(categories.getCategories().get(entry.getKey()), entry.getValue());
            } else {
                data.put("другое", entry.getValue());
            }
            if (data.get(entry.getKey()) != null) {
                int sum = data.get(entry.getKey()) + entry.getValue();
                data.put(entry.getKey(), sum);
            }

        }
        return data;
    }
}
