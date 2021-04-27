package com.makstat.demo.repository;

import java.util.List;

import com.makstat.demo.entity.EmployeeCountEntity;
import com.makstat.demo.model.SubCategory;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeCountEntityRepository extends JpaRepository<EmployeeCountEntity, Integer> {
    
    List<EmployeeCountEntity> findEmployeeCountBySubCategoryName(String subCategoryName);

    List<EmployeeCountEntity> findEmployeeCountBySubCategoryNameAndYear(String subCategoryName, int year);

    EmployeeCountEntity findEmployeeCountBySubCategoryNameAndYearAndSex(String subCategoryName, int year, boolean sex);
}
