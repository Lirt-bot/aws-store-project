package se.jensen.linus.awsstoreproject.service;

import lombok.Getter;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.SessionScope;
import se.jensen.linus.awsstoreproject.model.Product;

import java.util.ArrayList;
import java.util.List;

@Service
@SessionScope
@Getter

public class CartService {
    private final List<Product> items = new ArrayList<>();


    public void addItem(Product product) {
        items.add(product);
    }

    public void removeItem(Integer productId) {
        items.removeIf(p -> p.getId().equals(productId));
    }

    public void clear() {
        items.clear();
    }

    public Double getTotal() {
        return items.stream()
                .mapToDouble(Product::getPrice)
                .sum();
    }

}
