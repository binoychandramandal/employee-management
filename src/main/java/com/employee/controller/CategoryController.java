package com.employee.controller;

import com.employee.service.CategoryService;
import com.employee.vo.CategoryVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
    public CategoryVO addCategory(@RequestBody CategoryVO categoryVO) {
        return categoryService.addCategory(categoryVO);
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<CategoryVO> getAllCategory() {
        return categoryService.getAllCategory();
    }

    @RequestMapping("/{id}")
    public CategoryVO getCategoryById(@PathVariable("id") int id) {
        return categoryService.getCategoryById(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/{id}")
    public Map<String, String> deleteCategory(@PathVariable("id") int id) {
        Map<String,String> output =new HashMap<>();
        output.put("message", categoryService.deleteCategory(id) == null ? "Not Deleted" : "Deleted");
        return output;
    }

    @RequestMapping(method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_VALUE)
    public Map<String, String> updateCategory(@RequestBody CategoryVO categoryVO) {
        Map<String,String> output =new HashMap<>();
        output.put("message", categoryService.updateCategory(categoryVO) == null ? "Not Updated" : "Updated");
        return output;
    }
}
