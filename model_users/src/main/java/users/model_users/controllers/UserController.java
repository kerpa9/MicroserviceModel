package users.model_users.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.validation.Valid;
import users.model_users.domail.models.User;
import users.model_users.services.UserService;

@RestController
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUser() {

        return userService.findAll();

    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getById(@PathVariable Long id) {
        Optional<User> user = userService.byId(id);

        if (user.isPresent()) {
            return ResponseEntity.status(HttpStatus.FOUND).body(user.get());
        }

        return ResponseEntity.notFound().build();

    }

    @PostMapping
    public ResponseEntity<User> create(@RequestBody @Valid User user) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(user));

    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@RequestBody User user, @PathVariable Long id) {

        Optional<User> userId = userService.byId(id);

        if (userId.isPresent()) {
            User userDb = userId.get();
            userDb.setName(user.getName());
            userDb.setPassword(user.getPassword());
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.saveUser(userDb));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleted(@PathVariable Long id) {
        Optional<User> userId = userService.byId(id);
        if (userId.isPresent()) {
            userService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
