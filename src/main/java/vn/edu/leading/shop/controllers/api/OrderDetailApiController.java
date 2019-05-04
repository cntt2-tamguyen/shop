package vn.edu.leading.shop.controllers.api;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import vn.edu.leading.shop.models.OrderDetailModel;
import vn.edu.leading.shop.services.OrderDetailService;

import javax.validation.Valid;

@RestController
@RequestMapping("/api/v1")
public class OrderDetailApiController {

    private final OrderDetailService orderDetailService;

    public OrderDetailApiController(OrderDetailService orderDetailService) {
        this.orderDetailService = orderDetailService;
    }

    @GetMapping("/orderDetails")
    public ResponseEntity<?> list() {
        return new ResponseEntity<>(orderDetailService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/orderDetails/add")
    public ResponseEntity<?> add(){
        return new ResponseEntity<>(new OrderDetailModel(), HttpStatus.OK);
    }

    @GetMapping("/orderDetails/{id}/edit")
    public ResponseEntity<?> edit(@PathVariable("id") Long id) {
        return return new ResponseEntity<>(orderDetailService.findById(id), HttpStatus.OK);
    }
//
//    @PostMapping("/orderDetails/save")
//    public ResponseEntity<?> save(@Valid OrderDetailModel orderDetail, BindingResult result, RedirectAttributes redirect) {
//        if (result.hasErrors()) {
//            return "orderDetails/form";
//        }
//        orderDetailService.save(orderDetail);
//        redirect.addFlashAttribute("successMessage", "Saved orderDetails successfully!");
//        return new ResponseEntity<>(orderDetailService.findAll(), HttpStatus.OK);
//    }

    @GetMapping("/orderDetails/{id}/delete")
    public ResponseEntity<?> delete(@PathVariable Long id, RedirectAttributes redirect) {
        if (orderDetailService.delete(id)) {
            redirect.addFlashAttribute("successMessage", "Deleted orderDetails successfully!");
            return new ResponseEntity<>(orderDetailService.findAll(), HttpStatus.OK);
        } else {
            redirect.addFlashAttribute("successMessage", "Not found!!!");
            return new ResponseEntity<>(orderDetailService.findAll(), HttpStatus.OK);
        }
    }
}
