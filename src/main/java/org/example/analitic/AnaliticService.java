package org.example.analitic;

import org.example.MaxValues;
import org.example.UserData;

public interface AnaliticService {

    MaxValues searchMaxCategory(UserData userData);

    MaxValues searchMaxCategoryForYear(UserData userData, String year);

    MaxValues searchMaxCategoryForMonth(UserData userData, String month);

    MaxValues searchMaxCategoryForDay(UserData userData, String day);

}
