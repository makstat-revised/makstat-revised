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
import com.makstat.demo.model.common.Year;
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
    private final SubCategoryEntityRepository subCategoryEntityRepository;

    EmployeeCountController(EmployeeCountEntityRepository employeeCountEntityRepository, SubCategoryEntityRepository subCategoryEntityRepository) {
        this.employeeCountEntityRepository = employeeCountEntityRepository;
        this.subCategoryEntityRepository = subCategoryEntityRepository;
    }

    @GetMapping("/{categoryName}/{subCategoryName}/{year}")
    EntityModel<Year> getYear(@PathVariable String categoryName, @PathVariable String subCategoryName, @PathVariable int year) {
        List<EntityModel<Gender>> genderEntityModels = employeeCountEntityRepository.findEmployeeCountBySubCategoryAndYear(getSubCategory(categoryName, subCategoryName), year)
            .stream()
            .map(employeeCountEntity -> getGender(categoryName, subCategoryName, year, Gender.toString(employeeCountEntity.getSex()), true))
            .collect(Collectors.toList());
        Year yearResource = new Year(year, CollectionModel.of(genderEntityModels));
        return EntityModel.of(yearResource,
            linkTo(methodOn(EmployeeCountController.class).getYear(categoryName, subCategoryName, year)).withSelfRel());
    }

    @GetMapping("/{categoryName}/{subCategoryName}/{year}/{gender}")
    EntityModel<Gender> getGender(@PathVariable String categoryName, @PathVariable String subCategoryName, @PathVariable int year, @PathVariable String gender, boolean... selfRelOnly) {
        Gender genderResource = new Gender(
            gender, 
            employeeCountEntityRepository.findEmployeeCountBySubCategoryAndYearAndSex(
                getSubCategory(categoryName, subCategoryName),
                year,
                Gender.toBoolean(gender))
                    .getCount());
        if (selfRelOnly != null && selfRelOnly[0] == true)
            return EntityModel.of(genderResource,
                linkTo(methodOn(EmployeeCountController.class).getGender(categoryName, subCategoryName, year, gender)).withSelfRel());
        return EntityModel.of(genderResource,
            linkTo(methodOn(EmployeeCountController.class).getGender(categoryName, subCategoryName, year, gender)).withSelfRel(),
            linkTo(methodOn(EmployeeCountController.class).getYear(categoryName, subCategoryName, year)).withRel("year"));
    }

    private SubCategoryEntity getSubCategory(String categoryName, String subCategoryName) {
        return subCategoryEntityRepository.findSubCategoryByCategoryNameAndName(categoryName, subCategoryName);
    }
}
