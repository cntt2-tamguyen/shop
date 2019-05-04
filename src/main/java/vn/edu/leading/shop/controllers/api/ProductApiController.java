package vn.edu.leading.shop.controllers.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.edu.leading.shop.models.ProductModel;
import vn.edu.leading.shop.services.ProductService;

import javax.validation.Valid;

@Controller
public class ProductApiController {

    private final ProductService productService;

    public ProductApiController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping("/products")
    public ResponseEntity<?> list() {
        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/products/add")
    public ResponseEntity<?>  add() {
        return new ResponseEntity<>( new ProductModel(), HttpStatus.OK);
    }

    @GetMapping("/products/{id}/edit")
    public ResponseEntity<?> edit(@PathVariable("id") Long id){
        return new ResponseEntity<>( productService.findById(id), HttpStatus.OK);
    }

//    @PostMapping("/products/save")
//    public ResponseEntity<?>  save(@Valid ProductModel product, BindingResult result, RedirectAttributes redirect) {
//        if (result.hasErrors()) {
//            return "products/form";
//        }
//        productService.save(product);
//        redirect.addFlashAttribute("successMessage", "Saved product successfully!");
//        return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
//    }

    @GetMapping("/products/{id}/delete")
    public ResponseEntity<?> delete(@PathVariable Long id, RedirectAttributes redirect) {
        if (productService.delete(id)) {
            redirect.addFlashAttribute("successMessage", "Deleted product successfully!");
            return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
        } else {
            redirect.addFlashAttribute("successMessage", "Not found!!!");
            return new ResponseEntity<>(productService.findAll(), HttpStatus.OK);
        }
    }
}
