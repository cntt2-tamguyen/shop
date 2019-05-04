package vn.edu.leading.shop.controllers.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.*;
import vn.edu.leading.shop.dto.ProductDTO;
import vn.edu.leading.shop.models.CategoryModel;
import vn.edu.leading.shop.models.ProductModel;
import vn.edu.leading.shop.models.SupplierModel;
import vn.edu.leading.shop.services.CategoryService;
import vn.edu.leading.shop.services.ProductService;
import vn.edu.leading.shop.services.SupplierService;

import java.util.List;

@RestController
@RequestMapping("/api/v2")
public class ProductApiController {

    private final ProductService productService;
    private final SupplierService supplierService;
    private final CategoryService categoryService;

    public ProductApiController(ProductService productService, SupplierService supplierService, CategoryService categoryService) {
        this.productService = productService;
        this.supplierService = supplierService;
        this.categoryService = categoryService;
    }

    @GetMapping("/products")
    public ResponseEntity<List<ProductModel>> list() {
        return new ResponseEntity(productService.findAll(), HttpStatus.OK);
    }

    @PostMapping(value = "/products", produces = "application/json")
    public ResponseEntity save(@RequestBody ProductDTO dto) {
        SupplierModel supplierModel = supplierService.findById(dto.getSupplierId()).orElseThrow(() -> new UsernameNotFoundException("supplierModel null"));
        CategoryModel categoryModel = categoryService.findById(dto.getCategoryId());
        if (categoryModel == null) {
            throw new UsernameNotFoundException("categoryModel null");
        }
        ProductModel productModel = new ProductModel();
        productModel.setPrice(dto.getPrice());
        productModel.setUnit(dto.getUnit());
        productModel.setProductName(dto.getProductName());
        productModel.setSupplierModel(supplierModel);
        productModel.setCategoryModel(categoryModel);

        return new ResponseEntity(productService.save(productModel), HttpStatus.OK);
    }
}
