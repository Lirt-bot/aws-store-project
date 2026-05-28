package se.jensen.linus.awsstoreproject.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;
import se.jensen.linus.awsstoreproject.model.Product;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ProductServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private ProductServiceImpl productServiceImpl;


    @Test
    void getAllProducts() {
        // Arrange
        Product[] fakeProducts = {
                new Product(1, "Test Produkt", 99.0, "Beskrivning",
                        "Kategori", "bild.jpg"),

                new Product(2, "Test Produkt 2", 199.0,
                        "Beskrivning 2", "Kategori", "bild2.jpg")
        };

        when(restTemplate.getForObject(anyString(), eq(Product[].class)))
                .thenReturn(fakeProducts);

        // Act
        List<Product> result = productServiceImpl.getAllProducts();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Test Produkt", result.getFirst().getTitle());
    }

    @Test
    void getProductById_shouldReturnCorrectProduct() {
        // Arrange
        Product fakeProduct = new Product(1, "Test Produkt", 99.0, "Beskrivning", "Kategori", "bild.jpg");

        when(restTemplate.getForObject(anyString(), eq(Product.class)))
                .thenReturn(fakeProduct);

        // Act
        Product result = productServiceImpl.getProductById(1);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getId());
        assertEquals("Test Produkt", result.getTitle());
        assertEquals(99.0, result.getPrice());
    }

}
