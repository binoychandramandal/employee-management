package com.employee.service;

import com.employee.converter.VOEntityConverterUtil;
import com.employee.dao.CategoryExpenseDao;
import com.employee.vo.ExpenseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryExpenseServiceImpl implements CategoryExpenseService {
    @Autowired
    private CategoryExpenseDao categoryExpenseDao;

    @Override
    public boolean updateCategoryExpense(ExpenseVO expenseVO) {
        return categoryExpenseDao.updateCategoryExpense(VOEntityConverterUtil.convert(expenseVO));
    }
}
