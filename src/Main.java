import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws IOException {

        Scanner scanner = new Scanner(System.in);
        ArrayList<MonthlyReport> monthlyReportsList = null;
        YearlyReport yearlyReport = null;

        int menuOption = 0;

        //Menu mechanics realization
        while (true) {
            printMenu();
            switch (scanner.nextInt()) {
                case 1:
                    monthlyReportsList = scanMonthlyReports();
                    break;
                case 2:
                    yearlyReport = scanYearlyReport();
                    break;
                case 3:
                    if ((yearlyReport != null) && (monthlyReportsList != null))
                        CheckingReports.checkReports(yearlyReport, monthlyReportsList);
                    else
                        System.out.println("Данные годового отчета или данные месячных отчетов не считаны");
                    break;
                case 4:
                    if (monthlyReportsList != null) {
                        for (MonthlyReport monthlyReport : monthlyReportsList
                        ) {
                            System.out.println("Месяц : " + monthlyReport.getMonth());
                            for (int i = 0; i < monthlyReport.getItem_name().size(); i++) {
                                System.out.println(monthlyReport.getItem_name().get(i));
                                System.out.println(monthlyReport.getIs_expense().get(i));
                                System.out.println(monthlyReport.getQuantity().get(i));
                                System.out.println(monthlyReport.getSum_of_one().get(i));
                            }
                        }
                    } else System.out.println("Данные месячных отчетов не считаны.");
                    break;
                case 5:
                    if (yearlyReport != null) {
                        for (int i = 0; i < yearlyReport.getMonth().size(); i++) {
                            System.out.println(yearlyReport.getMonth().get(i));
                            System.out.println(yearlyReport.getAmount().get(i));
                            System.out.println(yearlyReport.getIs_expense().get(i));
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

    private static ArrayList<MonthlyReport> scanMonthlyReports() throws IOException {
        final File PATH = new File("C:/Test/MonthReports/");
        ArrayList<MonthlyReport> monthlyReportsList = new ArrayList<>();

        if (PATH.listFiles() != null) {
            for (File file : PATH.listFiles()
            ) {
                if (file.isFile() && file.getName().contains("m.") && file.getName().contains(".csv")) {

                    ArrayList<String> item_name = new ArrayList<>();
                    ArrayList<Boolean> is_expense = new ArrayList<>();
                    ArrayList<Integer> quantity = new ArrayList<>();
                    ArrayList<Integer> sum_of_one = new ArrayList<>();

                    Integer month = Integer.parseInt(file.getName().substring(7, 8));

                    FileInputStream fileInputStream = new FileInputStream(file.getAbsoluteFile());
                    System.out.println("Загружаем отчет из файла:" + file.getAbsoluteFile());
                    InputStreamReader inputStreamReader = new InputStreamReader(fileInputStream, StandardCharsets.UTF_8);
                    BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                    String string = bufferedReader.readLine();
                    string = bufferedReader.readLine();
                    while (string != null) {
                        String[] values = string.split(";");
                        item_name.add(values[0]);
                        is_expense.add(Boolean.parseBoolean(values[1]));
                        quantity.add(Integer.parseInt(values[2]));
                        sum_of_one.add(Integer.parseInt(values[3]));
                        string = bufferedReader.readLine();
                    }
                    MonthlyReport monthlyReport = new MonthlyReport(item_name, is_expense, quantity, sum_of_one, month);
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

    private static YearlyReport scanYearlyReport() throws IOException {
        final File PATH = new File("C:/Test/YearReports/y.2021.csv");
        if (!PATH.exists()) {
            System.out.println("Файл с годовым отчетом не обнаружен в рабочей директории.");
            return null;
        } else System.out.println("Загружаем годовой отчет из файла" + PATH.getAbsolutePath());
        ArrayList<Integer> month = new ArrayList<>();
        ArrayList<Integer> amount = new ArrayList<>();
        ArrayList<Boolean> is_expense = new ArrayList<>();

        FileReader fileReader = new FileReader(PATH);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        String string = bufferedReader.readLine();
        string = bufferedReader.readLine();
        while (string != null) {
            String[] values = string.split(";");
            month.add(Integer.parseInt(values[0]));
            amount.add(Integer.parseInt(values[1]));
            is_expense.add(Boolean.parseBoolean(values[2]));
            string = bufferedReader.readLine();
        }
        return new YearlyReport(month, amount, is_expense);
    }
}