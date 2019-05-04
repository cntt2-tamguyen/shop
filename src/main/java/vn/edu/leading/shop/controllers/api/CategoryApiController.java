package vn.edu.leading.shop.controllers.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.edu.leading.shop.models.CategoryModel;
import vn.edu.leading.shop.services.CategoryService;

import javax.validation.Valid;

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

    @GetMapping("/categories/add")
    public ResponseEntity<?> add() {
        return new ResponseEntity<>(new CategoryModel(), HttpStatus.OK);
    }

    @GetMapping("/categories/{id}/edit")
    public ResponseEntity<?> edit(@PathVariable("id") Long id) {
        return new ResponseEntity<>(categoryService.findById(id), HttpStatus.OK);
    }

//    @PostMapping("/categories/save")
//    public ResponseEntity<?> save(@Valid CategoryModel category, BindingResult result, RedirectAttributes redirect) {
//        if (result.hasErrors()) {
//            return "categories/form";
//        }
//        categoryService.save(category);
//        redirect.addFlashAttribute("successMessage", "Saved category successfully!");
//        return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
//    }

    @GetMapping("/categories/{id}/delete")
    public ResponseEntity<?> delete(@PathVariable Long id, RedirectAttributes redirect) {
        if (categoryService.delete(id)) {
            redirect.addFlashAttribute("successMessage", "Deleted category successfully!");
            return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
        } else {
            redirect.addFlashAttribute("successMessage", "Not found!!!");
            return new ResponseEntity<>(categoryService.findAll(), HttpStatus.OK);
        }
    }
}
