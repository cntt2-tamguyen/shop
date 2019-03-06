package vn.edu.leading.shop.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "shop_employees")
public class EmployeeModel extends BassModel<EmployeeModel>{

    @NotEmpty
    @Column (name = "last_name",nullable = false)
    private String lastName;

    @NotEmpty
    @Column(name = "first_name",nullable = false)
    private String firstName;


    @Column (name = "birth_date",nullable = false)
    private String birthDate;

    private String photo;

    private String notes;

}

