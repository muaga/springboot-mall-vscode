package shop.mtcoding.mallstudy01.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import shop.mtcoding.mallstudy01.model.Seller;

@Repository
public class SellerRepository {

    @Autowired
    private EntityManager em;

    // save
    @Transactional
    public void save(Seller seller) {
        Query query = em.createNativeQuery("insert into seller_tb(name, email) values(:name, :email)");
        query.setParameter("name", seller.getName());
        query.setParameter("email", seller.getEmail());
        query.executeUpdate();
    }

    // update
    @Transactional
    public void update(Seller seller) {
        Query query = em.createNativeQuery("update seller_tb set name = :name, email = :email where id = :id");
        query.setParameter("name", seller.getName());
        query.setParameter("email", seller.getEmail());
        query.setParameter("id", seller.getId());
        query.executeUpdate();
    }

    // delete
    @Transactional
    public void delete(Integer id) {
        Query query = em.createNativeQuery("delete from seller_tb where id = :id");
        query.setParameter("id", id);
        query.executeUpdate();
    }

    // findByAll
    public List<Seller> findByAll() {
        Query query = em.createNativeQuery("select * from seller_tb", Seller.class);
        List<Seller> sellerList = query.getResultList();
        return sellerList;
    }

    // findById
    public Seller findById(Integer id) {
        Query query = em.createNativeQuery("select * from seller_tb where id = :id", Seller.class);
        query.setParameter("id", id);
        Seller seller = (Seller) query.getSingleResult();
        return seller;
    }

    // fingByEmail
    public Seller findByEmail(String email) {
        try {
            Query query = em.createNativeQuery("select * from seller_tb where email = :email", Seller.class);
            query.setParameter("email", email);
            Seller seller = (Seller) query.getSingleResult();
            return seller;
        } catch (Exception e) {
            return null;
        }

    }

}
