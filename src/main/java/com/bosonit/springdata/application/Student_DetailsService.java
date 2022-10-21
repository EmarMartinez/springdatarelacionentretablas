package com.bosonit.springdata.application;

import com.bosonit.springdata.controller.dto.Student_DetailsInputDto;
import com.bosonit.springdata.controller.dto.Student_DetailsOutputDto;


public interface Student_DetailsService {
    Student_DetailsOutputDto addDetailsToStudent(Student_DetailsInputDto student_detailsInputDto);
}
