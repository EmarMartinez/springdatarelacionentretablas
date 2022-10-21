package com.bosonit.springdata.application;

import com.bosonit.springdata.controller.dto.SchoolInputDto;
import com.bosonit.springdata.controller.dto.SchoolOutputDto;

public interface SchoolService {
    public SchoolOutputDto addSchool(SchoolInputDto schoolInputDto);
    public void addStudentToSchool(int student_id, int school_id);
}
