import java.util.ArrayList;

public class MonthlyReport {
    private ArrayList<String> item_name;
    private ArrayList<Boolean> is_expense;
    private ArrayList<Integer> quantity;
    private ArrayList<Integer> sum_of_one;
    private Integer month;

    public ArrayList<String> getItem_name() {
        return item_name;
    }

    public ArrayList<Boolean> getIs_expense() {
        return is_expense;
    }

    public ArrayList<Integer> getQuantity() {
        return quantity;
    }

    public ArrayList<Integer> getSum_of_one() {
        return sum_of_one;
    }

    public Integer getMonth() {
        return month;
    }

    public MonthlyReport(ArrayList<String> item_name, ArrayList<Boolean> is_expense, ArrayList<Integer> quantity, ArrayList<Integer> sum_of_one, Integer month) {
        this.item_name = item_name;
        this.is_expense = is_expense;
        this.quantity = quantity;
        this.sum_of_one = sum_of_one;
        this.month = month;
    }
}
