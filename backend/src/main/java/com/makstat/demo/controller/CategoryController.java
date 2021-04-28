package com.makstat.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.makstat.demo.model.Category;
import com.makstat.demo.model.SubCategory;
import com.makstat.demo.repository.CategoryEntityRepository;
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
@RequestMapping("/categories")
public class CategoryController {
   
    private final CategoryEntityRepository categoryEntityRepository;
    private final SubCategoryEntityRepository subCategoryEntityRepository;

    CategoryController(CategoryEntityRepository categoryRepository, SubCategoryEntityRepository subCategoryRepository) {
        this.categoryEntityRepository = categoryRepository;
        this.subCategoryEntityRepository = subCategoryRepository;
    }

    @GetMapping()
    CollectionModel<EntityModel<Category>> getCategories() {
        List<EntityModel<Category>> categoryEntityModels = categoryEntityRepository.findAll()
            .stream()
            .map(categoryEntity -> getCategory(categoryEntity.getName(), true))
            .collect(Collectors.toList());
        return CollectionModel.of(categoryEntityModels,
            linkTo(methodOn(CategoryController.class).getCategories()).withSelfRel());
    }

    @GetMapping("/{categoryName}")
    EntityModel<Category> getCategory(@PathVariable String categoryName, boolean... selfRelOnly) {
        Category categoryResource = new Category(
            categoryEntityRepository.findCategoryByName(categoryName).getName(),
            getSubCategories(categoryName, true));
        if (selfRelOnly != null && selfRelOnly[0] == true)
            return EntityModel.of(categoryResource,
                linkTo(methodOn(CategoryController.class).getCategory(categoryName)).withSelfRel());    
        return EntityModel.of(categoryResource,
            linkTo(methodOn(CategoryController.class).getCategory(categoryName)).withSelfRel(),
            linkTo(methodOn(CategoryController.class).getCategories()).withRel("categories"));
    }

    @GetMapping("/{categoryName}/subCategories")
    CollectionModel<EntityModel<SubCategory>> getSubCategories(@PathVariable String categoryName, boolean... selfRelOnly) {
        List<EntityModel<SubCategory>> subCategoryEntityModels = subCategoryEntityRepository.findSubCategoryByCategoryName(categoryName)
            .stream()
            .map(subCategoryEntity -> getSubCategory(categoryName, subCategoryEntity.getName(), true))
            .collect(Collectors.toList());
        if (selfRelOnly != null && selfRelOnly[0] == true)
            return CollectionModel.of(subCategoryEntityModels,
                linkTo(methodOn(CategoryController.class).getSubCategories(categoryName)).withSelfRel());    
        return CollectionModel.of(subCategoryEntityModels,
            linkTo(methodOn(CategoryController.class).getSubCategories(categoryName)).withSelfRel(),
            linkTo(methodOn(CategoryController.class).getCategory(categoryName)).withRel("category"));
    }

    @GetMapping("/{categoryName}/subCategories/{subCategoryName}")
    EntityModel<SubCategory> getSubCategory(@PathVariable String categoryName, @PathVariable String subCategoryName, boolean... selfRelOnly) {
        SubCategory subCategoryResource = new SubCategory(
            subCategoryEntityRepository.findSubCategoryByCategoryNameAndName(categoryName, subCategoryName).getName());
        if (selfRelOnly != null && selfRelOnly[0] == true)
            return EntityModel.of(subCategoryResource,
                linkTo(methodOn(CategoryController.class).getSubCategory(categoryName, subCategoryName)).withSelfRel());
        return EntityModel.of(subCategoryResource,
            linkTo(methodOn(CategoryController.class).getSubCategory(categoryName, subCategoryName)).withSelfRel(),
            linkTo(methodOn(CategoryController.class).getSubCategories(categoryName)).withRel("subCategories"));
    }
}
