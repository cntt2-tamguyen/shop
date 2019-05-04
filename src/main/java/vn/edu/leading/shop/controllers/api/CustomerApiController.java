package vn.edu.leading.shop.controllers.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.edu.leading.shop.models.CustomerModel;
import vn.edu.leading.shop.services.CustomerService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class CustomerApiController {

    private final CustomerService customerService;

    public CustomerApiController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping("/customers")
    public ResponseEntity<?> list() {
        return new ResponseEntity<>(customerService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/customers/add")
    public ResponseEntity<?> add() {
        return new ResponseEntity<>(new CustomerModel(), HttpStatus.OK);
    }

    @GetMapping("/customers/{id}/edit")
    public ResponseEntity<?> edit(@PathVariable("id") Long id) {
        return new ResponseEntity<>(customerService.findById(id), HttpStatus.OK);
    }

    @PostMapping("/customers/save")
//    public ResponseEntity<?> save(@Valid CustomerModel customer, BindingResult result, RedirectAttributes redirect) {
//        if (result.hasErrors()) {
//            return "customers/form";
//        }
//        customerService.save(customer);
//        redirect.addFlashAttribute("successMessage", "Saved customer successfully!");
//        return new ResponseEntity<>(customerService.findAll(), HttpStatus.OK);
//    }

    @GetMapping("/customers/{id}/delete")
    public ResponseEntity<?> delete(@PathVariable Long id, RedirectAttributes redirect) {
        if (customerService.delete(id)) {
            redirect.addFlashAttribute("successMessage", "Deleted customer successfully!");
            return new ResponseEntity<>(customerService.findAll(), HttpStatus.OK);
        } else {
            redirect.addFlashAttribute("successMessage", "Not found!!!");
            return new ResponseEntity<>(customerService.findAll(), HttpStatus.OK);
        }
    }
}
