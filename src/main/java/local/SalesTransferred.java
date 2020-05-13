package local;

public class SalesTransferred extends AbstractEvent {

    private int cafeId;
    private String yearmonth;
    private Double salesSum;
    private Double salesQty;
    private Double orderCount;

    public SalesTransferred(){
        super();
    }

    public int getCafeId() {
        return cafeId;
    }

    public void setCafeId(int cafeId) {
        this.cafeId = cafeId;
    }

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
