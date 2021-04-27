package com.makstat.demo.controller;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.makstat.demo.entity.CategoryEntity;
import com.makstat.demo.entity.SubCategoryEntity;
import com.makstat.demo.model.Category;
import com.makstat.demo.model.SubCategory;
import com.makstat.demo.model.common.Gender;
import com.makstat.demo.repository.CategoryEntityRepository;
import com.makstat.demo.repository.EmployeeCountEntityRepository;
import com.makstat.demo.repository.SubCategoryEntityRepository;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/employeeCount")
public class EmployeeCountController {
   
    private final EmployeeCountEntityRepository employeeCountEntityRepository;

    EmployeeCountController(EmployeeCountEntityRepository employeeCountEntityRepository) {
        this.employeeCountEntityRepository = employeeCountEntityRepository;
    }

    @GetMapping("/{categoryName}/{subCategoryName}/{year}/{gender}")
    EntityModel<SubCategory> getGender(@PathVariable String categoryName, @PathVariable String subCategoryName, @PathVariable int year, @PathVariable String gender) {
        Gender genderResource = new Gender(
            gender, employeeCountEntityRepository.findEmployeeCountBySubCategoryNameAndYearAndSex(subCategoryName, year, (gender.equals("male")) ? false : true));
        return EntityModel.of(subCategoryResource,
            linkTo(methodOn(CategoryController.class).getSubCategory(categoryName, subCategoryName)).withSelfRel(),
            linkTo(methodOn(CategoryController.class).getSubCategories(categoryName)).withRel("subCategories"));
    }
}
