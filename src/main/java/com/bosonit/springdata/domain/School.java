package com.bosonit.springdata.domain;

import com.bosonit.springdata.controller.dto.SchoolInputDto;
import com.bosonit.springdata.controller.dto.SchoolOutputDto;

import lombok.*;

import javax.persistence.*;
import java.util.Set;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class School {
    @Id
    @GeneratedValue
    private int id;
    private String name;
    private String address;

    @OneToMany(mappedBy = "school")
    private Set<Student> students;

    public School(SchoolInputDto schoolInputDto) {
        this.id = schoolInputDto.getId();
        this.name = schoolInputDto.getName();
        this.address = schoolInputDto.getAddress();
    }

    public SchoolOutputDto schoolToSchoolOutputDto() {
        return new SchoolOutputDto(
                this.id,
                this.name,
                this.address
        );
    }

//    public void addStudent(Student student) {
//        this.students.add(student);
//    }
}
