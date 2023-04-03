# Менеджер личных финансов
## Логика работы
- В классе **Main** создается сервер, принимающий подключения в цикле
- Класс **Client** подключается к серверу, отправляя только что сделанную покупку, предварительно конверсировав в JSON 
- Сервер обрабатывает покупку, добавляя и сохраняя ее в список покупок и отправляет клиенты объект его статистики класса **Statistic** в JSON 
- Обработку статистики осуществляется объект типа **AnaliticService** 
## Хранение данных
Все данные хранятся в файле **data.bin**, в который сериализуется объект класса UserData