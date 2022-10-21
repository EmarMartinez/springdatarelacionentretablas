package com.bosonit.springdata.domain;

import com.bosonit.springdata.controller.dto.StudentInputDto;
import com.bosonit.springdata.controller.dto.StudentOutputDto;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Student {
    @Id
    @GeneratedValue
    int id;
    String name;
    String lastName;

    @OneToOne
    private Student_Details student_details;

    @ManyToOne
    private School school;

    @ManyToMany
    Set<Course> courses;

    public Student(StudentInputDto studentInputDto) {
        this.id = studentInputDto.getId();
        this.name = studentInputDto.getName();
        this.lastName = studentInputDto.getLastName();
    }

    public StudentOutputDto studentToStudentOutputDto() {
        return new StudentOutputDto(
                this.id,
                this.name,
                this.lastName
        );
    }
}
