package shop.mtcoding.mallstudy01.model;

import javax.persistence.Entity;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Table(name = "seller_tb")
@Entity
public class Seller {
    private Integer id;
    private String name;
    private String email;

    @Builder
    public Seller(Integer id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

}
