package com.M1.MDB.Web.Controller;

import com.M1.MDB.Web.Model.Category;
import com.M1.MDB.Web.Service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PostMapping("/{userId}/add")
    public Category addCategory(@PathVariable String userId, @RequestBody Category category) {
        category.setUserId(userId);
        return categoryService.addCategory(category);
    }

    @PutMapping("/{userId}/{categoryName}")
    public Category updateCategory(@PathVariable String userId, @PathVariable String categoryName, @RequestBody Category category) {
        return categoryService.updateCategory(userId, categoryName, category);
    }

    @GetMapping("/{userId}")
    public List<Category> getAllCategoriesByUserId(@PathVariable String userId) {
        return categoryService.getAllCategoriesByUserId(userId);
    }
}

