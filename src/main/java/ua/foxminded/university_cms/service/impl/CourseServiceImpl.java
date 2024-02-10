package ua.foxminded.university_cms.service.impl;

import org.springframework.stereotype.Service;
import ua.foxminded.university_cms.exception.NotFoundException;
import ua.foxminded.university_cms.exception.ValidationException;
import ua.foxminded.university_cms.model.Course;
import ua.foxminded.university_cms.repository.impl.CourseRepository;
import ua.foxminded.university_cms.service.CourseService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {
    // Imitate db
    private final CourseRepository courseRepository;

    public CourseServiceImpl(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public List<Course> getAll() {
        return courseRepository.getAll();
    }

    @Override
    public Course add(Course course) {
        checkUniqueName(course.getName());
        return courseRepository.add(course);
    }

    @Override
    public Optional<Course> findById(int id) {
        return courseRepository.findById(id);
    }

    @Override
    public Course update(Course course) {
        Course oldCourse = courseRepository.findById(course.getId())
                .orElseThrow(() -> new NotFoundException("Course with such id not found: " + course.getId()));
        if (!oldCourse.getName().equals(course.getName())) {
            checkUniqueName(course.getName());
        }
        return courseRepository.update(course);
    }

    @Override
    public Optional<Course> findByName(String name) {
        return courseRepository.findByName(name);
    }

    @Override
    public void deleteById(Integer id) {
        courseRepository.deleteById(id);
    }

    private void checkUniqueName(String name) {
        if (findByName(name).isPresent()) {
            throw new ValidationException("Course with such name already exist: " + name);
        }
    }
}
