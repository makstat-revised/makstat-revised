package com.makstat.demo.controller;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import com.makstat.demo.entity.Category;
import com.makstat.demo.entity.SubCategory;
import com.makstat.demo.repository.CategoryRepository;
import com.makstat.demo.repository.SubCategoryRepository;

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
   
    private final CategoryRepository categoryRepository;
    private final SubCategoryRepository subCategoryRepository;

    CategoryController(CategoryRepository categoryRepository, SubCategoryRepository subCategoryRepository) {
        this.categoryRepository = categoryRepository;
        this.subCategoryRepository = subCategoryRepository;
    }

    // @GetMapping()
    // CollectionModel<EntityModel<HashMap<String, String>>> getCategories() {
    //     List<Category> categories = categoryRepository.findAll();
    //     HashMap<Category, List<SubCategory>> categoryAndSubCategoryMap = new HashMap<>();
    //     categories.forEach(category -> categoryAndSubCategoryMap.put(category, subCategoryRepository.findSubCategoryByCategory(category)));

    //     List<EntityModel<HashMap<String, String>>> 
    //     categoryRepository.findAll();
    //     HashMap<String, String> responseObj = new HashMap<>();

    //     return categories;
    // }

    // @GetMapping("/{categoryName}")
    // EntityModel<HashMap<String, String>> getCategory(@PathVariable String categoryName) {

        // List<SubCategory> subCategories = subCategoryRepository.findSubCategoryByCategoryName(categoryName);
        // HashMap<Category, List<SubCategory>> categoryAndSubCategoryMap = new HashMap<>();
        // categories.forEach(category -> categoryAndSubCategoryMap.put(category, subCategoryRepository.findSubCategoryByCategory(category)));
        // List<EntityModel<HashMap<String, String>>> 
        // categoryRepository.findAll();
        // HashMap<String, String> responseObj = new HashMap<>();
        // return categories;

    //     Category category = categoryRepository.findCategoryByName(categoryName);
    //     List<SubCategory> subCategories = subCategoryRepository.findSubCategoryByCategoryName(categoryName);
    //     HashMap<String, String> responseObj = new HashMap<>();
    //     responseObj.put("name", category.getName());
    //     responseObj.put("subCategories", subCategories.toString());
    //     return EntityModel.of(responseObj,
    //         linkTo(methodOn(CategoryController.class).findCategoryByName(categoryName)).withSelfRel(),
    //         linkTo(methodOn(CategoryController.class).findAll()).withRel("categories"));
    // }

    // @GetMapping("/{categoryName}/subCategories")
    // CollectionModel<EntityModel<HashMap<String, String>>> getSubCategories(String categoryName) {
        // List<SubCategory> subCategories = subCategoryRepository.findSubCategoryByCategoryName(categoryName);
        // List<Map<String, String>> subCategoriesMaps = subCategories.stream()
        //     .map(subCategory -> Map.of("name", subCategory.getName()))
        //     .collect(Collectors.toList());
        // List<EntityModel<Map<String, String>>> responseObj = subCategoriesMaps.stream()
        //     .map(subCategoryMap -> EntityModel.of(subCategoryMap,
        //         linkTo(methodOn(CategoryController.class).getSubCategory(categoryName, subCategory.getName())).withSelfRel()))
        //     .collect(Collectors.toList());
        // return CollectionModel.of(responseObj);
    // }

    @GetMapping("/{categoryName}/subCategories/{subCategoryName}")
    EntityModel<HashMap<String, String>> getSubCategory(@PathVariable String categoryName, @PathVariable String subCategoryName) {
        SubCategory subCategory = subCategoryRepository.findSubCategoryByCategoryNameAndName(categoryName, subCategoryName);
        HashMap<String, String> responseObj = new HashMap<>();
        responseObj.put("name", subCategory.getName());
        // TODO: add link to all subcategories
        return EntityModel.of(responseObj,
            linkTo(methodOn(CategoryController.class).getSubCategory(categoryName, subCategoryName)).withSelfRel());
    }
}
