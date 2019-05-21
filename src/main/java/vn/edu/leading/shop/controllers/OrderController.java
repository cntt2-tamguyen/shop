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
import vn.edu.leading.shop.models.OrderModel;
import vn.edu.leading.shop.services.CustomerService;
import vn.edu.leading.shop.services.EmployeeService;
import vn.edu.leading.shop.services.OrderService;
import vn.edu.leading.shop.services.ShipperService;

import javax.validation.Valid;

@Controller
public class OrderController {

    private final OrderService orderService;

    private final CustomerService customerService;

    private final EmployeeService employeeService;

    private final ShipperService shipperService;

    public OrderController(OrderService orderService, CustomerService customerService, EmployeeService employeeService, ShipperService shipperService) {
        this.orderService = orderService;
        this.customerService = customerService;
        this.employeeService = employeeService;
        this.shipperService = shipperService;
    }

    @GetMapping("/admin/orders")
    public String list(Model model) {
        model.addAttribute("orders", orderService.findAll());
        model.addAttribute("customers", customerService.findAll());
        model.addAttribute("employees", employeeService.findAll());
        model.addAttribute("shippers", shipperService.findAll());
        return "admin/pages/orders";
    }

    @GetMapping("orders/search")
    public String search(@RequestParam("term") String term, Model model) {
        if (StringUtils.isEmpty(term)) {
            return "redirect:/admin/orders";
        }
        model.addAttribute("orders", orderService.search(term));
        return "orders/list";
    }

    @GetMapping("/orders/add")
    public String add(Model model) {
        model.addAttribute("orderModel", new OrderModel());
        return "orders/form";
    }

    @GetMapping("/orders/{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model) {
        model.addAttribute(orderService.findById(id));
        return "admin/pages/orders";
    }

    @PostMapping("admin/orders")
    public String save(@Valid OrderModel order, Model model) {
        orderService.save(order);
        model.addAttribute("orders", orderService.findAll());
        model.addAttribute("customers", customerService.findAll());
        model.addAttribute("employees", employeeService.findAll());
        model.addAttribute("shippers", shipperService.findAll());
        return "admin/pages/orders";
    }

    @GetMapping("/orders/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirect) {
        if (orderService.delete(id)) {
            redirect.addFlashAttribute("successMessage", "Deleted order successfully!");
            return "redirect:/admin/orders";
        } else {
            redirect.addFlashAttribute("successMessage", "Not found!!!");
            return "redirect:/admin/orders";
        }
    }

    @GetMapping("orders/{id}")
    public String printf(@PathVariable("id") Long id, Model model) {
        model.addAttribute("orderDetails", orderService.findById(id).getOrderDetails());
        model.addAttribute("customerName", orderService.findById(id).getCustomerModel().getCustomerName());
        model.addAttribute("employeeName", orderService.findById(id).getEmployeeModel().getFirstName());
        model.addAttribute("shipperName", orderService.findById(id).getShipperModel().getShipperName());
        return "orderDetails/list";
    }
}
