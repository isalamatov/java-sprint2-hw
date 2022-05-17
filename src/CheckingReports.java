import java.util.ArrayList;
import java.util.HashMap;

public class CheckingReports {

    public static void checkReports(YearlyReport yearlyReport, ArrayList<MonthlyReport> monthlyReportsList) {
        Integer sumExp = 0;
        Integer sumInc = 0;
        HashMap<Integer, Integer> expensesYearly = new HashMap<>();
        HashMap<Integer, Integer> expensesMonthly = new HashMap<>();
        HashMap<Integer, Integer> incomesYearly = new HashMap<>();
        HashMap<Integer, Integer> incomesMonthly = new HashMap<>();

        for (int i = 0; i < yearlyReport.getMonth().size(); i++) {
            if (yearlyReport.getIs_expense().get(i)) {
                expensesYearly.put(yearlyReport.getMonth().get(i), (sumExp += yearlyReport.getAmount().get(i)));
            } else {
                incomesYearly.put(yearlyReport.getMonth().get(i), (sumInc += yearlyReport.getAmount().get(i)));
            }
        }

        sumExp = 0;
        sumInc = 0;

        for (MonthlyReport monthlyReport : monthlyReportsList
        ) {
            for (int j = 0; j < monthlyReport.getItem_name().size(); j++) {
                if (monthlyReport.getIs_expense().get(j)) {
                    expensesMonthly.put(monthlyReport.getMonth(), sumExp += monthlyReport.getSum_of_one().get(j)*monthlyReport.getQuantity().get(j));
                } else {
                    incomesMonthly.put(monthlyReport.getMonth(), sumInc += monthlyReport.getSum_of_one().get(j)*monthlyReport.getQuantity().get(j));
                }
            }
        }

        for (Integer month:expensesMonthly.keySet()
        ) {
            if (expensesMonthly.get(month).equals(expensesYearly.get(month)))
                System.out.println("Расходы в годовом отчете и месячном отчете за месяц "+month+" сходятся и составляют: " + expensesMonthly.get(month));
            else {
                System.out.println("Расходы в годовом отчете за месяц " + month + " составили : " + expensesYearly.get(month));
                System.out.println("Расходы в месячном отчете за месяц " + month + " составили : " + expensesMonthly.get(month));
            }
        }

        for (Integer month:incomesMonthly.keySet()
        ) {
            if (incomesMonthly.get(month).equals(incomesYearly.get(month)))
                System.out.println("Доходы в годовом отчете и месячном отчете за месяц "+month+" сходятся и составляют: " + incomesMonthly.get(month));
            else {
                System.out.println("Доходы в годовом отчете за месяц " + month + " составили : " + incomesYearly.get(month));
                System.out.println("Доходы в месячном отчете за месяц " + month + " составили : " + incomesMonthly.get(month));
            }
        }

    }

}
