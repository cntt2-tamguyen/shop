package vn.edu.leading.shop.controllers.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.edu.leading.shop.models.EmployeeModel;
import vn.edu.leading.shop.services.EmployeeService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class EmployeeApiController {

    private final EmployeeService employeeService;

    public EmployeeApiController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public ResponseEntity<?> list() {
        return new ResponseEntity<>(employeeService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/employees/add")
    public ResponseEntity<?> add() {
        return new ResponseEntity<>(new EmployeeModel(), HttpStatus.OK);
    }

    @GetMapping("/employees/{id}/edit")
    public ResponseEntity<?> edit(@PathVariable("id") Long id) {
        return new ResponseEntity<>(employeeService.findById(id), HttpStatus.OK);
    }

//    @PostMapping("/employees/save")
//    public ResponseEntity<?> save(@Valid EmployeeModel employee, BindingResult result, RedirectAttributes redirect) {
//        if (result.hasErrors()) {
//            return "employees/form";
//        }
//        employeeService.save(employee);
//        redirect.addFlashAttribute("successMessage", "Saved employee successfully!");
//        return new ResponseEntity<>(employeeService.findAll(), HttpStatus.OK);
//    }

    @GetMapping("/employees/{id}/delete")
    public ResponseEntity<?> delete(@PathVariable Long id, RedirectAttributes redirect) {
        if (employeeService.delete(id)) {
            redirect.addFlashAttribute("successMessage", "Deleted employee successfully!");
            return new ResponseEntity<>(employeeService.findAll(), HttpStatus.OK);
        } else {
            redirect.addFlashAttribute("successMessage", "Not found!!!");
            return new ResponseEntity<>(employeeService.findAll(), HttpStatus.OK);
        }
    }
}
