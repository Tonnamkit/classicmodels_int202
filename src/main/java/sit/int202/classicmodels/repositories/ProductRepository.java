package sit.int202.classicmodels.repositories;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import sit.int202.classicmodels.entities.Product;

import java.util.List;

import static sit.int202.classicmodels.repositories.EntityManagerBuilder.*;

public class ProductRepository {
    private static int PAGE_SIZE = 10;
    private EntityManager entityManager;
    public int getDefaultPageSize() {
        return PAGE_SIZE;
    }
    private EntityManager getEntityManager() {
        if (entityManager == null || !entityManager.isOpen()) {
            entityManager = EntityManagerBuilder.getEntityManager();
        }
        return entityManager;
    }
    public List<Product> findAll(int page, int pageSize) {
        int startPosition = (page-1) * pageSize;
        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createNamedQuery("PRODUCT.FIND_ALL");
        query.setFirstResult(startPosition);
        query.setMaxResults(pageSize);
        List<Product> productList = query.getResultList();
        return productList;
    }

    public int countAll() {
        int number = ((Number) entityManager.createNamedQuery("PRODUCT.COUNT").getSingleResult()).intValue();
        return number;
    }

    public void close() {
        if (entityManager != null && entityManager.isOpen()) {
            entityManager.close();
        }
    }

    public Product findProduct(String productCode){
        return getEntityManager().find(Product.class,productCode);
    }
}
