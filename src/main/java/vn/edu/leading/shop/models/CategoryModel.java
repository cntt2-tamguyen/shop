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
@Table(name = "shop_categories")
public class CategoryModel extends BassModel<CategoryModel>{

    @NotEmpty
    @Column(name = "category_name")
    String categoryName;

    String description;
}
