package com.bosonit.springdata.controller;

import com.bosonit.springdata.application.SchoolServiceImpl;
import com.bosonit.springdata.controller.dto.SchoolInputDto;
import com.bosonit.springdata.controller.dto.SchoolOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/school")
public class SchoolController {
    @Autowired
    SchoolServiceImpl schoolService;
    @PostMapping
    public ResponseEntity<SchoolOutputDto> addSchool(@RequestBody SchoolInputDto schoolInputDto) {

        return ResponseEntity.ok().body(schoolService.addSchool(schoolInputDto));
    }
    @PostMapping("/student")
    public ResponseEntity<String> addStudentToSchool(@RequestParam int student_id, @RequestParam int school_id) {
        try {
            schoolService.addStudentToSchool(student_id,school_id);
            return ResponseEntity.ok().body("Student with id " + student_id + " added successfully to school with id " + school_id);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Something went wrong");
        }
    }
}
