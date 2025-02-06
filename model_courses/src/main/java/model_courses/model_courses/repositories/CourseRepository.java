package model_courses.model_courses.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import model_courses.model_courses.domail.models.Course;

public interface CourseRepository extends JpaRepository<Course, Long> {

    @Modifying
    @Query("delete from CourseUsers cu where cu.userId=:id")
    void deleteCourseUsers(Long id);

}
