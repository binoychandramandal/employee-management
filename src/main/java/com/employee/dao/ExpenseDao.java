package com.employee.dao;

import com.employee.entity.Expense;
import com.employee.reportvo.CategoryWiseReport;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ExpenseDao extends JpaRepository<Expense, Integer> {
    public List<Expense> findByCategoryId(int id);

    public List<Expense> findByDate(LocalDateTime date);

    @Query("SELECT e.category.name as categoryName, AVG(e.amount) as average,MIN(e.amount) as min,MAX(e.amount) as max,SUM(e.amount) as total,COUNT(e.category) as totalTransaction FROM Expense AS e GROUP BY e.category ORDER BY e.category DESC")
    List<CategoryWiseReport> countTotalExpenseByCategory();

}
