package users.model_users.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import users.model_users.domail.models.User;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    // @Query("Select u from User u where u.email= :email")
    // Optional<User> findByEmailExist(String email);

    // boolean existsByEmail(String email);

}
