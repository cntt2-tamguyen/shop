package vn.edu.leading.shop.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.edu.leading.shop.models.OrderDetailModel;
import vn.edu.leading.shop.services.CustomerService;
import vn.edu.leading.shop.services.OrderDetailService;
import vn.edu.leading.shop.services.OrderService;
import vn.edu.leading.shop.services.ProductService;

import javax.validation.Valid;

@Controller
public class OrderDetailController {

    private final OrderDetailService orderDetailService;

    private final OrderService orderService;

    private final ProductService productService;

    public OrderDetailController(OrderDetailService orderDetailService, OrderService orderService, ProductService productService) {
        this.orderDetailService = orderDetailService;
        this.orderService = orderService;
        this.productService = productService;
    }

    @GetMapping("/admin/orderDetails")
    public String list(Model model) {
        model.addAttribute("orderDetails", orderDetailService.findAll());
        model.addAttribute("orders", orderService.findAll());
        model.addAttribute("products", productService.findAll());
        return "admin/pages/orderDetails";
    }

    @GetMapping("orderDetails/search")
    public String search(@RequestParam("term") String term, Model model) {
        if (StringUtils.isEmpty(term)) {
            return "redirect:/admin/orderDetails";
        }
        model.addAttribute("orderDetails", orderDetailService.search(term));
        return "orderDetails/list";
    }

    @GetMapping("/orderDetails/add")
    public String add(Model model) {
        model.addAttribute("orderDetailModel", new OrderDetailModel());
        model.addAttribute("orders", orderService.findAll());
        model.addAttribute("products", productService.findAll());
        return "orderDetails/form";
    }

    @GetMapping("/orderDetails/{id}/edit")
    public String edit(@PathVariable("id") Long id, Model model) {
        model.addAttribute(orderDetailService.findById(id));
        return "admin/pages/orderDetails";
    }

    @PostMapping("admin/orderDetails")
    public String save(@Valid OrderDetailModel orderDetail, Model model) {
        orderDetailService.save(orderDetail);
        model.addAttribute("orderDetails", orderDetailService.findAll());
        model.addAttribute("orders", orderService.findAll());
        model.addAttribute("products", productService.findAll());
        return "admin/pages/orderDetails";
    }

    @GetMapping("/orderDetails/{id}/delete")
    public String delete(@PathVariable Long id, RedirectAttributes redirect) {
        if (orderDetailService.delete(id)) {
            redirect.addFlashAttribute("successMessage", "Deleted orderDetails successfully!");
            return "redirect:/admin/orderDetails";
        } else {
            redirect.addFlashAttribute("successMessage", "Not found!!!");
            return "redirect:/admin/orderDetails";
        }
    }
}
