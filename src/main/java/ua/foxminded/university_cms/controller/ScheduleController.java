package ua.foxminded.university_cms.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import ua.foxminded.university_cms.service.ScheduleService;

@Controller
@RequestMapping("/schedule")
public class ScheduleController {
    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    /*
     This controller is created to demonstrate working with the table.
     In a real-world scenario, it should return not the ALL schedules but the schedules for a specific student/teacher
     for a specific day.
     Additionally, if the Admin or Teacher has special permissions (e.g., to edit or delete the schedule),
     the role should be taken into account when rendering elements on the page (e.g., adding an edit button, etc.).
     How to implement this functionality is demonstrated in the CourseController.
     */
    @GetMapping
    public ModelAndView getAll() {
        ModelAndView mav = new ModelAndView("schedule/schedule");

        mav.addObject("schedules", scheduleService.findAll());

        return mav;
    }
}
