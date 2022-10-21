package com.bosonit.springdata.repository;

import com.bosonit.springdata.controller.dto.StudentOutputDto;
import com.bosonit.springdata.domain.Student;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class StudentRepositoryImpl {
    @PersistenceContext
    private EntityManager entityManager;

    public List<StudentOutputDto> getCustomQuery(HashMap<String, Object> conditions) {
        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
        CriteriaQuery<Student> query = cb.createQuery(Student.class);
        Root<Student> root = query.from(Student.class);

        List<Predicate> predicates = new ArrayList<>();

        conditions.forEach((field, value) -> {
            switch (field) {
                case "name":
                    predicates.add(cb.like(root.get(field), "%" + (String) value + "%"));
                    break;
                case "lastName":
                    predicates.add(cb.like(root.get(field), "%" + (String) value + "%"));
                    break;
            }
        });
//        query.select(root).where(predicates.toArray(new Predicate[predicates.size()]));
        query.select(root);
        Predicate[] predicate = new Predicate[predicates.size()];
        query.where(predicates.toArray(predicate));

        return entityManager
                .createQuery(query)
                .getResultList()
                .stream()
                .map(Student::studentToStudentOutputDto)
                .toList();
    }
}
