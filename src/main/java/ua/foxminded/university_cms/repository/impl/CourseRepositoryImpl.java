package ua.foxminded.university_cms.repository.impl;

import org.springframework.stereotype.Repository;
import ua.foxminded.university_cms.model.Course;
import ua.foxminded.university_cms.repository.CourseRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class CourseRepositoryImpl implements CourseRepository {
    // Imitate db
    private static final List<Course> STORAGE = new ArrayList<>();

    // populate storage
    static {
        STORAGE.add(new Course(1, "Math", "Math course"));
        STORAGE.add(new Course(2, "Biology", "Biology course"));
        STORAGE.add(new Course(3, "Genetic", "Genetic course"));
    }

    @Override
    public Course add(Course course) {
        course.setId(STORAGE.isEmpty() ?
                1 :
                STORAGE.get(STORAGE.size() - 1).getId() + 1);
        STORAGE.add(course);
        return course;
    }

    @Override
    public List<Course> getAll() {
        return STORAGE;
    }

    @Override
    public Optional<Course> findById(int id) {
        return STORAGE.stream()
                .filter(course -> course.getId().equals(id))
                .findFirst();
    }

    @Override
    public Course update(Course course) {
        STORAGE.forEach(с -> {
            if (с.getId().equals(course.getId())) {
                с.setName(course.getName());
                с.setDescription(course.getDescription());
            }
        });
        return course;
    }

    @Override
    public Optional<Course> findByName(String name) {
        return STORAGE.stream()
                .filter(course -> course.getName().equals(name))
                .findFirst();
    }

    @Override
    public void deleteById(Integer id) {
        STORAGE.removeIf(c -> Objects.equals(c.getId(), id));
    }
}
