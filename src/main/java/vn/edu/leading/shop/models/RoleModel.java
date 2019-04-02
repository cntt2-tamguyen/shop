package vn.edu.leading.shop.models;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
//@ToString
@Entity
@Table(name = "shop_role")
public class RoleModel extends BaseModel<RoleModel>{

    private String name;

    @ManyToMany(mappedBy = "roleModels")
    private List<UserModel> userModels = new ArrayList<>();
}
