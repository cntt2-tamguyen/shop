package vn.edu.leading.shop.models;

import lombok.*;
import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.io.Serializable;

@MappedSuperclass
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class BassModel<T extends BassModel<T>>  implements Persistable, Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    protected Long id;


    @Override
    @Transient
    public boolean isNew() {
        return null == this.getId();
    }
}
