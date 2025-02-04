package model_courses.model_courses.domail.models;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import model_courses.model_courses.domail.Users;

@Entity
@Table(name = "course")
@Getter
@Setter
@AllArgsConstructor
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "course_id")
    private List<CourseUsers> course_users;

    @Transient
    private List<Users> users;

    public Course() {
        course_users = new ArrayList<>();
        users = new ArrayList<>();
    }

    public void addCourseUsers(CourseUsers courseUsers) {
        course_users.add(courseUsers);
    }

    public void removeCourseUser(CourseUsers courseUsers) {
        course_users.remove(courseUsers);
    }

}
