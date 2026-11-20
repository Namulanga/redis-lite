# Redis Lite

Redis Lite — упрощённая реализация Redis-подобного сервера на языке Java. Проект позволяет хранить пары «ключ — значение» в оперативной памяти и взаимодействовать с хранилищем через TCP-соединение.

## Возможности

- **SET** — сохранить значение по ключу или обновить существующее.
- **GET** — получить значение по ключу.
- **DEL** — удалить значение по ключу.
- **BEGIN** — начать транзакцию.
- **COMMIT** — применить изменения транзакции.
- Обработка некорректных команд и аргументов.
- Обмен командами и ответами через TCP-соединение.

## Технологии

- Java 17
- Maven
- JUnit 5
- TCP Sockets

## Структура проекта

```text
redis-lite/
├── pom.xml
├── README.md
├── .gitignore
└── src/
    ├── main/
    │   ├── java/com/example/redislite/
    │   │   ├── Main.java
    │   │   ├── server/
    │   │   │   └── KVServer.java
    │   │   ├── command/
    │   │   │   ├── Command.java
    │   │   │   ├── CommandFactory.java
    │   │   │   └── impl/
    │   │   │       ├── GetCommand.java
    │   │   │       ├── SetCommand.java
    │   │   │       ├── DelCommand.java
    │   │   │       ├── BeginCommand.java
    │   │   │       └── CommitCommand.java
    │   │   ├── parser/
    │   │   │   └── CommandParser.java
    │   │   ├── store/
    │   │   │   └── KeyValueStore.java
    │   │   ├── transaction/
    │   │   │   └── Transaction.java
    │   │   └── session/
    │   │       └── ClientSession.java
    │   └── resources/
    └── test/
        ├── java/com/example/redislite/
        │   ├── store/
        │   │   └── KeyValueStoreTest.java
        │   └── parser/
        │       └── CommandParserTest.java
        └── resources/
```

## Требования

Для сборки и запуска проекта необходимы:

- JDK 17 или выше.
- Apache Maven.

## Сборка проекта

Клонируйте репозиторий:

```bash
git clone <repository-url>
cd redis-lite
```

Соберите проект:

```bash
mvn clean package
```

## Запуск сервера

После сборки запустите приложение:

```bash
java -jar target/redis-lite.jar
```

По умолчанию сервер использует порт `5555`.

Можно указать другой порт:

```bash
java -jar target/redis-lite.jar 6000
```

## Использование

После запуска сервера подключитесь к нему через TCP-клиент, например `telnet`:

```bash
telnet localhost 5555
```

Примеры команд:

```text
SET name Anna
OK

GET name
Anna

DEL name
OK

BEGIN
OK

SET city Tallinn
OK

COMMIT
OK
```

Для завершения соединения используйте:

```text
QUIT
```

## Тестирование

Для запуска автоматических тестов выполните:

```bash
mvn test
```

Тесты проверяют работу хранилища ключей и значений, а также обработку команд парсером.

## Хранение данных

В текущей версии данные хранятся в оперативной памяти. После перезапуска сервера хранилище очищается.