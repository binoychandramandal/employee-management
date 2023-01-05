package com.employee.reportvo;

public interface CategoryWiseReport {
    String getCategoryName();
    Double getAverage();
    Double getMin();
    Double getMax();
    Double getTotal();
    Integer getTotalTransaction();
}
