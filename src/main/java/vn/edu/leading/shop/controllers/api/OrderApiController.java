package vn.edu.leading.shop.controllers.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.edu.leading.shop.models.OrderModel;
import vn.edu.leading.shop.services.OrderService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class OrderApiController {

    private final OrderService orderService;

    public OrderApiController(OrderService orderService) {
        this.orderService = orderService;
    }

    @GetMapping("/orders")
    public ResponseEntity<?> list() {
        return new ResponseEntity<>(orderService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/orders/add")
    public ResponseEntity<?> add() {
        return new ResponseEntity<>(new OrderModel(), HttpStatus.OK);
    }

    @GetMapping("/orders/{id}/edit")
    public ResponseEntity<?> edit(@PathVariable("id") Long id) {
        return new ResponseEntity<>( orderService.findById(id), HttpStatus.OK);
    }

//    @PostMapping("/orders/save")
//    public ResponseEntity<?> save(@Valid OrderModel order, BindingResult result, RedirectAttributes redirect) {
//        if (result.hasErrors()) {
//            return "orders/form";
//        }
//        orderService.save(order);
//        redirect.addFlashAttribute("successMessage", "Saved order successfully!");
//        return new ResponseEntity<>(orderService.findAll(), HttpStatus.OK);
//    }

    @GetMapping("/orders/{id}/delete")
    public ResponseEntity<?> delete(@PathVariable Long id, RedirectAttributes redirect) {
        if (orderService.delete(id)) {
            redirect.addFlashAttribute("successMessage", "Deleted order successfully!");
            return new ResponseEntity<>(orderService.findAll(), HttpStatus.OK);
        } else {
            redirect.addFlashAttribute("successMessage", "Not found!!!");
            return new ResponseEntity<>(orderService.findAll(), HttpStatus.OK);
        }
    }

//    @GetMapping("orders/{id}")
//    public ResponseEntity<?> printf(@PathVariable("id") Long id) {
//        return new ResponseEntity<>(orderService.findById(id).getOrderDetails(),
//                orderService.findById(id).getCustomerModel().getCustomerName(),
//                orderService.findById(id).getEmployeeModel().getFirstName(),
//                orderService.findById(id).getShipperModel().getShipperName(),
//                HttpStatus.OK);
//    }
}
