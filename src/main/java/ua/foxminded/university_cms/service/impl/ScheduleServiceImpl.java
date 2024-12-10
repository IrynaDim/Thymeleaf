package ua.foxminded.university_cms.service.impl;

import org.springframework.stereotype.Service;
import ua.foxminded.university_cms.model.Schedule;
import ua.foxminded.university_cms.repository.ScheduleRepository;
import ua.foxminded.university_cms.service.ScheduleService;

import java.util.List;

@Service
public class ScheduleServiceImpl implements ScheduleService {
    private final ScheduleRepository scheduleRepository;

    public ScheduleServiceImpl(ScheduleRepository scheduleRepository) {
        this.scheduleRepository = scheduleRepository;
    }

    @Override
    public List<Schedule> findAll() {
        return scheduleRepository.findAll();
    }
}
