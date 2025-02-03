package users.model_users.services;

import java.util.List;
import java.util.Optional;

import users.model_users.domail.models.User;

public interface IUserService {

    List<User> findAll();

    Optional<User> byId(Long id);

    User saveUser(User user);

    void delete(Long id);

    Optional<User> findByEmail(String email);

}
