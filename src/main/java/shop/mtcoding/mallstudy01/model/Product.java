package shop.mtcoding.mallstudy01.model;

import java.security.Timestamp;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@Table(name = "product_tb")
@Entity
public class Product {
    private Integer id;
    private String name;
    private Integer price;
    private Integer qty;
    private Timestamp createdAt;

    @ManyToOne
    private Seller Seller;

    @Builder
    public Product(Integer id, String name, Integer price, Integer qty, Timestamp createdAt, Seller seller) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.qty = qty;
        this.createdAt = createdAt;
        this.Seller = seller;
    }

}
