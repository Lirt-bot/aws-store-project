package se.jensen.linus.awsstoreproject.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se.jensen.linus.awsstoreproject.model.User;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByUsername(String username);

    Optional<User> findByEmail(String email);


}
