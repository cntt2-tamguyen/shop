package vn.edu.leading.shop.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
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
//@ToString
@Entity
@Table(name = "shop_products")
public class ProductModel extends BaseModel<ProductModel> {

    @NotEmpty
    @Column(name = "product_name", nullable = false)
    private String productName;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "supplier_id", nullable = false)
    @BatchSize(size = 50)
    private SupplierModel supplierModel;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "category_id", nullable = false)
    @BatchSize(size = 50)
    private CategoryModel categoryModel;

    private String unit;

    private Double price;

    @OneToMany(
            mappedBy = "productModel",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    @Fetch(FetchMode.SELECT)
    @BatchSize(size = 50)
    private Set<OrderDetailModel> orderDetails = new HashSet<>();
}
