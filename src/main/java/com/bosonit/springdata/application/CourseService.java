package com.bosonit.springdata.application;

import com.bosonit.springdata.controller.dto.CourseInputDto;
import com.bosonit.springdata.controller.dto.CourseOutputDto;

public interface CourseService {
    CourseOutputDto addCourse(CourseInputDto courseInputDto);
    void addCoursesToStudent(int course_id, int student_id);

}
