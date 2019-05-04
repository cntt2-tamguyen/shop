package vn.edu.leading.shop.controllers.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import vn.edu.leading.shop.dto.CategoryDTO;
import vn.edu.leading.shop.models.CategoryModel;
import vn.edu.leading.shop.services.CategoryService;

import javax.validation.Valid;
import java.util.HashMap;

@RestController
@RequestMapping("/api/v1")
public class CategoryApiController {

    private final CategoryService categoryService;

    public CategoryApiController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }


    @GetMapping("/categories")
    public ResponseEntity<?> list() {
        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
    }

    @PostMapping("/categories")
    public ResponseEntity save(@Valid @RequestBody CategoryDTO categoryDTO) {
        CategoryModel categoryModel = new CategoryModel();
        categoryModel.setCategoryName(categoryDTO.getCategoryName());
        categoryModel.setDescription(categoryDTO.getDescription());
        return new ResponseEntity(categoryService.save(categoryModel), HttpStatus.OK);
    }

    @PutMapping("/categories")
    public ResponseEntity update(@Valid @RequestBody CategoryDTO categoryDTO) {
        CategoryModel categoryModel = new CategoryModel();
        categoryModel.setCategoryName(categoryDTO.getCategoryName());
        categoryModel.setDescription(categoryDTO.getDescription());
        categoryModel.setId(categoryDTO.getId());
        return new ResponseEntity(categoryService.save(categoryModel), HttpStatus.OK);
    }

//    @PostMapping("/categories")
//    public ResponseEntity save(@Valid @RequestBody HashMap<String, String> body) {
//        CategoryModel categoryModel = new CategoryModel();
//        categoryModel.setCategoryName(body.get("categoryName"));
//        categoryModel.setDescription(body.get("description"));
//        return new ResponseEntity(categoryService.save(categoryModel), HttpStatus.OK);
//    }

    @DeleteMapping("/categories")
    public ResponseEntity<?> delete(@RequestBody CategoryDTO categoryDTO) {
        return new ResponseEntity<>(categoryService.delete(categoryDTO.getId()), HttpStatus.OK);
    }
}
