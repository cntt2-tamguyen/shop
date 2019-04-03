package vn.edu.leading.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.edu.leading.shop.models.OrderDetailModel;

@Repository
public interface OrderDetailRepository extends JpaRepository<OrderDetailModel, Long> {

    //List<OrderDetailModel> findByOrderIdContaining(String term);
}
