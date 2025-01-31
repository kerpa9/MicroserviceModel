package users.model_courses.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import users.model_users.domail.models.Course;
import users.model_users.repositories.UserRepository;


@Service
public class CourseService implements ICourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Override
    @Transactional
    public User saveUser(Course course) {
        return courseRepository.save(course);

    }

    @Override
    @Transactional(readOnly = true)
    public List<Course> findAll() {

        return courseRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Course> byId(Long id) {
        return courseRepository.findById(id);

    }

    @Override
    @Transactional
    public void delete(Long id) {
        courseRepository.deleteById(id);
    }

    


}
