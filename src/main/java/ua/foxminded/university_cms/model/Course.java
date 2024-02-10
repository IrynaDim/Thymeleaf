package ua.foxminded.university_cms.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class Course {
    private Integer id;
    private String name;
    private String description;
}
