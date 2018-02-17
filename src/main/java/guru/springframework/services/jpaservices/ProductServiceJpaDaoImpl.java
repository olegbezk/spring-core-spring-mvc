package guru.springframework.services.jpaservices;

import guru.springframework.commands.ProductForm;
import guru.springframework.converters.ProductFormToProduct;
import guru.springframework.domain.Product;
import guru.springframework.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Service
@Profile("jpa_dao")
public class ProductServiceJpaDaoImpl extends AbstractJpaDaoService implements ProductService {

    private final ProductFormToProduct productFormToProduct;

    @Autowired
    public ProductServiceJpaDaoImpl(final ProductFormToProduct productFormToProduct) {
        this.productFormToProduct = productFormToProduct;
    }

    @Override
    public List<Product> listAll() {
        EntityManager em = emf.createEntityManager();

        return em.createQuery("from Product", Product.class).getResultList();
    }

    @Override
    public Product getById(Long id) {
        EntityManager em = emf.createEntityManager();

        return em.find(Product.class, id);
    }

    @Override
    public Product saveOrUpdate(Product domainObject) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        Product savedProduct = em.merge(domainObject);
        em.getTransaction().commit();

        return savedProduct;
    }

    @Override
    public void delete(Long id) {
        EntityManager em = emf.createEntityManager();

        em.getTransaction().begin();
        em.remove(em.find(Product.class, id));
        em.getTransaction().commit();
    }

    @Override
    public Product saveOrUpdateProductForm(ProductForm productForm) {
        return saveOrUpdate(productFormToProduct.convert(productForm));
    }
}
