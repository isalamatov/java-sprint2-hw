# Репозиторий финансового приложения
---
## Описание приложения
Приложение выполняет разнообразные тестовые функции, в т.ч.:
* загрузку данных из **csv** файлов
* парсинг текстовых данных, приведение типов
* сравнение *месячных* и *годового* отчета 

## Данные об образовательном курсе
Образовательный курс проводится на платформе **Яндекс**, размещенной по адресу:
[Яндекс.Практикум](https://practicum.yandex.ru/ "Ссылка на заглавную страницу яндекс.практикум")

### Язык разработки
Приложение создано на Java. 
В приложении использованы коллекции из стандартных библиотек Java:
```java
public static Integer calculateMonthIncome(ArrayList<MonthlyReportRecord> expenses, Integer monthNumber) {
        AtomicInteger Sum = new AtomicInteger();
        expenses.stream().filter(exp -> exp.getMonth() == monthNumber && !exp.getIs_expense()).
		forEach(x -> Sum.addAndGet(x.getQuantity() * x.getSum_of_one()));

        return Sum.get();
    }
```
