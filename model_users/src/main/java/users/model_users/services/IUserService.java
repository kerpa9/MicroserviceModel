package users.model_users.services;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;

public interface IUserService {

    List<User> findAll();

    Optional<User> byId(Long id);

    User saveUser(User user);

    void delete(Long id);

}
