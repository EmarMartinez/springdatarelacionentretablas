package com.bosonit.springdata.controller;

import com.bosonit.springdata.application.StudentServiceImpl;
import com.bosonit.springdata.controller.dto.StudentInputDto;
import com.bosonit.springdata.controller.dto.StudentOutputDto;
import com.bosonit.springdata.repository.StudentRepositoryImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.net.URI;
import java.util.HashMap;
import java.util.Map;


@RestController
@RequestMapping("/student")
public class Controller {

    @Autowired
    StudentServiceImpl studentService;

    @PostMapping
    public ResponseEntity<StudentOutputDto> addStudent(@Valid @RequestBody StudentInputDto student) {
        URI location = URI.create("/student");
        return ResponseEntity.created(location).body(studentService.addStudent(student));
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentOutputDto> getStudentById(@PathVariable int id) {
        try {
            return ResponseEntity.ok().body(studentService.getStudentById(id));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteStudentById(@RequestParam int id) {
        try {
            studentService.deleteStudentById(id);
            return ResponseEntity.ok().body("student with id: "+id+" was deleted");
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    public Iterable<StudentOutputDto> getAllStudents(
            @RequestParam(defaultValue = "0", required = false) int pageNumber,
            @RequestParam(defaultValue = "4", required = false) int pageSize) {

        return studentService.getAllStudents(pageNumber, pageSize);
    }

    @PutMapping
    public ResponseEntity<StudentOutputDto> updateStudent(@RequestBody StudentInputDto student) {
        try {
            studentService.getStudentById(student.getId());
            return  ResponseEntity.ok().body(studentService.addStudent(student));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    @GetMapping("/name/{name}")
    public Iterable<StudentOutputDto> findStudentByNameLike(@PathVariable String name) {
        return studentService.getAllStudentsByNameLike(name);
    }

    @GetMapping("/namejpql/{name}")
    public Iterable<StudentOutputDto> findStudentByNameLikeJPQL(@PathVariable String name) {
        return studentService.getAllStudentsByNameLikeJQPL(name);
    }

    @GetMapping("/namenative/{name}")
    public Iterable<StudentOutputDto> findStudentByNameLikeNative(@PathVariable String name) {
        return studentService.getAllStudentsByNameLikeNative(name);
    }

    @Autowired
    StudentRepositoryImpl studentRepository;

    @GetMapping("/customquery")
    public Iterable<StudentOutputDto> findStudentByNameAndLastname(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String lastName) {

        HashMap<String, Object> conditions = new HashMap<>();

        if(name != null) conditions.put("name",name);
        if(lastName != null) conditions.put ("lastName", lastName);

        return studentRepository.getCustomQuery(conditions);

    }

}
