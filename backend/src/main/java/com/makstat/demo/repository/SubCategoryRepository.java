package com.makstat.demo.repository;

import java.util.List;

import com.makstat.demo.entity.Category;
import com.makstat.demo.entity.SubCategory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(exported = false)
public interface SubCategoryRepository extends JpaRepository<SubCategory, Integer> {
    
    List<SubCategory> findSubCategoryByCategory(Category category);

    List<SubCategory> findSubCategoryByCategoryName(String name);

    SubCategory findSubCategoryByCategoryNameAndName(String categoryName, String name);
}
