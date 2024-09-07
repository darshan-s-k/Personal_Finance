package com.M1.MDB.Web.Service;

import com.M1.MDB.Web.Model.Category;

import java.util.List;

public interface CategoryService {
    List<Category> getAllCategoriesByUserId(String userId);
    Category getCategoryById(String userId, String categoryId);
    Category addCategory(Category category);
    Category updateCategory(String userId, String categoryName, Category category);
    void updateCategorySpent(String userId, String categoryName, double amount);
}
