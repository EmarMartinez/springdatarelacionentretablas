package com.bosonit.springdata.application;

import com.bosonit.springdata.controller.dto.CourseInputDto;
import com.bosonit.springdata.controller.dto.CourseOutputDto;

import com.bosonit.springdata.domain.Course;
import com.bosonit.springdata.domain.Student;

import com.bosonit.springdata.repository.CourseRepository;
import com.bosonit.springdata.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



@Service
public class CourseServiceImpl implements CourseService{
    @Autowired
    CourseRepository courseRepository;

    @Autowired
    StudentRepository studentRepository;
    @Override
    public CourseOutputDto addCourse(CourseInputDto courseInputDto) {
        return courseRepository
                .save(new Course(courseInputDto))
                .courseToCourseOutputDto();
    }

    @Override
    public void addCoursesToStudent(int course_id, int student_id) {
        Student student = studentRepository.findById(student_id).orElseThrow();
        Course course = courseRepository.findById(course_id).orElseThrow();

        student.getCourses().add(course);
        studentRepository.save(student);
    }


}
