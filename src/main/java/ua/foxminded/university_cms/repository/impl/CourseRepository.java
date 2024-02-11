package ua.foxminded.university_cms.repository.impl;

import ua.foxminded.university_cms.model.Course;

import java.util.List;
import java.util.Optional;

public interface CourseRepository {
    Course add(Course course);

    List<Course> getAll();

    Optional<Course> findById(int id);

    Course update(Course course);

    Optional<Course> findByName(String name);

    void deleteById(Integer id);
}
