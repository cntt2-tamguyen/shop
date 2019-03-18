package vn.edu.leading.shop.controllers;

import org.springframework.beans.support.PagedListHolder;
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

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@Controller
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/employees")
    public String index(HttpServletRequest request) {
        request.getSession().setAttribute("employees", null);
        return "redirect:/employees/page/1";
    }

    @GetMapping("/employees/page/{pageNumber}")
    public String showEmployeePage(HttpServletRequest request,
                                   @PathVariable int pageNumber, Model model) {
        PagedListHolder<EmployeeModel> pages = (PagedListHolder<EmployeeModel>) request.getSession().getAttribute("employeeList");
        int pagesize = 3;
        List<EmployeeModel> list = employeeService.findAll();
        System.out.println(list.size());
        if (pages == null) {
            pages = new PagedListHolder<>(list);
            pages.setPageSize(pagesize);
        } else {
            final int goToPage = pageNumber - 1;
            if (goToPage <= pages.getPageCount() && goToPage >= 0) {
                pages.setPage(goToPage);
            }
        }
        request.getSession().setAttribute("employeeList", pages);
        int current = pages.getPage() + 1;
        int begin = Math.max(1, current - list.size());
        int end = Math.min(begin + 5, pages.getPageCount());
        int totalPageCount = pages.getPageCount();
        String baseUrl = "/employees/page/";

        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);
        model.addAttribute("totalPageCount", totalPageCount);
        model.addAttribute("baseUrl", baseUrl);
        model.addAttribute("employees", pages);

        return "employees/list";
    }

    @GetMapping("/employees/search/{pageNumber}")
    public String search(@RequestParam("term") String term, Model model, HttpServletRequest request,
                         @PathVariable int pageNumber) {
        if (StringUtils.isEmpty(term)) {
            return "redirect:/employees";
        }
        List<EmployeeModel> list = employeeService.search(term);
        if (list == null) {
            return "redirect:/employee";
        }
        PagedListHolder<EmployeeModel> pages;
        int pagesize = 3;

        pages = new PagedListHolder<EmployeeModel>(list);
        pages.setPageSize(pagesize);

        final int goToPage = pageNumber - 1;
        if (goToPage <= pages.getPageCount() && goToPage >= 0) {
            pages.setPage(goToPage);
        }

        request.getSession().setAttribute("employeelist", pages);
        int current = pages.getPage() + 1;
        int begin = Math.max(1, current - list.size());
        int end = Math.min(begin + 5, pages.getPageCount());
        int totalPageCount = pages.getPageCount();
        String baseUrl = "/employees/page/";

        model.addAttribute("beginIndex", begin);
        model.addAttribute("endIndex", end);
        model.addAttribute("currentIndex", current);
        model.addAttribute("totalPageCount", totalPageCount);
        model.addAttribute("baseUrl", baseUrl);
        model.addAttribute("employees", pages);

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
        return "redirect:/employees";
    }

    @GetMapping("/employees/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirect) {
        if (employeeService.delete(id)) {
            redirect.addFlashAttribute("successMessage", "Deleted employee successfully!");
            return "redirect:/employees";
        } else {
            redirect.addFlashAttribute("successMessage", "Not found!!!");
            return "redirect:/employees";
        }
    }
}
