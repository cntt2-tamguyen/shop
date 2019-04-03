package vn.edu.leading.shop.models;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
//@ToString
@Entity
@Table(name = "shop_roles")
public class RoleModel extends BaseModel<RoleModel>{

    @NotNull
    @Column(nullable = false, unique = true)
    private String name;
}
