package com.makstat.demo.model.common;

import java.util.Objects;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

public class Category {
    private String category;
    private CollectionModel<EntityModel<SubCategory>> subCategories;

    public Category() {
    }

    public Category(String category, CollectionModel<EntityModel<SubCategory>> subCategories) {
        this.category = category;
        this.subCategories = subCategories;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public CollectionModel<EntityModel<SubCategory>> getSubCategories() {
        return this.subCategories;
    }

    public void setSubCategories(CollectionModel<EntityModel<SubCategory>> subCategories) {
        this.subCategories = subCategories;
    }

    public Category category(String category) {
        setCategory(category);
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
        return Objects.equals(category, category.category) && Objects.equals(subCategories, category.subCategories);
    }

    @Override
    public int hashCode() {
        return Objects.hash(category, subCategories);
    }

    @Override
    public String toString() {
        return "{" +
            " category='" + getCategory() + "'" +
            ", subCategories='" + getSubCategories() + "'" +
            "}";
    }
}
