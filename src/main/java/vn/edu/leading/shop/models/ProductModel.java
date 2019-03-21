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
@Table(name = "shop_products")
public class ProductModel extends BassModel<ProductModel> {

    @NotEmpty
    @Column(name = "product_name", nullable = false)
    private String productName;

    @NotEmpty
    @Column(name = "supplier_id", nullable = false)
    private Long supplierId;

    @NotEmpty
    @Column(name = "category_id", nullable = false)
    private Long categoryId;

    private String unit;

    private Double price;
}
