import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        final String PATH_TO_REPORTS = "resources/";

        //    final String PATH_TO_MONTH_REPORTS = System.getProperty("user.dir").replace("\\","/").concat("/resources");
        //    final String PATH_TO_YEAR_REPORTS = System.getProperty("user.dir").replace("\\","/").concat("/resources/y.2021.csv");

        Scanner scanner = new Scanner(System.in);

        //Initializing objects to pass first NullPointerException check
        ArrayList<MonthlyReportRecord> monthlyReport = null;
        ArrayList<YearlyReportRecord> yearlyReport = null;

        //Menu mechanics realization
        while (true) {
            printMenu();
            switch (scanner.nextInt()) {
                case 1:
                    monthlyReport = scanMonthlyReports(PATH_TO_REPORTS);
                    break;
                case 2:
                    yearlyReport = scanYearlyReport(PATH_TO_REPORTS);
                    break;
                case 3:
                    //Checking if monthly and yearly reports are loaded from files and then comparing them
                    if ((yearlyReport != null) && (monthlyReport != null))
                        CheckingReports.compareReports(CheckingReports.calculateMonthExpenses(monthlyReport), CheckingReports.calculateYearExpenses(yearlyReport));
                    else
                        System.out.println("Данные годового отчета или данные месячных отчетов не считаны");
                    break;
                case 4:
                    //Checking if monthly reports are loaded from files and then printing all monthly reports
                    if (monthlyReport != null) {
                        for (MonthlyReportRecord record : monthlyReport
                        ) {
                            System.out.println("Месяц : " + record.getMonth());
                            System.out.println(record.getItem_name());
                            System.out.println(record.getIs_expense());
                            System.out.println(record.getQuantity());
                            System.out.println(record.getSum_of_one());
                        }
                    } else System.out.println("Данные месячных отчетов не считаны.");
                    break;
                case 5:
                    //Checking if yearly report is loaded from file and then printing it.
                    if (yearlyReport != null) {
                        for (YearlyReportRecord record : yearlyReport) {
                            System.out.println(record.getMonth());
                            System.out.println(record.getAmount());
                            System.out.println(record.getIs_expense());
                        }
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

    //Method to print menu
    private static void printMenu() {
        System.out.println("1 - Считать все месячные отчёты");
        System.out.println("2 - Считать годовой отчёт");
        System.out.println("3 - Сверить отчёты");
        System.out.println("4 - Вывести информацию о всех месячных отчётах");
        System.out.println("5 - Вывести информацию о годовом отчёте");
        System.out.println("6 - Завершить работу");
        System.out.println("Выберите желаемое действие:");
    }

    //Method to scan all *.csv files meeting certain conditions from the designated folder
    private static ArrayList<MonthlyReportRecord> scanMonthlyReports(String PATH_TO_MONTH_REPORTS) throws IOException {
        final File PATH = new File(PATH_TO_MONTH_REPORTS);

        ArrayList<MonthlyReportRecord> monthlyReportsList = new ArrayList<>();

        //Checking if the folder is empty
        if (PATH.listFiles() != null) {
            for (File file : PATH.listFiles()
            ) {
                if (file.isFile() && file.getName().contains("m.") && file.getName().contains(".csv")) {
                    //Getting month name from the file name
                    Integer month = Integer.parseInt(file.getName().substring(7, 8));

                    System.out.println("Загружаем отчет из файла:" + file.getAbsoluteFile());

                    //Reading file with specific charset
                    FileInputStream fileInputStream = new FileInputStream(file.getAbsoluteFile());
                    InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    String string = bufferedReader.readLine();
                    string = bufferedReader.readLine(); //Step to skip first line
                    while (string != null) {
                        String[] values = string.split(",");
                        monthlyReportsList.add(new MonthlyReportRecord(values[0], Boolean.parseBoolean(values[1]), Integer.parseInt(values[2]), Integer.parseInt(values[3]), month));
                        string = bufferedReader.readLine();
                    }
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
        }
        else {
            System.out.println("Папка " + PATH + " не содержит месячных отчетов");
            return null;
        }
        return null;
    }
}