package vn.edu.leading.shop.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
//@ToString
@Entity
@Table(name = "shop_roles")
public class RoleModel extends BaseModel<RoleModel> {

    @NotNull
    @Column(nullable = false, unique = true)
    private String name;
}
