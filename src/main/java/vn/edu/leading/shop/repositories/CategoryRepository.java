package vn.edu.leading.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.leading.shop.models.CategoryModel;


import java.util.List;

public interface CategoryRepository extends JpaRepository<CategoryModel,Long> {
    List<CategoryModel> findByCategoryNameContaining(String term);
}
