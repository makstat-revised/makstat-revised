package com.makstat.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.makstat.demo.entity.CategoryEntity;
import com.makstat.demo.entity.SubCategoryEntity;
import com.makstat.demo.model.EmployeeCount;
import com.makstat.demo.model.common.Category;
import com.makstat.demo.model.common.SubCategory;
import com.makstat.demo.model.common.Gender;
import com.makstat.demo.model.common.Year;
import com.makstat.demo.repository.CategoryEntityRepository;
import com.makstat.demo.repository.EmployeeCountEntityRepository;
import com.makstat.demo.repository.SubCategoryEntityRepository;

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
    private final CategoryEntityRepository categoryEntityRepository;
    private final SubCategoryEntityRepository subCategoryEntityRepository;

    EmployeeCountController(EmployeeCountEntityRepository employeeCountEntityRepository, SubCategoryEntityRepository subCategoryEntityRepository, CategoryEntityRepository categoryEntityRepository) {
        this.employeeCountEntityRepository = employeeCountEntityRepository;
        this.categoryEntityRepository = categoryEntityRepository;
        this.subCategoryEntityRepository = subCategoryEntityRepository;
    }

    @GetMapping("")
    EntityModel<EmployeeCount> getEmployeeCount() {
        List<EntityModel<Category>> categoryEntityModels = getCategoryEntities()
            .stream()
            .map(categoryEntity -> getCategory(categoryEntity.getName(), true))
            .collect(Collectors.toList());
        EmployeeCount employeeCountResource = new EmployeeCount(CollectionModel.of(categoryEntityModels));
        return EntityModel.of(employeeCountResource,
            linkTo(methodOn(EmployeeCountController.class).getEmployeeCount()).withSelfRel());
    }

    @GetMapping("/{categoryName}")
    EntityModel<Category> getCategory(@PathVariable String categoryName, boolean... selfRelOnly) {
        List<EntityModel<SubCategory>> subCategoryEntityModels = getSubCategoryEntities(categoryName)
            .stream()
            .map(subCategoryEntity -> getSubCategory(categoryName, subCategoryEntity.getName(), true))
            .collect(Collectors.toList());
        Category categoryResource = new Category(categoryName, CollectionModel.of(subCategoryEntityModels));
        if (selfRelOnly != null && selfRelOnly[0] == true)
            return EntityModel.of(categoryResource,
                linkTo(methodOn(EmployeeCountController.class).getCategory(categoryName)).withSelfRel(),
                linkTo(methodOn(CategoryController.class).getCategory(categoryName)).withRel("_category"));
        return EntityModel.of(categoryResource,
            linkTo(methodOn(EmployeeCountController.class).getCategory(categoryName)).withSelfRel(),
            linkTo(methodOn(EmployeeCountController.class).getEmployeeCount()).withRel("employeeCount"),
            linkTo(methodOn(CategoryController.class).getCategory(categoryName)).withRel("_category"));
    }

    @GetMapping("/{categoryName}/{subCategoryName}")
    EntityModel<SubCategory> getSubCategory(@PathVariable String categoryName, @PathVariable String subCategoryName, boolean... selfRelOnly) {
        List<EntityModel<Year>> yearEntityModels = employeeCountEntityRepository.findEmployeeCountBySubCategory(getSubCategoryEntity(categoryName, subCategoryName))
            .stream()
            .map(employeeCountEntity -> getYear(categoryName, subCategoryName, employeeCountEntity.getYear(), true))
            .collect(Collectors.toList());
        SubCategory subCategoryResource = new SubCategory(subCategoryName, CollectionModel.of(yearEntityModels));
        if (selfRelOnly != null && selfRelOnly[0] == true)
            return EntityModel.of(subCategoryResource,
                linkTo(methodOn(EmployeeCountController.class).getSubCategory(categoryName, subCategoryName)).withSelfRel(),
                linkTo(methodOn(CategoryController.class).getSubCategory(categoryName, subCategoryName)).withRel("_subCategory"));
        return EntityModel.of(subCategoryResource,
            linkTo(methodOn(EmployeeCountController.class).getSubCategory(categoryName, subCategoryName)).withSelfRel(),
            linkTo(methodOn(EmployeeCountController.class).getCategory(categoryName)).withRel("category"),
            linkTo(methodOn(CategoryController.class).getSubCategory(categoryName, subCategoryName)).withRel("_subCategory"));
    }

    @GetMapping("/{categoryName}/{subCategoryName}/{year}")
    EntityModel<Year> getYear(@PathVariable String categoryName, @PathVariable String subCategoryName, @PathVariable int year, boolean... selfRelOnly) {
        List<EntityModel<Gender>> genderEntityModels = employeeCountEntityRepository.findEmployeeCountBySubCategoryAndYear(getSubCategoryEntity(categoryName, subCategoryName), year)
            .stream()
            .map(employeeCountEntity -> getGender(categoryName, subCategoryName, year, Gender.toString(employeeCountEntity.getSex()), true))
            .collect(Collectors.toList());
        Year yearResource = new Year(year, CollectionModel.of(genderEntityModels));
        if (selfRelOnly != null && selfRelOnly[0] == true)
            return EntityModel.of(yearResource,
                linkTo(methodOn(EmployeeCountController.class).getYear(categoryName, subCategoryName, year)).withSelfRel());
        return EntityModel.of(yearResource,
            linkTo(methodOn(EmployeeCountController.class).getYear(categoryName, subCategoryName, year)).withSelfRel(),
            linkTo(methodOn(EmployeeCountController.class).getSubCategory(categoryName, subCategoryName)).withRel("subCategory"));
    }

    @GetMapping("/{categoryName}/{subCategoryName}/{year}/{gender}")
    EntityModel<Gender> getGender(@PathVariable String categoryName, @PathVariable String subCategoryName, @PathVariable int year, @PathVariable String gender, boolean... selfRelOnly) {
        Gender genderResource = new Gender(
            gender, 
            employeeCountEntityRepository.findEmployeeCountBySubCategoryAndYearAndSex(
                getSubCategoryEntity(categoryName, subCategoryName),
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
    
    private List<CategoryEntity> getCategoryEntities() {
        return categoryEntityRepository.findAll();
    }

    private List<SubCategoryEntity> getSubCategoryEntities(String categoryName) {
        return subCategoryEntityRepository.findSubCategoryByCategoryName(categoryName);
    }

    private SubCategoryEntity getSubCategoryEntity(String categoryName, String subCategoryName) {
        return subCategoryEntityRepository.findSubCategoryByCategoryNameAndName(categoryName, subCategoryName);
    }
}
