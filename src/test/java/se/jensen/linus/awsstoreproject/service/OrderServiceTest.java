package se.jensen.linus.awsstoreproject.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import se.jensen.linus.awsstoreproject.Repository.OrderRepository;
import se.jensen.linus.awsstoreproject.model.Order;
import se.jensen.linus.awsstoreproject.model.Product;
import se.jensen.linus.awsstoreproject.model.User;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;


@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {


    @Mock
    private OrderRepository orderRepository;

    @Mock
    private ProductService productService;

    @InjectMocks
    private OrderServiceImpl orderService;

    @Test
    void createOrder_shouldSaveAndReturnOrder() {

        // Arrange
        User fakeUser = new User();
        fakeUser.setUsername("testuser");

        Product fakeProduct = new Product(1, "Test Produkt", 99.0, "Beskrivning", "Kategori", "bild.jpg");

        Order savedOrder = new Order();
        savedOrder.setUser(fakeUser);
        savedOrder.setProductId(1);
        savedOrder.setProductTitle("Test Produkt");
        savedOrder.setProductPrice(99.0);
        savedOrder.setQuantity(2);

        when(productService.getProductById(1)).thenReturn(fakeProduct);
        when(orderRepository.save(any(Order.class))).thenReturn(savedOrder);

        // Act
        Order result = orderService.createOrder(fakeUser, 1, 2);

        // Assert
        assertNotNull(result);
        assertEquals("Test Produkt", result.getProductTitle());
        assertEquals(99.0, result.getProductPrice());
        assertEquals(2, result.getQuantity());
    }

    @Test
    void getOrdersByUser_shouldReturnUsersOrders() {

        // Arrange
        User fakeUser = new User();
        fakeUser.setUsername("testuser");

        Order order1 = new Order();
        order1.setProductTitle("Produkt 1");

        Order order2 = new Order();
        order2.setProductTitle("Produkt 2");

        when(orderRepository.findByUser(fakeUser))
                .thenReturn(List.of(order1, order2));

        // Act
        List<Order> result = orderService.getOrdersByUser(fakeUser);

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Produkt 1", result.getFirst().getProductTitle());
    }
}

