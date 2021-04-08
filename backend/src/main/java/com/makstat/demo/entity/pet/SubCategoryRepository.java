package com.makstat.demo.entity.pet;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface SubCategoryRepository extends CrudRepository<SubCategory, String> {

    List<SubCategory> findByCategoryName(String categoryName);
}
