package com.bosonit.springdata.domain;

import com.bosonit.springdata.controller.dto.CourseInputDto;
import com.bosonit.springdata.controller.dto.CourseOutputDto;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Course {
    @GeneratedValue
    @Id
    private int id;
    private String name;

    @ManyToMany(mappedBy = "courses")
    Set<Student> students;

    public Course(CourseInputDto courseInputDto) {
        this.id = courseInputDto.getId();
        this.name = courseInputDto.getName();
    }

    public CourseOutputDto courseToCourseOutputDto() {
        return new CourseOutputDto(
                this.id,
                this.name
        );
    }
}
