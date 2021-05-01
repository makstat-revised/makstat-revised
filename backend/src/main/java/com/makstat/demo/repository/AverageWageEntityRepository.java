package com.makstat.demo.repository;

import java.util.List;

import com.makstat.demo.entity.AverageWageEntity;
import com.makstat.demo.entity.SubCategoryEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AverageWageEntityRepository extends JpaRepository<AverageWageEntity, Integer> {

    List<AverageWageEntity> findAverageWageBySubCategory(SubCategoryEntity subCategory);

    List<AverageWageEntity> findAverageWageBySubCategoryAndYear(SubCategoryEntity subCategory, int year);

    AverageWageEntity findAverageWageBySubCategoryAndYearAndSex(SubCategoryEntity subCategory, int year, Boolean sex);
}
