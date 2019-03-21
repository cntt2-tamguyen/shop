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
import vn.edu.leading.shop.services.OrderService;

import javax.validation.Valid;

@Controller
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public String list(Model model) {
        model.addAttribute("orders", orderService.findAll());
        return "orders/list";
    }

    @GetMapping("orders/search")
    public String search(@RequestParam("term") String term, Model model) {
        if (StringUtils.isEmpty(term)) {
            return "redirect:/orders";
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
        model.addAttribute("orderModel", orderService.findById(id));
        return "orders/form";
    }

    @PostMapping("/orders/save")
    public String save(@Valid OrderModel order, BindingResult result, RedirectAttributes redirect) {
        if (result.hasErrors()) {
            return "orders/form";
        }
        orderService.save(order);
        redirect.addFlashAttribute("successMessage", "Saved order successfully!");
        return "redirect:/orders";
    }

    @GetMapping("/orders/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirect) {
        if (orderService.delete(id)) {
            redirect.addFlashAttribute("successMessage", "Deleted order successfully!");
            return "redirect:/orders";
        } else {
            redirect.addFlashAttribute("successMessage", "Not found!!!");
            return "redirect:/orders";
        }
    }

    @GetMapping("/orders/{id}/print")
    public String print(@PathVariable("id") Long id, Model model) {
        model.addAttribute("name", orderService.getNameCustomer(id));
        model.addAttribute("list", orderService.listProduct(id));
        return "orders/print";
    }
}
