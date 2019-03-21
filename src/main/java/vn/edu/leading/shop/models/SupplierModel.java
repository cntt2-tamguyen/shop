package vn.edu.leading.shop.models;

import lombok.*;
import org.hibernate.annotations.BatchSize;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

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

    @OneToMany(
            mappedBy = "supplierModel",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    @Fetch(FetchMode.SELECT)
    @BatchSize(size = 50)
    private Set<ProductModel> products =new HashSet<>();
}
