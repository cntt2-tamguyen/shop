package vn.edu.leading.shop.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import vn.edu.leading.shop.models.ShipperModel;

import java.util.List;

public interface ShipperRepository extends JpaRepository<ShipperModel, Long> {

    List<ShipperModel> findByShipperNameContaining(String term);
}
