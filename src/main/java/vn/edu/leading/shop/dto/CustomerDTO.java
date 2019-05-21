package vn.edu.leading.shop.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

public class CustomerDTO {

    private Long id;

    private String customerName;

    private String contactName;

    private String address;

    private String city;

    private String postalCode;

    private String country;
}
