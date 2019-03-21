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
@Table(name = "shop_suppliers")
public class SupplierModel extends BassModel<SupplierModel> {

    @NotEmpty
    @Column(name = "supplier_name", nullable = false)
    private String supplierName;

    @NotEmpty
    @Column(name = "contact_name", nullable = false)
    private String contactName;

    private String address;

    private String city;

    @Column(name = "postal_code")
    private String postalCode;

    private String country;

    private String phone;
}
