package se.jensen.linus.awsstoreproject.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import se.jensen.linus.awsstoreproject.model.User;
import se.jensen.linus.awsstoreproject.service.OrderService;
import se.jensen.linus.awsstoreproject.service.UserService;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;


    @PostMapping("/orders/create")
    public String createOrder(@RequestParam("productId") Integer productId,
                              @RequestParam("quantity") Integer quantity,
                              @AuthenticationPrincipal UserDetails userDetails) {
        User user = userService.findByUsername(userDetails.getUsername());
        orderService.createOrder(user, productId, quantity);
        return "redirect:/orders";
    }

    @GetMapping("/orders")
    public String getOrders(@AuthenticationPrincipal UserDetails userDetails,
                            Model model) {
        User user = userService.findByUsername(userDetails.getUsername());
        model.addAttribute("orders", orderService.getOrdersByUser(user));
        return "orders";
    }

}
