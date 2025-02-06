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
    @Transactional
    public Optional<Users> insertUser(Users user, Long id) {
        Optional<Course> course = courseRepository.findById(id);
        if (course.isPresent()) {
            Users userResponse = clientRest.getById(user.getId());

            Course courseResponse = course.get();
            CourseUsers courseUsers = new CourseUsers();
            courseUsers.setUserId(userResponse.getId());

            boolean userExists = courseResponse.getCourse_users().stream()
                    .anyMatch(cu -> cu.getUserId().equals(userResponse.getId()));

            if (!userExists) {
                courseResponse.addCourseUsers(courseUsers);
                courseRepository.save(courseResponse);
                return Optional.of(userResponse);
            }
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Users> createUser(Users user, Long id) {
        Optional<Course> course = courseRepository.findById(id);
        if (course.isPresent()) {
            Users userResponse = clientRest.create(user);

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
    @Transactional
    public Optional<Users> unsignedUser(Users user, Long id) {
        Optional<Course> course = courseRepository.findById(id);
        if (course.isPresent()) {
            Users userResponse = clientRest.getById(user.getId());

            Course courseResponse = course.get();

            boolean userExists = courseResponse.getCourse_users().stream()
                    .anyMatch(cu -> cu.getUserId().equals(userResponse.getId()));

            if (userExists) {
                CourseUsers courseUsers = new CourseUsers();
                courseUsers.setUserId(userResponse.getId());
                courseResponse.removeCourseUser(courseUsers);
                courseRepository.save(courseResponse);
                return Optional.of(userResponse);
            }
        }
        return Optional.empty();
    }

    @Override
    @Transactional
    public Optional<Course> byIds(Long id) {
        Optional<Course> courseDb = courseRepository.findById(id);
        if (courseDb.isPresent()) {
            Course course = courseDb.get();

            if (!course.getCourse_users().isEmpty()) {
                List<Long> ids = course.getCourse_users().stream().map(CourseUsers::getUserId).toList();

                List<Users> users = clientRest.usersByCourse(ids);
                course.setUsers(users);
            }

            return Optional.of(course);
        }
        return Optional.empty();
    }

}
