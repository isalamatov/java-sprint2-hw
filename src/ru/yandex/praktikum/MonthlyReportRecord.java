import java.util.ArrayList;

public class MonthlyReportRecord {

    private String item_name;
    private Boolean is_expense;
    private Integer quantity;
    private Integer sum_of_one;
    private Integer month;
    public String getItem_name() {
        return item_name;
    }

    public Boolean getIs_expense() {
        return is_expense;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public Integer getSum_of_one() {
        return sum_of_one;
    }

    public Integer getMonth() {
        return month;
    }

    public MonthlyReportRecord(String item_name, Boolean is_expense, Integer quantity, Integer sum_of_one, Integer month) {
        this.item_name = item_name;
        this.is_expense = is_expense;
        this.quantity = quantity;
        this.sum_of_one = sum_of_one;
        this.month = month;
    }
}
