package se.jensen.linus.awsstoreproject.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import se.jensen.linus.awsstoreproject.model.Product;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final RestTemplate restTemplate;
    private static final String BASE_URL = "https://fakestoreapi.com";

    @Override
    public List<Product> getAllProducts() {
        Product[] products = restTemplate.getForObject(BASE_URL + "/products", Product[].class);
        return List.of(products);
    }

    @Override
    public Product getProductById(Integer id) {
        return restTemplate.getForObject(BASE_URL + "/products/" + id, Product.class);
    }

}
