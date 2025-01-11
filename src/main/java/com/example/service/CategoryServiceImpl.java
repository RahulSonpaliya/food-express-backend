package com.example.service;

import com.example.model.response.AllCategoriesResponse;
import com.example.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Value("${categoryIconPath}")
    private String categoryIconPath;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public AllCategoriesResponse getAllCategories() {
        final var response = new AllCategoriesResponse("Success", true);
        response.setCategoryList(categoryRepository.findAll());
        return response;
    }

    @Override
    public Resource downloadCategoryIcon(String iconName) {
        File dir = new File(categoryIconPath + iconName);
        try {
            if (dir.exists()) {
                return new UrlResource(dir.toURI());
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return null;
        }
        return null;
    }
}
