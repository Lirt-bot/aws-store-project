package se.jensen.linus.awsstoreproject.service;

import se.jensen.linus.awsstoreproject.DTO.RegisterDTO;
import se.jensen.linus.awsstoreproject.model.User;

public interface UserService {
    User registerUser(RegisterDTO registerDTO);

    User findByUsername(String username);
}
