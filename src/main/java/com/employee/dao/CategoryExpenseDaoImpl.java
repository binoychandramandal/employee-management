package com.employee.dao;

import com.employee.entity.Category;
import com.employee.entity.Expense;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public class CategoryExpenseDaoImpl implements CategoryExpenseDao {
    @Autowired
    private CategoryDao categoryDao;
    @Autowired
    private ExpenseDao expenseDao;

    @Override
    public boolean updateCategoryExpense(Expense expense) {
        try {
            if (expense == null)
                return false;
            if (expense.getCategory() == null)
                return false;
//            Category category = categoryDao.findById(expense.getCategory().getId()).orElse(null);
//            if (category == null) {
//                return false;
//            }
//            Expense expense1 = expenseDao.save(expense);
//            category.addExpenses(expense1);
//            categoryDao.save(category);

            Category category = categoryDao.findById(expense.getCategory().getId()).orElse(null);
            if (category == null) {
               return false;
            }
            expense.setCategory(category);
            expenseDao.save(expense);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Transactional
    public boolean updateExpense(Expense expense) {
        try {
            if (expense == null || expense.getCategory() == null) {
                return false;
            }
            Optional<Category> category = categoryDao.findById(expense.getCategory().getId());
            if (!category.isPresent()) {
                return false;
            }
            expense.setCategory(category.get());
            expenseDao.save(expense);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
