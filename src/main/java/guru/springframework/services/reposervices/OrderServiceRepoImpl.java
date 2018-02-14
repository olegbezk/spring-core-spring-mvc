package guru.springframework.services.reposervices;

import guru.springframework.domain.Order;
import guru.springframework.repositories.OrderRepository;
import guru.springframework.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Profile({"springdatajpa", "jpa_dao"})
public class OrderServiceRepoImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Autowired
    public OrderServiceRepoImpl(final OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<?> listAll() {
        List<Order> orders = new ArrayList<>();
        orderRepository.findAll().forEach(orders::add);
        return orders;
    }

    @Override
    public Order getById(final Long id) {
        return orderRepository.findOne(id);
    }

    @Override
    public Order saveOrUpdate(final Order domainObject) {
        return orderRepository.save(domainObject);
    }

    @Override
    public void delete(final Long id) {
        orderRepository.delete(id);
    }
}
