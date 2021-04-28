package com.makstat.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.makstat.demo.entity.CategoryEntity;
import com.makstat.demo.entity.EmployeeCountEntity;
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
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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
        List<EmployeeCountEntity> employeeCountEntities = employeeCountEntityRepository.findEmployeeCountBySubCategory(getSubCategoryEntity(categoryName, subCategoryName));
        if (employeeCountEntities.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        List<EntityModel<Year>> yearEntityModels = employeeCountEntities
            .stream()
            .distinct()
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
        List<EmployeeCountEntity> employeeCountEntities = employeeCountEntityRepository.findEmployeeCountBySubCategoryAndYear(getSubCategoryEntity(categoryName, subCategoryName), year);
        if (employeeCountEntities.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        List<EntityModel<Gender>> genderEntityModels = employeeCountEntities
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
        EmployeeCountEntity employeeCountEntity = employeeCountEntityRepository.findEmployeeCountBySubCategoryAndYearAndSex(
            getSubCategoryEntity(categoryName, subCategoryName), year, Gender.toBoolean(gender));
        if (employeeCountEntity == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        Gender genderResource = new Gender(gender, employeeCountEntity.getCount());
        if (selfRelOnly != null && selfRelOnly[0] == true)
            return EntityModel.of(genderResource,
                linkTo(methodOn(EmployeeCountController.class).getGender(categoryName, subCategoryName, year, gender)).withSelfRel());
        return EntityModel.of(genderResource,
            linkTo(methodOn(EmployeeCountController.class).getGender(categoryName, subCategoryName, year, gender)).withSelfRel(),
            linkTo(methodOn(EmployeeCountController.class).getYear(categoryName, subCategoryName, year)).withRel("year"));
    }
    
    private List<CategoryEntity> getCategoryEntities() {
        List<CategoryEntity> categoryEntities = categoryEntityRepository.findAll();
        return categoryEntities;
    }

    private List<SubCategoryEntity> getSubCategoryEntities(String categoryName) {
        List<SubCategoryEntity> subCategoryEntities = subCategoryEntityRepository.findSubCategoryByCategoryName(categoryName);
        if (subCategoryEntities.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return subCategoryEntities;
    }

    private SubCategoryEntity getSubCategoryEntity(String categoryName, String subCategoryName) {
        SubCategoryEntity subCategoryEntity = subCategoryEntityRepository.findSubCategoryByCategoryNameAndName(categoryName, subCategoryName);
        if (subCategoryEntity == null) throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        return subCategoryEntity;
    }
}
