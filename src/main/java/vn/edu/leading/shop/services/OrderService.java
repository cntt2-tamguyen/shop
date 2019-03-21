package vn.edu.leading.shop.services;

import vn.edu.leading.shop.dto.OrderDetailDTO;
import vn.edu.leading.shop.dto.OrderNameDTO;
import vn.edu.leading.shop.models.OrderModel;

import java.util.List;

public interface OrderService {

    List<OrderModel> findAll();

    List<OrderModel> search(String term);

    OrderModel findById(Long id);

    boolean update(OrderModel order);

    void save(OrderModel order);

    boolean delete(Long id);

    OrderNameDTO getNameCustomer(Long id);

    List<OrderDetailDTO> listProduct(Long id);
}
