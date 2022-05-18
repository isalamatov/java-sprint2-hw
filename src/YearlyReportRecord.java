
public class YearlyReportRecord {
    private Integer month;
    private Integer amount;
    private Boolean is_expense;

        public Integer getMonth() {
        return month;
    }

    public Integer getAmount() {
        return amount;
    }

    public Boolean getIs_expense() {
        return is_expense;
    }

    public YearlyReportRecord(Integer month, Integer amount, Boolean is_expense) {
        this.month = month;
        this.amount = amount;
        this.is_expense = is_expense;
    }

    public YearlyReportRecord(){}
}
