package com.bosonit.springdata.domain;

import com.bosonit.springdata.controller.dto.Student_DetailsInputDto;
import com.bosonit.springdata.controller.dto.Student_DetailsOutputDto;
import lombok.*;

import javax.persistence.*;

@Entity
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class Student_Details {

    @Id
    @GeneratedValue
    private int id;
    private String address;
    private String phone_number;
    @OneToOne
    @JoinColumn(name = "Student_Id", nullable = false, unique = true)
    private Student student;


    public Student_Details(Student_DetailsInputDto student_DetailsInputDto) {
        this.address = student_DetailsInputDto.getAddress();
        this.phone_number = student_DetailsInputDto.getPhone_number();
    }

    public Student_DetailsOutputDto student_DetailsToStudent_DetailsOutputDto() {
        return new Student_DetailsOutputDto(
                this.id,
                this.address,
                this.phone_number,
                this.student.getId(),
                this.student.getName(),
                this.student.getLastName()
        );
    }
}
