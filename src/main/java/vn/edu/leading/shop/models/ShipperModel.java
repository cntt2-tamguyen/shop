package vn.edu.leading.shop.models;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "shop_shippers")
public class ShipperModel extends BassModel<ShipperModel> {
    @NotEmpty
    @Column(name = "shipper_name", nullable = false)
    private String shipperName;

    @Column(name = "phone", nullable = false)
    private String phone;
}
