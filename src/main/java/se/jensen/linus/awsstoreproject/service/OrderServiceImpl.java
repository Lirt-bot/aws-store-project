package se.jensen.linus.awsstoreproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import se.jensen.linus.awsstoreproject.Repository.OrderRepository;
import se.jensen.linus.awsstoreproject.model.Order;
import se.jensen.linus.awsstoreproject.model.Product;
import se.jensen.linus.awsstoreproject.model.User;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final ProductService productService;


    @Override
    public Order createOrder(User user, Integer productId, Integer quantity) {
        Product product = productService.getProductById(productId);

        Order order = new Order();
        order.setUser(user);
        order.setProductId(productId);
        order.setProductTitle(product.getTitle());
        order.setProductPrice(product.getPrice());
        order.setQuantity(quantity);

        return orderRepository.save(order);

    }

    @Override
    public List<Order> getOrdersByUser(User user) {
        return orderRepository.findByUser(user);
    }
}
