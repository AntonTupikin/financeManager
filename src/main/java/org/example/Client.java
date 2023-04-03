package org.example;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.example.statistic.Statistic;

import java.io.*;
import java.net.Socket;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import static org.example.statistic.Statistic.parseStatistic;

class Client {
    public static void main(String[] args) {
        try (Socket clientSocket = new Socket("localhost", 8989)) // этой строкой мы запрашиваем у сервера доступ на соединение
        {
            try (//BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
                 BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream())); // поток чтения из сокета
                 BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream())) // поток записи в сокет
            ) {
                GsonBuilder builder = new GsonBuilder();
                Gson gson = builder.create();
                String obj = gson.toJson(newPurchase());

                out.write(obj + "\n");
                out.flush();

                String answer = in.readLine();
                Statistic statistic = parseStatistic(answer); //получаем сообщение от клиента и распарсим его в объект
                System.out.println(statistic.toString());
            } catch (IOException e) {
                System.err.println(e);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static Purchase newPurchase() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите название покупки");
        String title = scanner.nextLine();
        System.out.println("Введите сумму покупки");
        int sum = Integer.parseInt(scanner.nextLine());
        String timestamp = ZonedDateTime.now()
                .format(DateTimeFormatter.ofPattern("yyy.MM.dd"));
        return new Purchase(title, sum, timestamp);
    }
}