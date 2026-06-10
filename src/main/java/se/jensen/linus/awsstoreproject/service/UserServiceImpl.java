package se.jensen.linus.awsstoreproject.service;


import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import se.jensen.linus.awsstoreproject.DTO.RegisterDTO;
import se.jensen.linus.awsstoreproject.Repository.UserRepository;
import se.jensen.linus.awsstoreproject.model.User;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;


    @Override
    public User registerUser(RegisterDTO registerDTO) {
        if (userRepository.findByUsername(registerDTO.username()).isPresent()) {
            throw new RuntimeException("Användarnamnet är redan taget!");
        }
        if (userRepository.findByEmail(registerDTO.email()).isPresent()) {
            throw new RuntimeException("Emailen är redan registrerad!");
        }

        User user = new User();
        user.setUsername(registerDTO.username());
        user.setEmail(registerDTO.email());
        user.setPassword(passwordEncoder.encode(registerDTO.password()));
        return userRepository.save(user);
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)

                .orElseThrow(() -> new UsernameNotFoundException(

                        "User not found: " + username));

    }
}
