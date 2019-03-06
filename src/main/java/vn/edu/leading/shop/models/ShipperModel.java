package vn.edu.leading.shop.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@ToString
@Table(name = "shop_shippers")

public class ShipperModel extends BassModel<ShipperModel>{

    @NotEmpty
    @Column(name = "shipper_name",nullable = false)
    private String shipperName;

    private String phone;

}