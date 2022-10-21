package com.bosonit.springdata.repository;

import com.bosonit.springdata.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Integer>,
        PagingAndSortingRepository<Student, Integer> {

    List<Student> findByNameLike(String name);

    @Query("SELECT s FROM Student s WHERE s.name like %?1%")
    List<Student> getByNameLike(String name);

    @Query(value = "SELECT * FROM Student WHERE name like %?1%", nativeQuery = true)
    List<Student> getByNameLikeNative(String name);

}
