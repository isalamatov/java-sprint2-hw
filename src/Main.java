import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {

    public static void main(String[] args) throws IOException {

        final String PATH_TO_REPORTS = "resources/";

        //    final String PATH_TO_MONTH_REPORTS = System.getProperty("user.dir").replace("\\","/").concat("/resources");
        //    final String PATH_TO_YEAR_REPORTS = System.getProperty("user.dir").replace("\\","/").concat("/resources/y.2021.csv");

        Scanner scanner = new Scanner(System.in);

        //Initializing objects to pass first NullPointerException check

        ArrayList<MonthlyReport> monthlyReportsList = null;
        YearlyReport yearlyReport = null;

        //Menu mechanics realization
        while (true) {
            printMenu();
            switch (scanner.nextInt()) {
                case 1:
                    monthlyReportsList = scanMonthlyReports(PATH_TO_REPORTS);
                    break;
                case 2:
                    yearlyReport = new YearlyReport(scanYearlyReport(PATH_TO_REPORTS));
                    break;
                case 3:
                    //Checking if monthly and yearly reports are loaded from files and then comparing them
                    if ((yearlyReport != null) && (monthlyReportsList != null))
                        CheckingReports.compareReports(CheckingReports.calculateMonthExpenses(monthlyReportsList), CheckingReports.calculateYearExpenses(yearlyReport));
                    else
                        System.out.println("Данные годового отчета или данные месячных отчетов не считаны");
                    break;
                case 4:
                    //Checking if monthly reports are loaded from files and then printing all monthly reports
                    if (monthlyReportsList != null) {
                        printMonthlyReport(monthlyReportsList);
                        /*for (MonthlyReportRecord record : monthlyReport
                        ) {
                            System.out.println("Месяц : " + record.getMonth());
                            System.out.println(record.getItem_name());
                            System.out.println(record.getIs_expense());
                            System.out.println(record.getQuantity());
                            System.out.println(record.getSum_of_one());
                        }*/
                    } else System.out.println("Данные месячных отчетов не считаны.");
                    break;
                case 5:
                    //Checking if yearly report is loaded from file and then printing it.
                    if (yearlyReport != null) {
                        printYearlyReport(yearlyReport);
                        /*for (YearlyReportRecord record : yearlyReport) {
                            System.out.println(record.getMonth());
                            System.out.println(record.getAmount());
                            System.out.println(record.getIs_expense());
                        }*/
                    } else System.out.println("Данные годового отчета не считаны.");
                    break;
                case 6:
                    System.out.println("Завершение работы");
                    return;
                default:
                    System.out.println("Введено некорректное значение");
                    break;
            }
        }
    }

    private static void printYearlyReport(YearlyReport yearlyReport) {

        HashMap<Integer, Integer> monthlyIncome = new HashMap<>();
        HashMap<Integer, Integer> monthlyExpense = new HashMap<>();
        Integer prevValue = 0;
        Integer prevExpValue = 0;
        Integer sumExp = 0;
        Integer sumIncome = 0;

        for (YearlyReportRecord record : yearlyReport.getYearlyReportRecords()
        ) {
            if (record.getIs_expense()) {
                prevExpValue = monthlyExpense.getOrDefault(record.getMonth(), 0);
                monthlyExpense.put(record.getMonth(), prevExpValue + record.getAmount());
            } else {
                prevValue = monthlyIncome.getOrDefault(record.getMonth(), 0);
                monthlyIncome.put(record.getMonth(), prevValue + record.getAmount());
            }
        }

        for (Integer key : monthlyIncome.keySet()
        ) {
            System.out.println("Прибыль в месяце " + convertMonth(key) + " составила: " + (monthlyIncome.get(key) - monthlyExpense.get(key)));
            System.out.println("Доход в месяце " + convertMonth(key) + " составил: " + monthlyIncome.get(key));
            System.out.println("Расход в месяце " + convertMonth(key) + " составил: " + monthlyExpense.get(key) + "\n");

            sumIncome += monthlyIncome.get(key);
            sumExp += monthlyExpense.get(key);
        }
        System.out.println("Средний доход за все месяцы в году: " + sumIncome / monthlyIncome.size());
        System.out.println("Средний расход за все месяцы в году: " + sumExp / monthlyExpense.size() + "\n");
    }


    private static void printMonthlyReport(ArrayList<MonthlyReport> monthlyReport) {

        for (MonthlyReport report : monthlyReport
        ) {
            Integer maxExpense = 0;
            String maxExpenseName = null;
            Integer maxIncome = 0;
            String maxIncomeName = null;
            String monthName = null;

            for (MonthlyReportRecord record : report.getMonthlyReportRecords()
            ) {
                if (!record.getIs_expense()) {
                    if (record.getQuantity() * record.getSum_of_one() > maxExpense) {
                        maxIncome = record.getQuantity() * record.getSum_of_one();
                        maxIncomeName = record.getItem_name();
                    }
                } else {
                    if (record.getQuantity() * record.getSum_of_one() > maxExpense) {
                        maxExpense = record.getQuantity() * record.getSum_of_one();
                        maxExpenseName = record.getItem_name();
                    }
                }
            }
            monthName = convertMonth(report.getMonth());
            System.out.println("Максимальный доход в месяце " + monthName + " составил " + maxIncome + " по позициии " + maxIncomeName);
            System.out.println("Максимальная трата в месяце " + monthName + " составила " + maxExpense + " по позициии " + maxExpenseName + "\n");

        }

        /*int monthCounter = 1;
        HashMap<String, Integer> itemsIncome = new HashMap<>();
        int monthNumber = 0;
        int maxIncome = 0;
        String monthName = null;
        String maxIncomeKey = null;
        int maxExpense = 0;
        String maxExpenseName = null;

        for (MonthlyReportRecord record : monthlyReport
        ) {
            monthNumber = record.getMonth();

            if (!record.getIs_expense()) {
                if (itemsIncome.containsKey(record.getItem_name())) {
                    Integer prevIncome = itemsIncome.get(record.getItem_name());
                    itemsIncome.put(record.getItem_name(), prevIncome + record.getSum_of_one() * record.getQuantity());
                } else
                    itemsIncome.put(record.getItem_name(), record.getSum_of_one() * record.getQuantity());
            } else {
                if (record.getQuantity() * record.getSum_of_one() > maxExpense) {
                    maxExpense = record.getQuantity() * record.getSum_of_one();
                    maxExpenseName = record.getItem_name();
                }
            }

            if (monthCounter != monthNumber || monthlyReport.indexOf(record) == (monthlyReport.size() - 1)) {
                maxIncome = 0;
                maxIncomeKey = null;

                monthName = convertMonth(monthNumber - 1);

                if (monthlyReport.indexOf(record) == (monthlyReport.size() - 1))
                    monthName = convertMonth(monthNumber);

                for (String key : itemsIncome.keySet()
                ) {
                    if (itemsIncome.get(key) > maxIncome) {
                        maxIncome = itemsIncome.get(key);
                        maxIncomeKey = key;
                    }
                }

                System.out.println("Максимальный доход в месяце " + monthName + " составил " + maxIncome + " по позициии " + maxIncomeKey);
                System.out.println("Максимальная трата в месяце " + monthName + " составила " + maxExpense + " по позициии " + maxExpenseName + "\n");
                itemsIncome.clear();
                maxIncome = 0;
                maxExpense = 0;
                maxIncomeKey = null;
                monthCounter++;
            }
        }*/
    }

    //Method to print menu
    private static void printMenu() {
        System.out.println("1 - Считать все месячные отчёты");
        System.out.println("2 - Считать годовой отчёт");
        System.out.println("3 - Сверить отчёты");
        System.out.println("4 - Вывести информацию о всех месячных отчётах");
        System.out.println("5 - Вывести информацию о годовом отчёте");
        System.out.println("6 - Завершить работу");
        System.out.println("Выберите желаемое действие:\n");
    }

    private static String convertMonth(Integer month) {
        return Integer.toString(month).replace("1", "январь").replace("2", "февраль").replace("3", "март");
    }

    //Method to scan all *.csv files meeting certain conditions from the designated folder
    private static ArrayList<MonthlyReport> scanMonthlyReports(String PATH_TO_MONTH_REPORTS) throws IOException {
        final File PATH = new File(PATH_TO_MONTH_REPORTS);

        ArrayList<MonthlyReport> monthlyReportsList = new ArrayList<>();

        //Checking if the folder is empty
        if (PATH.listFiles() != null) {
            for (File file : PATH.listFiles()
            ) {
                if (file.isFile() && file.getName().contains("m.") && file.getName().contains(".csv")) {
                    //Getting month name from the file name
                    Integer month = Integer.parseInt(file.getName().substring(7, 8));
                    ArrayList<MonthlyReportRecord> monthlyReportRecords = new ArrayList<>();

                    System.out.println("Загружаем отчет из файла:" + file.getAbsoluteFile());

                    //Reading file with specific charset
                    FileInputStream fileInputStream = new FileInputStream(file.getAbsoluteFile());
                    InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    String string = bufferedReader.readLine();
                    string = bufferedReader.readLine(); //Step to skip first line
                    while (string != null) {
                        String[] values = string.split(",");
                        monthlyReportRecords.add(new MonthlyReportRecord(values[0], Boolean.parseBoolean(values[1]), Integer.parseInt(values[2]), Integer.parseInt(values[3]), month));
                        string = bufferedReader.readLine();
                    }
                    MonthlyReport monthlyReport = new MonthlyReport(month, monthlyReportRecords);
                    monthlyReportsList.add(monthlyReport);
                    inputStreamReader.close();
                    bufferedReader.close();
                } else {
                    System.out.println("Файл " + file.getAbsolutePath() + " не является месячным отчетом");
                }
            }
            return monthlyReportsList;
        } else {
            System.out.println("Папка " + PATH + " не содержит месячных отчетов");
            return null;
        }
    }

    //Method to load data from certain file, that is designated in the explicit way
    private static ArrayList<YearlyReportRecord> scanYearlyReport(String PATH_TO_YEAR_REPORTS) throws IOException {
        final File PATH = new File(PATH_TO_YEAR_REPORTS);

        if (PATH.listFiles() != null) {
            for (File file : PATH.listFiles()
            ) {
                if (file.isFile() && file.getName().contains("y") && file.getName().contains("csv")) {
                    System.out.println("Загружаем годовой отчет из файла " + file.getAbsolutePath());
                    ArrayList<YearlyReportRecord> yearlyReport = new ArrayList<>();

                    //Encoding does not matter in this case, because file does not contain String or Char objects
                    FileReader fileReader = new FileReader(file);
                    BufferedReader bufferedReader = new BufferedReader(fileReader);
                    String string = bufferedReader.readLine();
                    string = bufferedReader.readLine();
                    while (string != null) {
                        String[] values = string.split(",");
                        yearlyReport.add(new YearlyReportRecord(Integer.parseInt(values[0]), Integer.parseInt(values[1]), Boolean.parseBoolean(values[2])));
                        string = bufferedReader.readLine();
                    }

                    return yearlyReport;
                } else {
                    System.out.println("Файл " + file.getAbsolutePath() + " не является годовым отчетом");
                }
            }
        } else {
            System.out.println("Папка " + PATH + " не содержит месячных отчетов");
            return null;
        }
        return null;
    }

    public static Integer calculateMonthExpenses(ArrayList<MonthlyReportRecord> expenses, Integer monthNumber) {
        AtomicInteger Sum = new AtomicInteger();
        expenses.stream().filter(exp -> exp.getMonth() == monthNumber && exp.getIs_expense())
                .forEach(x -> Sum.addAndGet(x.getQuantity() * x.getSum_of_one()));

        return Sum.get();
    }

    public static Integer calculateMonthIncome(ArrayList<MonthlyReportRecord> expenses, Integer monthNumber) {
        AtomicInteger Sum = new AtomicInteger();
        expenses.stream().filter(exp -> exp.getMonth() == monthNumber && !exp.getIs_expense()).forEach(x -> Sum.addAndGet(x.getQuantity() * x.getSum_of_one()));

        return Sum.get();
    }
}

