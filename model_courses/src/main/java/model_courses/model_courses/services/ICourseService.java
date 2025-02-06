package model_courses.model_courses.services;

import java.util.List;
import java.util.Optional;

import model_courses.model_courses.domail.Users;
import model_courses.model_courses.domail.models.Course;

public interface ICourseService {

    List<Course> findAll();

    Optional<Course> byId(Long id);

    Course saveUser(Course user);

    void delete(Long id);

    void deleteById(Long id);

    Optional<Users> insertUser(Users user, Long id);

    Optional<Users> createUser(Users user, Long id);

    Optional<Users> unsignedUser(Users user, Long id);

    Optional<Course> byIds(Long id);

}
