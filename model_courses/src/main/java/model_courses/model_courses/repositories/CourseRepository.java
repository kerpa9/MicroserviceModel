package model_courses.model_courses.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import model_courses.model_courses.domail.models.Course;


public interface CourseRepository extends JpaRepository<Course, Long> {

}
