package com.makstat.demo.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.makstat.demo.entity.CategoryEntity;
import com.makstat.demo.entity.WageDistributionEntity;
import com.makstat.demo.entity.SubCategoryEntity;
import com.makstat.demo.model.WageDistribution;
import com.makstat.demo.model.wageDistribution.Category;
import com.makstat.demo.model.wageDistribution.SubCategory;
import com.makstat.demo.model.wageDistribution.Year;
import com.makstat.demo.model.wageDistribution.WageGroup;
import com.makstat.demo.repository.CategoryEntityRepository;
import com.makstat.demo.repository.WageDistributionEntityRepository;
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
@RequestMapping("/wageDistribution")
public class WageDistributionController {

	private final WageDistributionEntityRepository wageDistributionEntityRepository;
	private final CategoryEntityRepository categoryEntityRepository;
	private final SubCategoryEntityRepository subCategoryEntityRepository;

	WageDistributionController(WageDistributionEntityRepository wageDistributionEntityRepository,
			SubCategoryEntityRepository subCategoryEntityRepository,
			CategoryEntityRepository categoryEntityRepository) {
		this.wageDistributionEntityRepository = wageDistributionEntityRepository;
		this.categoryEntityRepository = categoryEntityRepository;
		this.subCategoryEntityRepository = subCategoryEntityRepository;
	}

	@GetMapping("")
	EntityModel<WageDistribution> getWageDistribution() {
		List<EntityModel<Category>> categoryEntityModels = getCategoryEntities().stream()
				.map(categoryEntity -> getCategory(categoryEntity.getName(), true)).collect(Collectors.toList());
		WageDistribution wageDistributionResource = new WageDistribution(CollectionModel.of(categoryEntityModels));
		return EntityModel.of(wageDistributionResource,
				linkTo(methodOn(WageDistributionController.class).getWageDistribution()).withSelfRel());
	}

	@GetMapping("/{categoryName}")
	EntityModel<Category> getCategory(@PathVariable String categoryName, boolean... selfRelOnly) {
		List<EntityModel<SubCategory>> subCategoryEntityModels = getSubCategoryEntities(categoryName).stream()
				.map(subCategoryEntity -> getSubCategory(categoryName, subCategoryEntity.getName(), true))
				.collect(Collectors.toList());
		Category categoryResource = new Category(categoryName, CollectionModel.of(subCategoryEntityModels));
		if (selfRelOnly != null && selfRelOnly[0] == true)
			return EntityModel.of(categoryResource,
					linkTo(methodOn(WageDistributionController.class).getCategory(categoryName)).withSelfRel(),
					linkTo(methodOn(CategoryController.class).getCategory(categoryName)).withRel("_category"));
		return EntityModel.of(categoryResource,
				linkTo(methodOn(WageDistributionController.class).getCategory(categoryName)).withSelfRel(),
				linkTo(methodOn(WageDistributionController.class).getWageDistribution()).withRel("WageDistribution"),
				linkTo(methodOn(CategoryController.class).getCategory(categoryName)).withRel("_category"));
	}

	@GetMapping("/{categoryName}/{subCategoryName}")
	EntityModel<SubCategory> getSubCategory(@PathVariable String categoryName, @PathVariable String subCategoryName,
			boolean... selfRelOnly) {
		List<WageDistributionEntity> wageDistributionEntities = wageDistributionEntityRepository
				.findWageDistributionBySubCategory(getSubCategoryEntity(categoryName, subCategoryName));
		// if (wageDistributionEntities.isEmpty())
		// throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		List<EntityModel<Year>> yearEntityModels = wageDistributionEntities.stream().distinct()
				.map(wageDistributionEntity -> getYear(categoryName, subCategoryName, wageDistributionEntity.getYear(),
						true))
				.collect(Collectors.toList());
		SubCategory subCategoryResource = new SubCategory(subCategoryName, CollectionModel.of(yearEntityModels));
		if (selfRelOnly != null && selfRelOnly[0] == true)
			return EntityModel.of(subCategoryResource,
					linkTo(methodOn(WageDistributionController.class).getSubCategory(categoryName, subCategoryName))
							.withSelfRel(),
					linkTo(methodOn(CategoryController.class).getSubCategory(categoryName, subCategoryName))
							.withRel("_subCategory"));
		return EntityModel.of(subCategoryResource,
				linkTo(methodOn(WageDistributionController.class).getSubCategory(categoryName, subCategoryName))
						.withSelfRel(),
				linkTo(methodOn(WageDistributionController.class).getCategory(categoryName)).withRel("category"),
				linkTo(methodOn(CategoryController.class).getSubCategory(categoryName, subCategoryName))
						.withRel("_subCategory"));
	}

	@GetMapping("/{categoryName}/{subCategoryName}/{year}")
	EntityModel<Year> getYear(@PathVariable String categoryName, @PathVariable String subCategoryName,
			@PathVariable int year, boolean... selfRelOnly) {
		List<WageDistributionEntity> wageDistributionEntities = wageDistributionEntityRepository
				.findWageDistributionBySubCategoryAndYear(getSubCategoryEntity(categoryName, subCategoryName), year);
		if (wageDistributionEntities.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		List<EntityModel<WageGroup>> wageDistributionEntityModels = wageDistributionEntities.stream()
				.map(wageDistributionEntity -> getWageGroup(categoryName, subCategoryName, year,
						wageDistributionEntity.getWageGroup().getName(), true))
				.collect(Collectors.toList());
		Year yearResource = new Year(year, CollectionModel.of(wageDistributionEntityModels));
		if (selfRelOnly != null && selfRelOnly[0] == true)
			return EntityModel.of(yearResource,
					linkTo(methodOn(WageDistributionController.class).getYear(categoryName, subCategoryName, year))
							.withSelfRel());
		return EntityModel.of(yearResource,
				linkTo(methodOn(WageDistributionController.class).getYear(categoryName, subCategoryName, year))
						.withSelfRel(),
				linkTo(methodOn(WageDistributionController.class).getSubCategory(categoryName, subCategoryName))
						.withRel("subCategory"));
	}

	@GetMapping("/{categoryName}/{subCategoryName}/{year}/{wageGroup}")
	EntityModel<WageGroup> getWageGroup(@PathVariable String categoryName, @PathVariable String subCategoryName,
			@PathVariable int year, @PathVariable String wageGroup, boolean... selfRelOnly) {
		WageDistributionEntity wageDistributionEntity = wageDistributionEntityRepository
				.findWageDistributionBySubCategoryAndYearAndWageGroup(
						getSubCategoryEntity(categoryName, subCategoryName), year, getWageGroupEntity(wageGroup));
		if (wageDistributionEntity == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		WageGroup wageGroupResource = new WageGroup(wageGroup, wageDistributionEntity.getCount());
		if (selfRelOnly != null && selfRelOnly[0] == true)
			return EntityModel.of(wageGroupResource, linkTo(methodOn(WageDistributionController.class)
					.getWageGroup(categoryName, subCategoryName, year, wageGroup)).withSelfRel());
		return EntityModel.of(wageGroupResource,
				linkTo(methodOn(WageDistributionController.class).getWageGroup(categoryName, subCategoryName, year,
						wageGroup)).withSelfRel(),
				linkTo(methodOn(WageDistributionController.class).getYear(categoryName, subCategoryName, year))
						.withRel("year"),
				linkTo(methodOn(CategoryController.class).getSubCategory(categoryName, subCategoryName))
						.withRel("_subCategory"));
	}

	private List<CategoryEntity> getCategoryEntities() {
		List<CategoryEntity> categoryEntities = categoryEntityRepository.findAll();
		return categoryEntities;
	}

	private List<SubCategoryEntity> getSubCategoryEntities(String categoryName) {
		List<SubCategoryEntity> subCategoryEntities = subCategoryEntityRepository
				.findSubCategoryByCategoryName(categoryName);
		if (subCategoryEntities.isEmpty())
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		return subCategoryEntities;
	}

	private SubCategoryEntity getSubCategoryEntity(String categoryName, String subCategoryName) {
		SubCategoryEntity subCategoryEntity = subCategoryEntityRepository
				.findSubCategoryByCategoryNameAndName(categoryName, subCategoryName);
		if (subCategoryEntity == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		return subCategoryEntity;
	}

	private SubCategoryEntity getWageGroupEntity(String wageGroup) {
		SubCategoryEntity wageGroupEntity = subCategoryEntityRepository
				.findSubCategoryByCategoryNameAndName("wageGroup", wageGroup);
		if (wageGroupEntity == null)
			throw new ResponseStatusException(HttpStatus.NOT_FOUND);
		return wageGroupEntity;
	}
}
