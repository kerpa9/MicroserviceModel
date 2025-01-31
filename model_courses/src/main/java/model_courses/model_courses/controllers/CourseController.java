package model_courses.model_courses.controllers;

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
import model_courses.model_courses.domail.models.Course;
import model_courses.model_courses.services.CourseService;

@RestController
@RequestMapping("/api/v1/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping
    public List<Course> getAllUser() {

        return courseService.findAll();

    }

    @GetMapping("/{id}")
    public ResponseEntity<Course> getById(@PathVariable Long id) {
        Optional<Course> user = courseService.byId(id);

        if (user.isPresent()) {
            return ResponseEntity.status(HttpStatus.FOUND).body(user.get());
        }

        return ResponseEntity.notFound().build();

    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody @Valid Course user, BindingResult result) {
        if (result.hasErrors()) {
            return getErrors(result);

        }
        return ResponseEntity.status(HttpStatus.CREATED).body(courseService.saveUser(user));

    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Course user, BindingResult result, @PathVariable Long id) {

        if (result.hasErrors()) {
            return getErrors(result);

        }

        Optional<Course> userId = courseService.byId(id);

        if (userId.isPresent()) {
            Course userDb = userId.get();
            userDb.setName(user.getName());
            return ResponseEntity.status(HttpStatus.ACCEPTED).body(courseService.saveUser(userDb));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Course> deleted(@PathVariable Long id) {
        Optional<Course> userId = courseService.byId(id);
        if (userId.isPresent()) {
            courseService.delete(id);
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
}
