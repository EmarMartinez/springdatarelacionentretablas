package com.bosonit.springdata.application;

import com.bosonit.springdata.controller.dto.Student_DetailsInputDto;
import com.bosonit.springdata.controller.dto.Student_DetailsOutputDto;
import com.bosonit.springdata.domain.Student;
import com.bosonit.springdata.domain.Student_Details;
import com.bosonit.springdata.repository.StudentRepository;
import com.bosonit.springdata.repository.Student_DetailsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class Student_DetailsServiceImpl implements Student_DetailsService{
    @Autowired
    Student_DetailsRepository student_detailsRepository;
    @Autowired
    StudentRepository studentRepository;
    @Override
    public Student_DetailsOutputDto addDetailsToStudent(Student_DetailsInputDto student_detailsInputDto) {

            Student student = studentRepository
                    .findById(student_detailsInputDto.getStudent_id())
                    .orElseThrow();
            Student_Details student_details = new Student_Details(student_detailsInputDto);

            student.setStudent_details(student_details);
            student_details.setStudent(student);

            return student_detailsRepository
                    .save(student_details)
                    .student_DetailsToStudent_DetailsOutputDto();
    }
}
