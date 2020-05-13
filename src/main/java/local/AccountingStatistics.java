package local;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="AccountingStatistics_table")
public class AccountingStatistics {

    @Id
    private String yearmonth;
    private Double salesSum;
    private Double salesQty;
    private Double orderCount;

    public String getYearmonth() {
        return yearmonth;
    }

    public void setYearmonth(String yearmonth) {
        this.yearmonth = yearmonth;
    }

    public Double getSalesSum() {
        return salesSum;
    }

    public void setSalesSum(Double salesSum) {
        this.salesSum = salesSum;
    }

    public Double getSalesQty() {
        return salesQty;
    }

    public void setSalesQty(Double salesQty) {
        this.salesQty = salesQty;
    }

    public Double getOrderCount() {
        return orderCount;
    }

    public void setOrderCount(Double orderCount) {
        this.orderCount = orderCount;
    }
}
