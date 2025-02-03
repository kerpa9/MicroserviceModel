package users.model_users.controllers;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
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
    public ResponseEntity<?> create(@RequestBody @Valid User user, BindingResult result) {

        if (!user.getEmail().isEmpty() && userService.findByEmail(user.getEmail()).isPresent()) {
            return ResponseEntity.badRequest().body(Collections.singletonMap("message: ", "Already exists email"));

        }

        if (result.hasErrors()) {

            return getErrors(result);

        }
        return ResponseEntity.status(HttpStatus.CREATED).body(userService.saveUser(user));

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody @Valid User user, BindingResult result, @PathVariable @Valid Long id) {

        if (result.hasErrors()) {
            return getErrors(result);
        }

        Optional<User> userId = userService.byId(id);

        if (userId.isPresent()) {

            User userDb = userId.get();

            if (!user.getEmail().equalsIgnoreCase(userDb.getEmail())
                    && userService.findByEmail(user.getEmail()).isPresent()) {
                return ResponseEntity.badRequest().body(Collections.singletonMap("message: ", "Already exist email"));
            }
            userDb.setName(user.getName());
            userDb.setPassword(user.getPassword());
            userDb.setEmail(user.getEmail());
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(userService.saveUser(userDb));
        }
        return ResponseEntity.notFound().build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> deleted(@PathVariable @Valid Long id) {
        Optional<User> userId = userService.byId(id);
        if (userId.isPresent()) {
            userService.delete(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    private ResponseEntity<?> getErrors(BindingResult result) {
        Map<String, String> errors = new HashMap<>();

        result.getFieldErrors().forEach(e -> {
            errors.put(e.getField(), "This field: " + e.getField() + " is not empty");
        });

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errors);
    }

    // private ResponseEntity<Map<String, String>> validate(BindingResult result) {
    // Map<String, String> errors = new HashMap<>();
    // result.getFieldErrors().forEach(err -> {
    // errors.put(err.getField(), "This field: " + err.getField() + "is not empty: "
    // + err.getDefaultMessage());
    // });

    // return ResponseEntity.badRequest().body(errors);
    // }
}
