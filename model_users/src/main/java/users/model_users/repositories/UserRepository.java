package users.model_users.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import users.model_users.domail.models.User;

public interface UserRepository extends JpaRepository<User, Long> {

}
