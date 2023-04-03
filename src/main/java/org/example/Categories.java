package org.example;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class Categories implements Serializable, Comparable <Categories> {
    private Map<String, String> categories = new HashMap<>();
    public Categories(){
        ArrayList<String> categoriesFromFileLikeString = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("categories.tsv"))) {
            //чтение построчно
            String s;
            while ((s = br.readLine()) != null) {
                categoriesFromFileLikeString.add(s);
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
        Map<String, String> categories = new HashMap<>();
        Iterator<String> iter = categoriesFromFileLikeString.iterator();
        int i = 0;
        while (iter.hasNext()) {
            String[] words = iter.next().split("\t");
            this.categories.put(words[0], words[1]);
            i++;
        }
    }


    @Override
    public String toString() {

        if (!categories.isEmpty()) {
            String map="";
            for (Map.Entry<String, String> pair : categories.entrySet()) {
                String key = pair.getKey();
                String value = pair.getValue();
                map += key + " : " + value + "\n";
            }
            return map;
        } else {
            return "Категории пустые. Загрузите файл";
        }
    }

    public Map<String, String> getCategories() {
        return categories;
    }

    @Override
    public int compareTo(Categories o) {
        return 0;
    }
}
