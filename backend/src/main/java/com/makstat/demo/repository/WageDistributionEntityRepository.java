package com.makstat.demo.repository;

import java.util.List;

import com.makstat.demo.entity.WageDistributionEntity;
import com.makstat.demo.entity.SubCategoryEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface WageDistributionEntityRepository extends JpaRepository<WageDistributionEntity, Integer> {
    
    List<WageDistributionEntity> findWageDistributionBySubCategory(SubCategoryEntity subCategory);

    List<WageDistributionEntity> findWageDistributionBySubCategoryAndYear(SubCategoryEntity subCategory, int year);

    WageDistributionEntity findWageDistributionBySubCategoryAndYearAndWageGroup(SubCategoryEntity subCategory, int year, SubCategoryEntity wageGroup);
}
