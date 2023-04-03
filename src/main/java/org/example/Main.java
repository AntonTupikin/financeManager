package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.analitic.AnaliticService;
import org.example.analitic.AnaliticServiceImpl;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

import static java.lang.System.out;
import static org.example.Purchase.parsePurchase;


public class Main {


    public static void main(String[] args) {
        UserData userData = loadData();


        try (ServerSocket serverSocket = new ServerSocket(8989)) {
            out.println("Сервер запущен");// стартуем сервер один(!) раз
            while (true) { // в цикле(!) принимаем подключения
                try (
                        Socket clientSocket = serverSocket.accept();
                        BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                        PrintWriter out = new PrintWriter(clientSocket.getOutputStream())
                ) {
                    System.out.println("Клиент подключился");
                    String answer = in.readLine();
                    Purchase purchase = parsePurchase(answer); //получем сообщение от клиента и распарсим его в объект
                    System.out.println(purchase.toString());
                    userData.addToData(purchase);

                    analyze(userData, purchase);
                    saveData(userData);

                    GsonBuilder builder = new GsonBuilder();
                    Gson gson = builder.create();
                    String obj = gson.toJson(userData.getStatistic());

                    out.write(obj + "\n");
                    out.flush();
                }
            }
        } catch (IOException e) {
            System.out.println("Не могу стартовать сервер");
            e.printStackTrace();
        }
    }

    public static void analyze(UserData userData, Purchase purchase) {
        AnaliticService analiticService = new AnaliticServiceImpl();
        userData.setMaxCategory(analiticService.searchMaxCategory(userData));
        userData.setMaxCategoryForYear(analiticService.searchMaxCategoryForYear(userData, purchase.getYear()));
        userData.setMaxCategoryForMonth(analiticService.searchMaxCategoryForMonth(userData, purchase.getMonth()));
        userData.setMaxCategoryForDay(analiticService.searchMaxCategoryForDay(userData, purchase.getPurchaseDate()));
    }

    public static void saveData(UserData userData) {
        // откроем выходной поток для записи в файл
        try (FileOutputStream fos = new FileOutputStream("data.bin");
             ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            // запишем экземпляр класса в файл
            oos.writeObject(userData);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static UserData loadData() {
        // откроем входной поток для чтения файла
        UserData userData;
        File file = new File("data.bin");
        if (file.exists()) {
            out.println("Корзина найдена");
        }
        try (FileInputStream fis = new FileInputStream("data.bin");
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            // десериализуем объект и скастим его в класс
            return userData = (UserData) ois.readObject();

        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return userData = new UserData();
        }

    }


}