package se.jensen.linus.awsstoreproject.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;
import se.jensen.linus.awsstoreproject.DTO.RegisterDTO;
import se.jensen.linus.awsstoreproject.Repository.UserRepository;
import se.jensen.linus.awsstoreproject.model.User;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private UserServiceImpl userService;

    @Test
    void registerUser_shouldSaveAndReturnUser() {
        // Arrange
        RegisterDTO registerDTO = new RegisterDTO("testuser", "password123", "test@test.com");

        User savedUser = new User();
        savedUser.setUsername("testuser");
        savedUser.setEmail("test@test.com");
        savedUser.setPassword("hashedpassword");

        when(passwordEncoder.encode("password123")).thenReturn("hashedpassword");
        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        // Act
        User result = userService.registerUser(registerDTO);

        // Assert
        assertNotNull(result);
        assertEquals("testuser", result.getUsername());
        assertEquals("test@test.com", result.getEmail());
        assertEquals("hashedpassword", result.getPassword());
    }

    @Test
    void findByUsername_shouldReturnUser() {
        // Arrange
        User fakeUser = new User();
        fakeUser.setUsername("testuser");
        fakeUser.setEmail("test@test.com");

        when(userRepository.findByUsername("testuser"))
                .thenReturn(Optional.of(fakeUser));

        // Act
        User result = userService.findByUsername("testuser");

        // Assert
        assertNotNull(result);
        assertEquals("testuser", result.getUsername());
    }

    @Test
    void findByUsername_shouldThrowException_whenUserNotFound() {
        // Arrange
        when(userRepository.findByUsername("okänd"))
                .thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () ->
                userService.findByUsername("okänd")
        );
    }
}