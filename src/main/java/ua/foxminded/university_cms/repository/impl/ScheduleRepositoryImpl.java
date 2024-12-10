package ua.foxminded.university_cms.repository.impl;

import org.springframework.stereotype.Repository;
import ua.foxminded.university_cms.model.Course;
import ua.foxminded.university_cms.model.Schedule;
import ua.foxminded.university_cms.repository.ScheduleRepository;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ScheduleRepositoryImpl implements ScheduleRepository {
    // Imitate db
    private static final List<Schedule> STORAGE = new ArrayList<>();

    // populate storage
    static {
        STORAGE.add(new Schedule(
                1,
                "Math",
                "Alice Smith",
                "AI-11",
                LocalTime.of(9, 0),
                LocalTime.of(10, 0),
                "110",
                LocalDate.of(2024, 10, 15)
        ));
        STORAGE.add(new Schedule(
                2,
                "Physics",
                "John Smith",
                "AI-11",
                LocalTime.of(10, 15),
                LocalTime.of(11, 15),
                "115",
                LocalDate.of(2024, 10, 15)
        ));
        STORAGE.add(new Schedule(
                3,
                "Math",
                "Alice Smith",
                "AI-11",
                LocalTime.of(11, 30),
                LocalTime.of(12, 30),
                "286",
                LocalDate.of(2024, 10, 15)
        ));
        STORAGE.add(new Schedule(
                4,
                "Chemistry",
                "Mark Johnson",
                "AI-11",
                LocalTime.of(12, 45),
                LocalTime.of(13, 45),
                "147",
                LocalDate.of(2024, 10, 15)
        ));
        STORAGE.add(new Schedule(
                5,
                "Biology",
                "Emma Brown",
                "AI-11",
                LocalTime.of(14, 0),
                LocalTime.of(15, 0),
                "145",
                LocalDate.of(2024, 10, 15)
        ));
    }

    @Override
    public List<Schedule> findAll() {
        return STORAGE;
    }
}
