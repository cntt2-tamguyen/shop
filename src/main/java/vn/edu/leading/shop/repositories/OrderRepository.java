package vn.edu.leading.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import vn.edu.leading.shop.dto.OrderDetailDTO;
import vn.edu.leading.shop.dto.OrderNameDTO;
import vn.edu.leading.shop.models.OrderModel;

import java.util.List;

public interface OrderRepository extends JpaRepository<OrderModel, Long> {
    List<OrderModel> findByCustomerIdContaining(String term);
}
