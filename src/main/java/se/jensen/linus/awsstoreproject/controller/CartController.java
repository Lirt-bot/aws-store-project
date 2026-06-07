package se.jensen.linus.awsstoreproject.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import se.jensen.linus.awsstoreproject.service.CartService;
import se.jensen.linus.awsstoreproject.service.OrderService;
import se.jensen.linus.awsstoreproject.service.ProductService;
import se.jensen.linus.awsstoreproject.service.UserService;

@Controller
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {

    private final CartService cartService;
    private final ProductService productService;
    private final OrderService orderService;
    private final UserService userService;

    @GetMapping
    public String viewCart(Model model) {
        model.addAttribute("items", cartService.getItems());
        model.addAttribute("total", cartService.getTotal());
        return "cart";
    }

    @PostMapping("/add/{productId}")
    public String addToCart(@PathVariable Integer productId) {
        cartService.addItem(productService.getProductById(productId));
        return "redirect:/products";
    }

    @PostMapping("/remove/{productId}")
    public String removeFromCart(@PathVariable Integer productId) {
        cartService.removeItem(productId);
        return "redirect:/cart";
    }

    @PostMapping("/checkout")
    public String checkout(@AuthenticationPrincipal UserDetails userDetails) {

        var user = userService.findByUsername(userDetails.getUsername());


        cartService.getItems().forEach(product ->

                orderService.createOrder(user, product.getId(), 1)
        );

        cartService.clear();
        return "redirect:/orders";
    }

}
