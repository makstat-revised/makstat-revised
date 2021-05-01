package com.makstat.demo.repository;

import java.util.List;

import com.makstat.demo.entity.UnemploymentRateEntity;
import com.makstat.demo.entity.SubCategoryEntity;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UnemploymentRateEntityRepository extends JpaRepository<UnemploymentRateEntity, Integer> {

    List<UnemploymentRateEntity> findUnemploymentRateBySubCategory(SubCategoryEntity subCategory);

    List<UnemploymentRateEntity> findUnemploymentRateBySubCategoryAndYear(SubCategoryEntity subCategory, int year);

    UnemploymentRateEntity findUnemploymentRateBySubCategoryAndYearAndSex(SubCategoryEntity subCategory, int year,
            Boolean sex);
}
