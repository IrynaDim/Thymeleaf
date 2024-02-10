package ua.foxminded.thymeleaf.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ua.foxminded.thymeleaf.exception.ValidationException;
import ua.foxminded.thymeleaf.model.Course;
import ua.foxminded.thymeleaf.service.CourseService;

@RestController
@RequestMapping("/course")
public class CourseController {

    private final CourseService courseService;

    @Autowired
    public CourseController(CourseService courseService) {
        this.courseService = courseService;
    }

    @GetMapping
    public ModelAndView getAll() {
        ModelAndView mav = new ModelAndView("course/course");
        mav.addObject("courses", courseService.getAll());

        return mav;
    }

    @GetMapping("/update/{id}")
    public ModelAndView getUpdateForm(@PathVariable int id) {
        ModelAndView mav = new ModelAndView();
        return courseService.findById(id)
                .map(course -> {
                    mav.addObject("course", course);
                    mav.setViewName("course/edit");
                    return mav;
                })
                .orElseGet(() -> {
                    mav.setViewName("redirect:/course");
                    return mav;
                });
    }

    /*
    id passed as PathVariable just to show how to use PathVariable with PUT request in ajax.
     */
    @PutMapping(value = "/update/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> update(@RequestBody Course course, @PathVariable int id) {
        try {
            Course updatedCourse = courseService.update(course);
            return ResponseEntity.ok(updatedCourse);
        } catch (ValidationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    public void deleteById(@PathVariable int id) {
        courseService.deleteById(id);
    }

    @GetMapping("/create")
    public ModelAndView getCreateForm() {
        return new ModelAndView("course/new");
    }

    @PostMapping("/create")
    public ResponseEntity<Object> add(@RequestBody Course course) {
        try {
            Course updatedCourse = courseService.add(course);
            return ResponseEntity.ok(updatedCourse);
        } catch (ValidationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
