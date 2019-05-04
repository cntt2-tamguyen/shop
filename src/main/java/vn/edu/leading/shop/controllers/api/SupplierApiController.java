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
import vn.edu.leading.shop.models.SupplierModel;
import vn.edu.leading.shop.services.SupplierService;

import javax.validation.Valid;

@Controller
public class SupplierApiController {

    private final SupplierService supplierService;

    public SupplierApiController(SupplierService supplierService) {
        this.supplierService = supplierService;
    }

    @GetMapping("/suppliers")
    public ResponseEntity<?> list() {
        return new ResponseEntity<>(supplierService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/suppliers/add")
    public ResponseEntity<?> add() {
        return new ResponseEntity<>(new SupplierModel(), HttpStatus.OK);
    }

    @GetMapping("/suppliers/{id}/edit")
    public ResponseEntity<?> edit(@PathVariable("id") Long id) {
        return new ResponseEntity<>(supplierService.findById(id), HttpStatus.OK);
    }

//    @PostMapping("/suppliers/save")
//    public ResponseEntity<?> save(@Valid SupplierModel supplier, BindingResult result, RedirectAttributes redirect) {
//        if (result.hasErrors()) {
//            return "suppliers/form";
//        }
//        supplierService.save(supplier);
//        redirect.addFlashAttribute("successMessage", "Saved supplier successfully!");
//        return new ResponseEntity<>(supplierService.findAll(), HttpStatus.OK);
//    }

    @GetMapping("/suppliers/{id}/delete")
    public ResponseEntity<?> delete(@PathVariable Long id, RedirectAttributes redirect) {
        if (supplierService.delete(id)) {
            redirect.addFlashAttribute("successMessage", "Deleted supplier successfully!");
            return new ResponseEntity<>(supplierService.findAll(), HttpStatus.OK);
        } else {
            redirect.addFlashAttribute("successMessage", "Not found!!!");
            return new ResponseEntity<>(supplierService.findAll(), HttpStatus.OK);
        }
    }
}
