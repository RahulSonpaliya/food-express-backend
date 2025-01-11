package com.example.model.response;

import com.example.entity.Category;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class AllCategoriesResponse extends BaseResponse{

    private List<Category> categoryList;

    public AllCategoriesResponse(String message, boolean success) {
        super(message, success);
    }
}
