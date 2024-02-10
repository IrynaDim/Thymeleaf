package ua.foxminded.university_cms.repository;

import org.springframework.stereotype.Repository;
import ua.foxminded.university_cms.model.Course;
import ua.foxminded.university_cms.repository.impl.CourseRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository
public class CourseRepositoryImpl implements CourseRepository {
    // Imitate db
    private static final List<Course> storage = new ArrayList<>();

    // populate storage
    static {
        storage.add(new Course(1, "Math", "Math course"));
        storage.add(new Course(2, "Biology", "Biology course"));
        storage.add(new Course(3, "Genetic", "Genetic course"));
    }

    @Override
    public Course add(Course course) {
        course.setId(storage.isEmpty() ?
                1 :
                storage.get(storage.size() - 1).getId() + 1);
        storage.add(course);
        return course;
    }

    @Override
    public List<Course> getAll() {
        return storage;
    }

    @Override
    public Optional<Course> findById(int id) {
        return storage.stream()
                .filter(course -> course.getId().equals(id))
                .findFirst();
    }

    @Override
    public Course update(Course course) {
        storage.forEach(с -> {
            if (с.getId().equals(course.getId())) {
                с.setName(course.getName());
                с.setDescription(course.getDescription());
            }
        });
        return course;
    }

    @Override
    public Optional<Course> findByName(String name) {
        return storage.stream()
                .filter(course -> course.getName() != null && course.getName().equals(name))
                .findFirst();
    }

    @Override
    public void deleteById(Integer id) {
        storage.removeIf(c -> Objects.equals(c.getId(), id));
    }
}
