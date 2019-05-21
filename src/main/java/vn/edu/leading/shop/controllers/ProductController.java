package vn.edu.leading.shop.controllers;

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
import vn.edu.leading.shop.services.CategoryService;
import vn.edu.leading.shop.services.ProductService;
import vn.edu.leading.shop.services.SupplierService;

import javax.validation.Valid;

@Controller
public class ProductController {

    private final ProductService productService;

    private final CategoryService categoryService;

    private final SupplierService supplierService;

    public ProductController(ProductService productService, CategoryService categoryService, SupplierService supplierService) {
        this.productService = productService;
        this.categoryService = categoryService;
        this.supplierService = supplierService;
    }

    @GetMapping("/admin/products")
    public String list(Model model) {
        model.addAttribute("products", productService.findAll());
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("suppliers", supplierService.findAll());
        return "admin/pages/products";
    }

    @GetMapping("products/search")
    public String search(@RequestParam("term") String term, Model model) {
        if (StringUtils.isEmpty(term)) {
            return "redirect:/products";
        }
        model.addAttribute("products", productService.search(term));
        return "products/list";
    }

    @GetMapping("/products/add")
    public String add(Model model) {
        model.addAttribute("productModel", new ProductModel());
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("suppliers", supplierService.findAll());
        return "products/form";
    }

    @GetMapping("/products/{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model) {
        model.addAttribute(productService.findById(id));
        return "admin/pages/products";
    }

    @PostMapping("admin/products")
    public String save(@Valid ProductModel product,Model model) {
        productService.save(product);
        model.addAttribute("products", productService.findAll());
        model.addAttribute("categories", categoryService.findAll());
        model.addAttribute("suppliers", supplierService.findAll());
        return "admin/pages/products";
    }

    @GetMapping("/products/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirect) {
        if (productService.delete(id)) {
            redirect.addFlashAttribute("successMessage", "Deleted product successfully!");
            return "redirect:/admin/products";
        } else {
            redirect.addFlashAttribute("successMessage", "Not found!!!");
            return "redirect:/admin/products";
        }
    }
}