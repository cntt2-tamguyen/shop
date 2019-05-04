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
import vn.edu.leading.shop.models.ShipperModel;
import vn.edu.leading.shop.services.ShipperService;

import javax.validation.Valid;

@Controller
public class ShipperApiController {

    private final ShipperService shipperService;

    public ShipperApiController(ShipperService shipperService) {
        this.shipperService = shipperService;
    }

    @GetMapping("/shippers")
    public ResponseEntity<?> list() {
        return new ResponseEntity<>(shipperService.findAll(), HttpStatus.OK);
    }

    @GetMapping("/shippers/add")
    public ResponseEntity<?> add(){
        return new ResponseEntity<>( new ShipperModel(),HttpStatus.OK);
    }

    @GetMapping("/shippers/{id}/edit")
    public ResponseEntity<?> edit(@PathVariable("id") Long id){
        return new ResponseEntity<>(shipperService.findById(id), HttpStatus.OK);
    }

//    @PostMapping("/shippers/save")
//    public ResponseEntity<?> save(@Valid ShipperModel shipper, BindingResult result, RedirectAttributes redirect) {
//        if (result.hasErrors()) {
//            return "shippers/form";
//        }
//        shipperService.save(shipper);
//        redirect.addFlashAttribute("successMessage", "Saved shipper successfully!");
//        return new ResponseEntity<>(shipperService.findAll(), HttpStatus.OK);
//    }

    @GetMapping("/shippers/{id}/delete")
    public ResponseEntity<?> delete(@PathVariable Long id, RedirectAttributes redirect) {
        if (shipperService.delete(id)) {
            redirect.addFlashAttribute("successMessage", "Deleted shipper successfully!");
            return new ResponseEntity<>(shipperService.findAll(), HttpStatus.OK);
        } else {
            redirect.addFlashAttribute("successMessage", "Not found!!!");
            return new ResponseEntity<>(shipperService.findAll(), HttpStatus.OK);
        }
    }
}
