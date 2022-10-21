package com.bosonit.springdata.repository;

import com.bosonit.springdata.domain.School;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SchoolRepository extends JpaRepository<School, Integer> {
}
