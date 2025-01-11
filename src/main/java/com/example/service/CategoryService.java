package com.example.service;

import com.example.model.response.AllCategoriesResponse;
import org.springframework.core.io.Resource;

public interface CategoryService {
    AllCategoriesResponse getAllCategories();
    Resource downloadCategoryIcon(String iconName);
}
