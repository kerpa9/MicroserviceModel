package users.model_users.services;

import java.util.List;
import java.util.Optional;

import users.model_users.domail.models.Course;


public interface ICourseService {

    List<Course> findAll();

    Optional<Course> byId(Long id);

    Course saveUser(Course user);

    void delete(Long id);

}
