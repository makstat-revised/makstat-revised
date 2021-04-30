package com.makstat.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.makstat.demo.entity.CategoryEntity;
import com.makstat.demo.entity.SubCategoryEntity;
import com.makstat.demo.model.Category;
import com.makstat.demo.model.SubCategory;
import com.makstat.demo.repository.CategoryEntityRepository;
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
        List<EntityModel<Category>> categoryEntityModels = categoryEntityRepository.findAll().stream()
                .map(categoryEntity -> getCategory(categoryEntity.getName(), true)).collect(Collectors.toList());
        return CollectionModel.of(categoryEntityModels,
                linkTo(methodOn(CategoryController.class).getCategories()).withSelfRel());
    }

    @GetMapping("/{categoryName}")
    EntityModel<Category> getCategory(@PathVariable String categoryName, boolean... selfRelOnly) {
        CategoryEntity categoryEntity = categoryEntityRepository.findCategoryByName(categoryName);
        if (categoryEntity == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        Category categoryResource = new Category(categoryEntity.getName(), getSubCategories(categoryName, true));
        if (selfRelOnly != null && selfRelOnly[0] == true)
            return EntityModel.of(categoryResource,
                    linkTo(methodOn(CategoryController.class).getCategory(categoryName)).withSelfRel());
        return EntityModel.of(categoryResource,
                linkTo(methodOn(CategoryController.class).getCategory(categoryName)).withSelfRel(),
                linkTo(methodOn(CategoryController.class).getCategories()).withRel("categories"));
    }

    @GetMapping("/{categoryName}/subCategories")
    CollectionModel<EntityModel<SubCategory>> getSubCategories(@PathVariable String categoryName,
            boolean... selfRelOnly) {
        CategoryEntity categoryEntity = categoryEntityRepository.findCategoryByName(categoryName);
        if (categoryEntity == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        List<SubCategoryEntity> subCategoryEntities = subCategoryEntityRepository
                .findSubCategoryByCategoryName(categoryName);
        List<EntityModel<SubCategory>> subCategoryEntityModels = subCategoryEntities.stream()
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
    EntityModel<SubCategory> getSubCategory(@PathVariable String categoryName, @PathVariable String subCategoryName,
            boolean... selfRelOnly) {
        SubCategoryEntity subCategoryEntity = subCategoryEntityRepository
                .findSubCategoryByCategoryNameAndName(categoryName, subCategoryName);
        if (subCategoryEntity == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        SubCategory subCategoryResource = new SubCategory(subCategoryEntity.getName());
        if (selfRelOnly != null && selfRelOnly[0] == true)
            return EntityModel.of(subCategoryResource,
                    linkTo(methodOn(CategoryController.class).getSubCategory(categoryName, subCategoryName))
                            .withSelfRel());
        return EntityModel.of(subCategoryResource,
                linkTo(methodOn(CategoryController.class).getSubCategory(categoryName, subCategoryName)).withSelfRel(),
                linkTo(methodOn(CategoryController.class).getSubCategories(categoryName)).withRel("subCategories"));
    }
}
