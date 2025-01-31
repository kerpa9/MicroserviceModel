package model_courses.model_courses.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import model_courses.model_courses.domail.models.Course;
import model_courses.model_courses.repositories.CourseRepository;



@Service
public class CourseService implements ICourseService {

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

    


}
