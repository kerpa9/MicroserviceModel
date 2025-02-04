package users.model_users.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import users.model_users.domail.models.User;
import users.model_users.repositories.UserRepository;


@Service
public class UserService implements IUserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    @Transactional
    public User saveUser(User user) {
        return userRepository.save(user);

    }

    @Override
    @Transactional(readOnly = true)
    public List<User> findAll() {

        return userRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<User> byId(Long id) {
        return userRepository.findById(id);

    }

    @Override
    @Transactional
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    


}
