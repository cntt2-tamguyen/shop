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
@Table(name = "shop_order_details")
public class OrderDetailModel extends BassModel<OrderDetailModel> {

    @NotEmpty
    @Column(name = "order_id", nullable = false)
    private Long orderId;

    @NotEmpty
    @Column(name = "product_id", nullable = false)
    private Long productId;

    private Long quantity;
}
