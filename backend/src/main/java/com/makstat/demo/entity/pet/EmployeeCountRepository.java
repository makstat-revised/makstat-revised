package com.makstat.demo.entity.pet;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface EmployeeCountRepository extends CrudRepository<EmployeeCount, Long> {

    // List<EmployeeCount> findByYear(int year);

    // List<EmployeeCount> findBySex(boolean sex);

    // List<EmployeeCount> findByYearAndSex(int year, boolean sex);
}
