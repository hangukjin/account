package local;

import javax.persistence.*;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Entity
@Table(name="Accounting_table")
public class Accounting {

    @Id
    private String yearmonth;
    private Double salesSum;
    private Double salesQty;
    private Double orderCount;


    @PostPersist
    @PostUpdate
    public void onPrePersist(){
        SalesTransferred salesTransferred = new SalesTransferred();
        BeanUtils.copyProperties(this, salesTransferred);
        salesTransferred.setCafeId(92);

        this.salesTransfer(salesTransferred);
    }
    @RequestMapping(method= RequestMethod.POST, path="/headquarters")
    public void salesTransfer(@RequestBody SalesTransferred salesTransferred){
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
