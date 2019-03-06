package vn.edu.leading.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.leading.shop.models.SupplierModel;

import java.util.List;

public interface SupplierRepository extends JpaRepository<SupplierModel, Long> {
    List<SupplierModel> findBySupplierNameContaining(String term);
}
