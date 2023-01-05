package com.employee.vo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.List;
public class CategoryVO {
    private int id;
    private String name;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private List<ExpenseVO> expenses;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<ExpenseVO> getExpenses() {
        return expenses;
    }

    public void setExpenses(List<ExpenseVO> expenses) {
        this.expenses = expenses;
    }
}
