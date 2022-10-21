package com.bosonit.springdata.controller;

import com.bosonit.springdata.application.Student_DetailsServiceImpl;
import com.bosonit.springdata.controller.dto.Student_DetailsInputDto;
import com.bosonit.springdata.controller.dto.Student_DetailsOutputDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/details")
public class DetailsController {
    @Autowired
    Student_DetailsServiceImpl student_detailsService;

    @PostMapping
    public ResponseEntity<Student_DetailsOutputDto> addDetailsToStudent(
            @RequestBody Student_DetailsInputDto student_detailsInputDto) {

        try {
            return ResponseEntity
                    .ok()
                    .body(student_detailsService
                            .addDetailsToStudent(student_detailsInputDto));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }
}

