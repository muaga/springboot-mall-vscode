package shop.mtcoding.mallstudy01.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import shop.mtcoding.mallstudy01.model.Product;

@Repository
public class ProductRepository {

    @Autowired
    private EntityManager em;

    // save
    @Transactional
    public void save(Product product) {
        Query query = em.createNativeQuery(
                "insert into product_tb(name, price, qty, seller_id, created_at) values (:name, :price, :qty, :sellerId, CURRENT_TIMESTAMP(0))");
        query.setParameter("name", product.getName());
        query.setParameter("price", product.getPrice());
        query.setParameter("qty", product.getQty());
        query.setParameter("sellerId", product.getSeller().getId());
        query.executeUpdate();
    }

    // update
    @Transactional
    public void update(Product product) {
        Query query = em.createNativeQuery(
                "update product_tb set name = :name, price = :price, qty = :qty, seller_id = :seller_id where id = :id");
        query.setParameter("name", product.getName());
        query.setParameter("price", product.getPrice());
        query.setParameter("qty", product.getQty());
        query.setParameter("sellerId", product.getSeller().getId());
        query.setParameter("id", product.getId());
        query.executeUpdate();
    }

    // delete
    @Transactional
    public void delete(Integer id) {
        Query query = em.createNativeQuery("delete from product_tb where id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    // findByAll
    public List<Product> findByAll() {
        Query query = em.createNativeQuery("select * from product_tb", Product.class);
        List<Product> productList = query.getResultList();
        return productList;
    }

    // findById
    public Product findById(Integer id) {
        Query query = em.createNativeQuery("select * from product_tb where id = :id", Product.class);
        query.setParameter("id", id);
        Product product = (Product) query.getSingleResult();
        return product;
    }

    // findByIdJoinSeller
    public List<Product> findByIdJoinSeller(Integer id) {
        Query query = em.createNativeQuery("select * \n" +
                "from product_tb as p \n" +
                "inner join seller_tb as s \n" +
                "on p.seller_id = s.id \n" +
                "where s.id = :id", Product.class);
        query.setParameter("id", id);
        List<Product> productList = query.getResultList();
        return productList;
    }

}
