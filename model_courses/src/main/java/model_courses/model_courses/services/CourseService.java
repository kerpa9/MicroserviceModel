package model_courses.model_courses.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import model_courses.model_courses.clients.IUserClientRest;
import model_courses.model_courses.domail.Users;
import model_courses.model_courses.domail.models.Course;
import model_courses.model_courses.domail.models.CourseUsers;
import model_courses.model_courses.repositories.CourseRepository;

@Service
public class CourseService implements ICourseService {

    @Autowired
    private IUserClientRest clientRest;

    @Autowired
    private CourseRepository courseRepository;

    @Override
    @Transactional
    public Course saveUser(Course course) {
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

    @Override
    public Optional<Users> insertUser(Users user, Long id) {
        Optional<Course> course = courseRepository.findById(id);
        if (course.isPresent()) {
            Users userResponse = clientRest.detail(user.getId());

            Course courseResponse = course.get();
            CourseUsers courseUsers = new CourseUsers();
            courseUsers.setUserId(userResponse.getId());
            courseResponse.addCourseUsers(courseUsers);
            courseRepository.save(courseResponse);
            return Optional.of(userResponse);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Users> createUser(Users user, Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'createUser'");
    }

    @Override
    public Optional<Users> unsignedUser(Users user, Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'unsignedUser'");
    }

}
