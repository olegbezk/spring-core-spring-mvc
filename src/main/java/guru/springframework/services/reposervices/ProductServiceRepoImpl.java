package guru.springframework.services.reposervices;

import guru.springframework.commands.ProductForm;
import guru.springframework.converters.ProductFormToProduct;
import guru.springframework.domain.Product;
import guru.springframework.repositories.ProductRepository;
import guru.springframework.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Profile("springdatajpa")
public class ProductServiceRepoImpl implements ProductService {

    private final ProductRepository productRepository;

    private final ProductFormToProduct productFormToProduct;

    @Autowired
    public ProductServiceRepoImpl(final ProductRepository productRepository,
                                  final ProductFormToProduct productFormToProduct) {
        this.productRepository = productRepository;
        this.productFormToProduct = productFormToProduct;
    }

    @Override
    public List<?> listAll() {
        List<Product> products = new ArrayList<>();
        productRepository.findAll().forEach(products::add);
        return products;
    }

    @Override
    public Product getById(Long id) {
        return productRepository.findOne(id);
    }

    @Override
    public Product saveOrUpdate(Product domainObject) {
        return productRepository.save(domainObject);
    }

    @Override
    public Product saveOrUpdateProductForm(ProductForm productForm) {
        return saveOrUpdate(productFormToProduct.convert(productForm));
    }

    @Override
    public void delete(Long id) {
        productRepository.delete(id);
    }
}
