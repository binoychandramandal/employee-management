package com.employee.service;

import com.employee.vo.CategoryVO;
import com.employee.vo.ExpenseVO;

import java.util.List;

public interface CategoryService {

    CategoryVO addCategory(CategoryVO categoryVO);

    List<CategoryVO> getAllCategory();

    CategoryVO getCategoryById(int id);

    CategoryVO deleteCategory(int id);

    CategoryVO updateCategory(CategoryVO categoryVO);
}
