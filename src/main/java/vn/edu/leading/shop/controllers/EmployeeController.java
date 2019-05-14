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
import vn.edu.leading.shop.models.EmployeeModel;
import vn.edu.leading.shop.services.EmployeeService;

import javax.validation.Valid;

@Controller
public class EmployeeController {

    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("admin/employees")
    public String list(Model model) {
        model.addAttribute("employees", employeeService.findAll());
        return "admin/pages/employees";
    }

    @GetMapping("employees/search")
    public String search(@RequestParam("term") String term, Model model) {
        if (StringUtils.isEmpty(term)) {
            return "redirect:/admin/employees";
        }
        model.addAttribute("employees", employeeService.search(term));
        return "employees/list";
    }

    @GetMapping("/employees/add")
    public String add(Model model) {
        model.addAttribute("employeeModel", new EmployeeModel());
        return "employees/form";
    }

    @GetMapping("/employees/{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model) {
        model.addAttribute("customerModel", employeeService.findById(id));
        return "employees/form";
    }

    @PostMapping("/employees/save")
    public String save(@Valid EmployeeModel employee, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return "employees/form";
        }
        employeeService.save(employee);
        redirect.addFlashAttribute("successMessage", "Saved employee successfully!");
        return "redirect:/admin/employees";
    }

    @GetMapping("/employees/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirect) {
        if (employeeService.delete(id)) {
            redirect.addFlashAttribute("successMessage", "Deleted employee successfully!");
            return "redirect:/admin/employees";
        } else {
            redirect.addFlashAttribute("successMessage", "Not found!!!");
            return "redirect:/admin/employees";
        }
    }
}
