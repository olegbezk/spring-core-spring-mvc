package guru.springframework.services;

import guru.springframework.config.JpaIntegrationConfig;
import guru.springframework.domain.Cart;
import guru.springframework.domain.CartDetail;
import guru.springframework.domain.Customer;
import guru.springframework.domain.Product;
import guru.springframework.domain.User;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static org.hibernate.validator.internal.util.Contracts.assertNotNull;
import static org.junit.Assert.assertEquals;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(JpaIntegrationConfig.class)
@ActiveProfiles("jpa_dao")
public class UserServiceJpaDaoImplTest {

    @Autowired
    private UserService userService;

    @Autowired
    private ProductService productService;

    @Test
    public void testSaveOfUser() {
        User user = new User();

        user.setUsername("someusername");
        user.setPassword("myPassword");

        User savedUser = userService.saveOrUpdate(user);

        assertNotNull(savedUser.getId());
        assertNotNull(savedUser.getEncryptedPassword());

        System.out.println("Encrypted Password");
        System.out.println(savedUser.getEncryptedPassword());

    }

    @Test
    public void testSaveOfUserWithCustomer() {

        User user = new User();

        user.setUsername("someusername");
        user.setPassword("myPassword");

        Customer customer = new Customer();
        customer.setFirstName("Chevy");
        customer.setLastName("Chase");

        user.setCustomer(customer);

        User savedUser = userService.saveOrUpdate(user);

        assertNotNull(savedUser.getId());
        assertNotNull(savedUser.getVersion());
        assertNotNull(savedUser.getCustomer());
        assertNotNull(savedUser.getCustomer().getId());

    }

    @Test
    public void testAddCartToUser() {
        User user = new User();

        user.setUsername("someusername");
        user.setPassword("myPassword");

        user.setCart(new Cart());

        User savedUser = userService.saveOrUpdate(user);

        assertNotNull(savedUser.getId());
        assertNotNull(savedUser.getVersion());
        assertNotNull(savedUser.getCart());
        assertNotNull(savedUser.getCart().getId());
    }

    @Test
    public void testAddCartToUserWithCartDetails() {
        User user = new User();

        user.setUsername("someusername");
        user.setPassword("myPassword");

        user.setCart(new Cart());

        List<Product> storedProducts = (List<Product>) productService.listAll();

        CartDetail cartItemOne = new CartDetail();
        cartItemOne.setProduct(storedProducts.get(0));
        user.getCart().addCartDetail(cartItemOne);

        CartDetail cartItemTwo = new CartDetail();
        cartItemTwo.setProduct(storedProducts.get(1));
        user.getCart().addCartDetail(cartItemTwo);

        User savedUser = userService.saveOrUpdate(user);

        assertNotNull(savedUser.getId());
        assertNotNull(savedUser.getVersion());
        assertNotNull(savedUser.getCart());
        assertNotNull(savedUser.getCart().getId());
        assertEquals(savedUser.getCart().getCartDetails().size(), 2);
    }

    @Test
    public void testAddAndRemoveCartToUserWithCartDetails() {
        User user = new User();

        user.setUsername("someusername");
        user.setPassword("myPassword");

        user.setCart(new Cart());

        List<Product> storedProducts = (List<Product>) productService.listAll();

        CartDetail cartItemOne = new CartDetail();
        cartItemOne.setProduct(storedProducts.get(0));
        user.getCart().addCartDetail(cartItemOne);

        CartDetail cartItemTwo = new CartDetail();
        cartItemTwo.setProduct(storedProducts.get(1));
        user.getCart().addCartDetail(cartItemTwo);

        User savedUser = userService.saveOrUpdate(user);

        assertEquals(savedUser.getCart().getCartDetails().size(), 2);

        savedUser.getCart().removeCartDetail(savedUser.getCart().getCartDetails().get(0));

        userService.saveOrUpdate(savedUser);

        assertEquals(savedUser.getCart().getCartDetails().size(), 1);
    }
}
