package se.jensen.linus.awsstoreproject.service;

import se.jensen.linus.awsstoreproject.model.Order;
import se.jensen.linus.awsstoreproject.model.User;

import java.util.List;

public interface OrderService {
    Order createOrder(User user, Integer productId, Integer quantity);

    List<Order> getOrdersByUser(User user);


}
