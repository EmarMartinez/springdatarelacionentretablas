package com.bosonit.springdata.controller.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student_DetailsInputDto {
    private int id;
    private String address;
    private String phone_number;
    private int student_id;
}
