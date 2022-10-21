package com.bosonit.springdata.controller;


import com.bosonit.springdata.application.CourseServiceImpl;
import com.bosonit.springdata.controller.dto.CourseInputDto;
import com.bosonit.springdata.controller.dto.CourseOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/course")
public class CourseController {
    @Autowired
    CourseServiceImpl courseService;


    @PostMapping
    public ResponseEntity<CourseOutputDto> addCourse(@RequestBody CourseInputDto courseInputDto) {
        return ResponseEntity.ok().body(courseService.addCourse(courseInputDto));
    }

    @PostMapping("/student")
    public ResponseEntity<String> addCourseToStudent(@RequestParam int course_id,@RequestParam int student_id) {
        try {
            courseService.addCoursesToStudent(course_id,student_id);
            return ResponseEntity.ok().body("Course with id "+course_id+" was successfully added to student with id "+student_id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Something went wrong "+ e.getMessage());
        }

    }


}
