package ua.foxminded.university_cms.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Schedule {

    private int id;

    private String courseName;

    private String professorName;

    private String groupName;

    private LocalTime startAt;

    private LocalTime endAt;

    private String classroom;

    private LocalDate scheduleDate;
}
