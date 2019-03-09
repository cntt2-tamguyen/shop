package vn.edu.leading.shop.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class OrderDetailDTO {


    private String productName;
    private Double price;
    private Long quantity;
    private Double sum;
}
