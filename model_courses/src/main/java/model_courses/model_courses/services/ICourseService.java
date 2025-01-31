package model_courses.model_courses.services;
import java.util.List;
import java.util.Optional;

import model_courses.model_courses.domail.models.Course;
public interface ICourseService {

    List<Course> findAll();

    Optional<Course> byId(Long id);

    Course saveUser(Course user);

    void delete(Long id);

}
