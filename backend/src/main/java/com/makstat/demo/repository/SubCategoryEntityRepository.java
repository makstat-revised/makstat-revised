package com.makstat.demo.repository;

import java.util.List;

import com.makstat.demo.entity.CategoryEntity;
import com.makstat.demo.entity.SubCategoryEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(exported = false)
public interface SubCategoryEntityRepository extends JpaRepository<SubCategoryEntity, Integer> {
    
    List<SubCategoryEntity> findSubCategoryByCategory(CategoryEntity category);

    List<SubCategoryEntity> findSubCategoryByCategoryName(String name);

    SubCategoryEntity findSubCategoryByCategoryNameAndName(String categoryName, String name);
}
