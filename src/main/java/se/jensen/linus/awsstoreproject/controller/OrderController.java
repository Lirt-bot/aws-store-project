package se.jensen.linus.awsstoreproject.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import se.jensen.linus.awsstoreproject.model.User;
import se.jensen.linus.awsstoreproject.service.OrderService;
import se.jensen.linus.awsstoreproject.service.UserService;

@Controller
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final UserService userService;


    @GetMapping("/orders")
    public String getOrders(@AuthenticationPrincipal UserDetails userDetails,
                            Model model) {
        User user = userService.findByUsername(userDetails.getUsername());
        model.addAttribute("orders", orderService.getOrdersByUser(user));
        return "orders";
    }

}
