package guru.springframework.services;

import guru.springframework.config.JpaIntegrationConfig;
import guru.springframework.domain.Product;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(JpaIntegrationConfig.class)
@ActiveProfiles("jpa_dao")
public class ProductServiceJpaDaoImplTest {

    @Autowired
    private ProductService productService;

    @Test
    public void testListMethod() {

        List<Product> products = (List<Product>) productService.listAll();

        assertEquals(products.size(), 5);
    }
}
