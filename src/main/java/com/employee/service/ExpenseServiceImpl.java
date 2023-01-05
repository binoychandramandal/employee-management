package com.employee.service;

import com.employee.converter.VOEntityConverterUtil;
import com.employee.dao.CategoryDao;
import com.employee.dao.CategoryExpenseDao;
import com.employee.dao.ExpenseDao;
import com.employee.reportvo.CategoryWiseReport;
import com.employee.vo.ExpenseVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ExpenseServiceImpl implements ExpenseService {

    @Autowired
    private ExpenseDao expenseDao;

    @Autowired
    private CategoryDao categoryDao;

    @Autowired
    private CategoryExpenseDao categoryExpenseDao;

    @Override
    public ExpenseVO addExpense(ExpenseVO expense) {
        return VOEntityConverterUtil.convert(expenseDao.save(VOEntityConverterUtil.convert(expense)));
    }

    @Override
    public List<ExpenseVO> getAllExpenses() {
        return expenseDao.findAll().stream().map(VOEntityConverterUtil::convert).collect(Collectors.toList());
    }

    @Override
    public ExpenseVO getExpenseById(int id) {
        return expenseDao.findById(id).map(VOEntityConverterUtil::convert).orElse(null);
    }

    @Override
    public boolean deleteExpense(int id) {
        try {
            ExpenseVO expenseVO = getExpenseById(id);
            if (expenseVO == null)
                return false;
            expenseDao.deleteById(id);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    @Transactional
    public boolean updateExpense(ExpenseVO expense) {
        return categoryExpenseDao.updateExpense(VOEntityConverterUtil.convert(expense));
    }

    @Override
    public List<CategoryWiseReport> countTotalExpenseByCategory() {
       List<CategoryWiseReport> l = expenseDao.countTotalExpenseByCategory();
        for (int i = 0; i < l.size(); i++) {
            System.out.println(l.get(0));
        }
        return expenseDao.countTotalExpenseByCategory();
    }

    @Override
    public List<ExpenseVO> findByCategoryId(int id) {
        return expenseDao.findByCategoryId(id).stream().map(VOEntityConverterUtil::convert).collect(Collectors.toList());
    }

}
