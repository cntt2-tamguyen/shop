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
@Table(name = "shop_suppliers")

public class SupplierModel extends BassModel<SupplierModel>{
    @NotEmpty
    @Column(name = "supplier_name",nullable = false)
    private String supplierName;

    @NotEmpty
    @Column(name = "contact_name",nullable = false)
    private String contactName;

    private String address;

    private String city;

    @Column(name ="postal_code" )
    private String postalCode;

    private String country;

    private String phone;
}