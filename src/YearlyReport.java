import java.util.ArrayList;

public class YearlyReport {
    private ArrayList<Integer> month;
    private ArrayList<Integer> amount;
    private ArrayList<Boolean> is_expense;

    public ArrayList<Integer> getMonth() {
        return month;
    }

    public ArrayList<Integer> getAmount() {
        return amount;
    }

    public ArrayList<Boolean> getIs_expense() {
        return is_expense;
    }

    public YearlyReport(ArrayList<Integer> month, ArrayList<Integer> amount, ArrayList<Boolean> is_expense) {
        this.month = month;
        this.amount = amount;
        this.is_expense = is_expense;
    }

    public YearlyReport(){}
}
