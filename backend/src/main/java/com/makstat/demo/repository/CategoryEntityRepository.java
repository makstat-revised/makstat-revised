package com.makstat.demo.repository;

import com.makstat.demo.entity.CategoryEntity;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.stereotype.Repository;

@Repository
@RepositoryRestResource(exported = false)
public interface CategoryEntityRepository extends JpaRepository<CategoryEntity, Integer> {

    CategoryEntity findCategoryByName(String name);
}
