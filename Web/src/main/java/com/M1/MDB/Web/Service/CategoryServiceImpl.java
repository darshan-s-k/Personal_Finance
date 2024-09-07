package com.M1.MDB.Web.Service;

import com.M1.MDB.Web.Model.Category;
import com.M1.MDB.Web.Repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public List<Category> getAllCategoriesByUserId(String userId) {
        return categoryRepository.findByUserId(userId);
    }

    @Override
    public Category getCategoryById(String userId, String categoryId) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        if (category.isPresent() && category.get().getUserId().equals(userId)) {
            return category.get();
        }
        return null;
    }

    @Override
    public Category addCategory(Category category) {
        return categoryRepository.save(category);
    }

    @Override
    public Category updateCategory(String userId, String categoryName, Category category) {
        Category existingCategory = categoryRepository.findByUserIdAndName(userId, categoryName);
        if (existingCategory != null) {
            existingCategory.setBudget(category.getBudget());
            existingCategory.setSpent(category.getSpent());
            return categoryRepository.save(existingCategory);
        }
        return null;
    }

    @Override
    public void updateCategorySpent(String userId, String categoryName, double amount) {
        Category category = categoryRepository.findByUserIdAndName(userId, categoryName);
        if (category != null) {
            double currentSpent = category.getSpent();
            category.setSpent(currentSpent + amount);
            categoryRepository.save(category);
        } else {
            throw new RuntimeException("Category not found");
        }
    }
}
