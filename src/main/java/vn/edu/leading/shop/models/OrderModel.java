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
@Table (name = "shop_orders")
public class OrderModel extends BassModel<OrderModel>{

    @NotEmpty
    @Column(name = "customer_id",nullable = false)
    private Long customerId;

    @NotEmpty
    @Column(name="employee_id",nullable = false)
    private Long employeeId;

    private String orderDate;

    @NotEmpty
    @Column(name = "shipper_id",nullable = false)
    private Long shipperId;


}