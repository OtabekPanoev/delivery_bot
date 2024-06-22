package uz.pdp.repository;

import lombok.Getter;
import lombok.NonNull;
import uz.pdp.model.Category;

import java.util.List;
import java.util.Optional;

public class CategoryRepository implements BaseRepository<Category, String> {
    @Getter
    private static final CategoryRepository instance = new CategoryRepository();
    private CategoryRepository() {}


    @Override
    public Boolean save(Category category) {
        return null;
    }

    @Override
    public Boolean update(String id, Category category) {
        return null;
    }

    @Override
    public List<Category> findAll() {
        return getAllCategoriesFromFile();
    }

    @Override
    public Optional<Category> findById(String id) {
        return Optional.empty();
    }

    @NonNull
    private List<Category> getAllCategoriesFromFile() {
        return null;
    }
    private void setAllCategoriesFromFile(List<Category> categories) {
        return;
    }
}
