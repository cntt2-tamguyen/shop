package vn.edu.leading.shop.models;

import lombok.*;
import org.hibernate.annotations.BatchSize;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
//@ToString
@Entity
@Table(name = "shop_order_details")
public class OrderDetailModel extends BassModel<OrderDetailModel> {

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "order_id", nullable = false)
    @BatchSize(size = 50)
    private OrderModel orderModel;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "product_id", nullable = false)
    @BatchSize(size = 50)
    private ProductModel productModel;

    private Long quantity;
}
