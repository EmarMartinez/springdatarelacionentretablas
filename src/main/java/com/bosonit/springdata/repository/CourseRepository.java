package com.bosonit.springdata.repository;

import com.bosonit.springdata.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Integer> {
}
