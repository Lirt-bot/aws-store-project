package se.jensen.linus.awsstoreproject.exception;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UsernameNotFoundException.class)
    public String handleUserNotFound(RedirectAttributes redirectAttributes) {
        redirectAttributes.addFlashAttribute("error",
                "Användaren hittades inte, vänligen logga in igen.");

        return "redirect:/login";
    }


}
