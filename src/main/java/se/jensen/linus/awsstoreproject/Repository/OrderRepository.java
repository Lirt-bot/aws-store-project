package se.jensen.linus.awsstoreproject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.jensen.linus.awsstoreproject.model.Order;
import se.jensen.linus.awsstoreproject.model.User;

import java.util.List;

public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);
}
