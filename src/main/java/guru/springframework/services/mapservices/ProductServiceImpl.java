package guru.springframework.services.mapservices;

import guru.springframework.commands.ProductForm;
import guru.springframework.converters.ProductFormToProduct;
import guru.springframework.domain.DomainObject;
import guru.springframework.domain.Product;
import guru.springframework.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Profile("map")
public class ProductServiceImpl extends AbstractMapService implements ProductService {

    private final ProductFormToProduct productFormToProduct;

    @Autowired
    public ProductServiceImpl(final ProductFormToProduct productFormToProduct) {
        this.productFormToProduct = productFormToProduct;
    }

    @Override
    public List<DomainObject> listAll() {
        return super.listAll();
    }

    @Override
    public Product getById(Long id) {
        return (Product) super.getById(id);
    }

    @Override
    public Product saveOrUpdate(Product domainObject) {
        return (Product) super.saveOrUpdate(domainObject);
    }

    @Override
    public void delete(Long id) {
        super.delete(id);
    }

    @Override
    public Product saveOrUpdateProductForm(final ProductForm productForm) {
        return saveOrUpdate(productFormToProduct.convert(productForm));
    }
}
