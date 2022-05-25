import java.util.ArrayList;
import java.util.HashMap;

public class CheckingReports {

    public static HashMap<Integer, Integer> calculateMonthExpenses(ArrayList<MonthlyReport> monthlyReport) {
        //Variables to store summary expenses and incomes
        int sumExp = 0;
        int sumInc = 0;

        //Hashmap objects to sore month's expenses and incomes data
        HashMap<Integer, Integer> expensesMonthly = new HashMap<>();

        //Sort out expenses and incomes from each monthly report and save their monthly summary in HashMap table
        for (MonthlyReport report : monthlyReport) {
            for (MonthlyReportRecord record : report.getMonthlyReportRecords()
            ) {
                if (record.getIs_expense()) {
                    expensesMonthly.put(report.getMonth(), sumExp += record.getSum_of_one() * record.getQuantity());
                } else {
                    expensesMonthly.put(-report.getMonth(), sumInc += record.getSum_of_one() * record.getQuantity());
                }
            }
        }
        return expensesMonthly;
    }

    public static HashMap<Integer, Integer> calculateYearExpenses(YearlyReport yearlyReport) {
        //Variables to store summary expenses and incomes
        Integer sumExp = 0;
        Integer sumInc = 0;

        //Hashmap objects to sore month's expenses and incomes data
        HashMap<Integer, Integer> expensesYearly = new HashMap<>();

        //Sort out expenses and incomes from yearly report and save their monthly summary in HashMap table
        for (YearlyReportRecord record : yearlyReport.getYearlyReportRecords()) {
            if (record.getIs_expense()) {
                expensesYearly.put(record.getMonth(), (sumExp += record.getAmount()));
            } else {
                expensesYearly.put(-record.getMonth(), (sumInc += record.getAmount()));
            }
        }
        return expensesYearly;
    }

    public static void compareReports(HashMap<Integer, Integer> expensesMonthly, HashMap<Integer, Integer> expensesYearly) {
        //Walking through keySet to compare expense values in yearly and monthly report
        for (Integer month : expensesMonthly.keySet()
        ) {
            if (month > 0) {
                if (expensesMonthly.get(month).equals(expensesYearly.get(month)))
                    System.out.println("Расходы в годовом отчете и месячном отчете за месяц " + month + " сходятся и составляют: " + expensesMonthly.get(month));
                else {
                    System.out.println("Расходы в годовом отчете за месяц " + month + " составили : " + expensesYearly.get(month));
                    System.out.println("Расходы в месячном отчете за месяц " + month + " составили : " + expensesMonthly.get(month));
                }
            } else {
                if (expensesMonthly.get(month).equals(expensesYearly.get(month)))
                    System.out.println("Доходы в годовом отчете и месячном отчете за месяц " + -month + " сходятся и составляют: " + expensesMonthly.get(month));
                else {
                    System.out.println("Доходы в годовом отчете за месяц " + -month + " составили : " + expensesYearly.get(month));
                    System.out.println("Доходы в месячном отчете за месяц " + -month + " составили : " + expensesMonthly.get(month));
                }
            }
        }
    }
}
