package vn.edu.leading.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.leading.shop.models.CustomerModel;

import java.util.List;

public interface CustomerRepository extends JpaRepository<CustomerModel, Long> {

    List<CustomerModel> findByCustomerNameContaining(String term);
}
