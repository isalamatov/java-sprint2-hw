import java.util.ArrayList;

public class MonthlyReport {
    private int month;
    private ArrayList<MonthlyReportRecord> monthlyReportRecords;

    public MonthlyReport(int month, ArrayList<MonthlyReportRecord> monthlyReportRecords) {
        this.month = month;
        this.monthlyReportRecords = monthlyReportRecords;
    }

    public MonthlyReport() {

    }

    public int getMonth() {
        return month;
    }

    public ArrayList<MonthlyReportRecord> getMonthlyReportRecords() {
        return monthlyReportRecords;
    }
}
