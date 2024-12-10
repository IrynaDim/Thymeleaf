package ua.foxminded.university_cms.repository;

import ua.foxminded.university_cms.model.Schedule;

import java.util.List;

public interface ScheduleRepository {
    List<Schedule> findAll();
}
