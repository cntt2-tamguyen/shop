package vn.edu.leading.shop.services;

import vn.edu.leading.shop.models.CategoryModel;
import vn.edu.leading.shop.repositories.CategoryRepository;


import java.util.List;

public class CategoryServicelmpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    public CategoryServicelmpl(CategoryRepository categoryRepository){
        this.categoryRepository = categoryRepository;
    }

    @Override
    public List<CategoryModel> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public List<CategoryModel> search(String term) {
        return categoryRepository.findByCategoryNameContaining(term);
    }

    @Override
    public CategoryModel findById(Long id) {
        return categoryRepository.findById(id).get();
    }

    @Override
    public boolean update(CategoryModel category) {
        CategoryModel categoryModel = categoryRepository.findById(category.getId()).orElse(null);
        if (categoryModel == null)
            return false;
        categoryRepository.delete(categoryModel);
        return true;
    }

    @Override
    public void save(CategoryModel categoryModel) {
        categoryRepository.save(categoryModel);
    }

    @Override
    public boolean delete(Long id) {
        CategoryModel categoryModel = categoryRepository.findById(id).orElse(null);
        if(categoryModel==null)
            return false;
        categoryRepository.delete(categoryModel);
        return true;
    }
}
