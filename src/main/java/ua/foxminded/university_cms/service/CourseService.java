package ua.foxminded.university_cms.service;

import org.springframework.stereotype.Service;
import ua.foxminded.university_cms.model.Course;

import java.util.List;
import java.util.Optional;

@Service
public interface CourseService {

    Course add(Course course);

    List<Course> getAll();

    Optional<Course> findById(int id);

    Course update(Course course);

    Optional<Course> findByName(String name);

    void deleteById(Integer id);
}
