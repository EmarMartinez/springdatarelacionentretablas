package com.bosonit.springdata.application;

import com.bosonit.springdata.controller.dto.SchoolInputDto;
import com.bosonit.springdata.controller.dto.SchoolOutputDto;
import com.bosonit.springdata.domain.School;
import com.bosonit.springdata.domain.Student;
import com.bosonit.springdata.repository.SchoolRepository;
import com.bosonit.springdata.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SchoolServiceImpl implements SchoolService{
    @Autowired
    SchoolRepository schoolRepository;
    @Autowired
    StudentRepository studentRepository;

    @Override
    public SchoolOutputDto addSchool(SchoolInputDto schoolInputDto) {
        return schoolRepository.save(new School(schoolInputDto))
                .schoolToSchoolOutputDto();
    }

        @Override
    public void addStudentToSchool(int student_id, int school_id) {
        Student student = studentRepository.findById(student_id).orElseThrow();
        School school = schoolRepository.findById(school_id).orElseThrow();

        student.setSchool(school);
        school.getStudents().add(student);

        studentRepository.save(student);
        schoolRepository.save(school);
    }
}
