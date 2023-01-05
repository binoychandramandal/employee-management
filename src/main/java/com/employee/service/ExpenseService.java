package com.employee.service;

import com.employee.entity.Expense;
import com.employee.reportvo.CategoryWiseReport;
import com.employee.vo.ExpenseVO;

import java.util.List;

public interface ExpenseService {

    ExpenseVO addExpense(ExpenseVO expense);

    List<ExpenseVO> getAllExpenses();

    ExpenseVO getExpenseById(int id);

    boolean deleteExpense(int id);

    boolean updateExpense(ExpenseVO expense);

     List<ExpenseVO> findByCategoryId(int id);

    List<CategoryWiseReport> countTotalExpenseByCategory();
}
