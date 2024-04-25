package uz.pdp.service;

import lombok.Getter;
import uz.pdp.model.Category;
import uz.pdp.repository.CategoryRepository;

import java.util.List;

public class CategoryService {
    @Getter
    private static final CategoryService instance = new CategoryService();
    private final CategoryRepository categoryRepository = CategoryRepository.getInstance();
    private CategoryService() {}

    public List<Category> findAll() {
        return categoryRepository.findAll();
    }
}
