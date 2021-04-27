package com.makstat.demo.repository;

import java.util.List;

import com.makstat.demo.entity.EmployeeCountEntity;
import com.makstat.demo.entity.SubCategoryEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeCountEntityRepository extends JpaRepository<EmployeeCountEntity, Integer> {
    
    List<EmployeeCountEntity> findEmployeeCountBySubCategory(SubCategoryEntity subCategory);

    List<EmployeeCountEntity> findEmployeeCountBySubCategoryAndYear(SubCategoryEntity subCategory, int year);

    EmployeeCountEntity findEmployeeCountBySubCategoryAndYearAndSex(SubCategoryEntity subCategory, int year, boolean sex);
}
