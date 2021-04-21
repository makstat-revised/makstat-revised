package com.makstat.demo.model;

import java.util.List;
import java.util.Objects;

import com.makstat.demo.entity.SubCategoryEntity;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

public class Category {
    private String name;
    private CollectionModel<EntityModel<SubCategory>> subCategories;

    public Category() {
    }

    public Category(String name, CollectionModel<EntityModel<SubCategory>> subCategories) {
        this.name = name;
        this.subCategories = subCategories;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CollectionModel<EntityModel<SubCategory>> getSubCategories() {
        return this.subCategories;
    }

    public void setSubCategories(CollectionModel<EntityModel<SubCategory>> subCategories) {
        this.subCategories = subCategories;
    }

    public Category name(String name) {
        setName(name);
        return this;
    }

    public Category subCategories(CollectionModel<EntityModel<SubCategory>> subCategories) {
        setSubCategories(subCategories);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Category)) {
            return false;
        }
        Category category = (Category) o;
        return Objects.equals(name, category.name) && Objects.equals(subCategories, category.subCategories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, subCategories);
    }

    @Override
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            ", subCategories='" + getSubCategories() + "'" +
            "}";
    }
}
