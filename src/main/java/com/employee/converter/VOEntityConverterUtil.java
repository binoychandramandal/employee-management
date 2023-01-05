package com.employee.converter;

import com.employee.entity.Category;
import com.employee.entity.Employee;
import com.employee.entity.Expense;
import com.employee.vo.CategoryVO;
import com.employee.vo.ExpenseVO;

import java.util.List;
import java.util.stream.Collectors;

public class VOEntityConverterUtil {

    public static Employee convert(com.employee.vo.Employee emp) {
        if(emp==null)
            return null;
        Employee entity = new Employee();
        entity.setEid(emp.getEid());
        entity.setAddress(emp.getAddress());
        entity.setName(emp.getName());
        entity.setEmail(emp.getEmail());
        entity.setPhone(emp.getPhone());
        entity.setImageUrl(emp.getImageUrl());
        return entity;
    }

   public static com.employee.vo.Employee convert(Employee entity) {
        if(entity==null)
            return null;
        com.employee.vo.Employee emp = new com.employee.vo.Employee();
        emp.setEid(entity.getEid());
        emp.setAddress(entity.getAddress());
        emp.setName(entity.getName());
        emp.setEmail(entity.getEmail());
        emp.setPhone(entity.getPhone());
        emp.setImageUrl(entity.getImageUrl());
        return emp;
    }

    public static Expense convert(ExpenseVO expenseVO) {
        if(expenseVO==null)
            return null;
        Expense expense = new Expense();
        expense.setAmount(expenseVO.getAmount());
        if(expenseVO.getCategory()!=null){
            Category category = new Category();
            category.setId(expenseVO.getCategory().getId());
            category.setName(expenseVO.getCategory().getName());
            expense.setCategory(category);
        }
        expense.setDate(expenseVO.getDate());
        expense.setId(expenseVO.getId());
        expense.setDescription(expenseVO.getDescription());
        return expense;
    }

    public static ExpenseVO convert(Expense expense) {
        if(expense==null)
            return null;
        System.out.println("Category "+expense.getCategory());
        ExpenseVO expenseVO = new ExpenseVO();
        expenseVO.setAmount(expense.getAmount());
        if(expense.getCategory()!=null){
            CategoryVO categoryVO = new CategoryVO();
            categoryVO.setId(expense.getCategory().getId());
            categoryVO.setName(expense.getCategory().getName());
            expenseVO.setCategory(categoryVO);
        }
        expenseVO.setDate(expense.getDate());
        expenseVO.setId(expense.getId());
        expenseVO.setDescription(expense.getDescription());
        return expenseVO;
    }

    public static Category convert(CategoryVO categoryVO) {
        if(categoryVO==null)
            return null;
        Category category = new Category();
        category.setId(categoryVO.getId());
        category.setName(categoryVO.getName());
        if(categoryVO.getExpenses()!=null){
            List<Expense> l=categoryVO.getExpenses().stream().map(VOEntityConverterUtil::convert).collect(Collectors.toList());
            category.setExpenses(l);
        }
        return category;
    }

    public static CategoryVO convert(Category category) {
        if(category==null)
            return null;
        CategoryVO categoryVO = new CategoryVO();
        categoryVO.setId(category.getId());
        categoryVO.setName(category.getName());
        if(category.getExpenses()!=null){
            categoryVO.setExpenses(category.getExpenses().stream().map(VOEntityConverterUtil::convert).map(x->{x.setCategory(null);return x;}).collect(Collectors.toList()));
        }
        return categoryVO;
    }
}
