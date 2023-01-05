package com.employee.service;

import com.employee.converter.VOEntityConverterUtil;
import com.employee.dao.CategoryDao;
import com.employee.entity.Category;
import com.employee.vo.CategoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public CategoryVO addCategory(CategoryVO categoryVO) {
        return VOEntityConverterUtil.convert(categoryDao.save(VOEntityConverterUtil.convert(categoryVO)));
    }

    @Override
    public List<CategoryVO> getAllCategory() {
        return categoryDao.findAll().stream().map(VOEntityConverterUtil::convert).collect(Collectors.toList());
    }

    @Override
    public CategoryVO getCategoryById(int id) {
        Optional<Category> response = categoryDao.findById(id);
        return response.map(VOEntityConverterUtil::convert).orElse(null);
    }

    @Override
    public CategoryVO deleteCategory(int id) {
        try {
            CategoryVO categoryVO = getCategoryById(id);
            if (categoryVO == null)
                return null;
            categoryDao.deleteById(id);
            return categoryVO;
        } catch (Exception e) {
            return null;
        }

    }

    @Override
    public CategoryVO updateCategory(CategoryVO categoryVO) {
        CategoryVO categoryVO1 = getCategoryById(categoryVO.getId());
        if (categoryVO1 == null) {
            return null;
        }
        return VOEntityConverterUtil.convert(categoryDao.save(VOEntityConverterUtil.convert(categoryVO)));
    }
}
