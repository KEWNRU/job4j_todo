# Job4j_TODO Приложение "TODO список".

## Описание:
В данном проекте реализуется сервис со списком задач.
Сервис позволяет:
1. Вывести все задачи
2. Вывести только новые задачи
3. Вывести только выполненные задачи
4. Добавить задачу (название задачи, её описание)
5. Обновить задачу (название, описание, статус (новый или выполненный))
6. Удалить задачу

## Стек технологий:
+ Java 17
+ PostgreSQL
+ Springframework.boot 2.7.6
+ Hibernate
+ Lombok
+ H2database
+ Liquibase

## Окружение:
- Java 17
- PostgreSQL 16
- Maven 3.8

## Запуск приложения:
1. Создайте базу данных
``` sql
CREATE DATABASE todo
```

2. Клонируйте репозиторий
``` bash
git clone https://github.com/KEWNRU/job4j_todo.git
cd job4j_todo
```

3. Соберите проект с помощью Maven:
``` bash
mvn clean install 
```

4. Запустите приложение:
``` bash
mvn spring-boot:run
```

## Главная страница:
![1.png](screenshot%2F1.png)
## Список всех заданий:
![2.png](screenshot%2F2.png)
## Список выполненных заданий:
![3.png](screenshot%2F3.png)
## Список выполненных заданий:
![4.png](screenshot%2F4.png)
## Страница редактирования и выполнения задач:
![5.png](screenshot%2F5.png)


