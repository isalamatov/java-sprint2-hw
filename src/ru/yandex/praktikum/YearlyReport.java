import java.lang.reflect.Array;
import java.util.ArrayList;

public class YearlyReport {
    private ArrayList<YearlyReportRecord> yearlyReportRecords;

    public YearlyReport(ArrayList<YearlyReportRecord> yearlyReportRecords) {
        this.yearlyReportRecords = yearlyReportRecords;
    }

    public YearlyReport() {

    }

    public ArrayList<YearlyReportRecord> getYearlyReportRecords() {
        return yearlyReportRecords;
    }
}
