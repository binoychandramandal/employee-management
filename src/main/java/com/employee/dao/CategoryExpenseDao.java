package com.employee.dao;

import com.employee.entity.Expense;

public interface CategoryExpenseDao {

    boolean updateCategoryExpense(Expense expense);
    boolean updateExpense(Expense expense);

}
