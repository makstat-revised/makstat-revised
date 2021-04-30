package com.makstat.demo.model;

import java.util.Objects;

import com.makstat.demo.model.common.Category;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

public class AverageWage {
    private CollectionModel<EntityModel<Category>> categories;

    public AverageWage() {
    }

    public AverageWage(CollectionModel<EntityModel<Category>> categories) {
        this.categories = categories;
    }

    public CollectionModel<EntityModel<Category>> getCategories() {
        return this.categories;
    }

    public void setCategories(CollectionModel<EntityModel<Category>> categories) {
        this.categories = categories;
    }

    public AverageWage categories(CollectionModel<EntityModel<Category>> categories) {
        setCategories(categories);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof AverageWage)) {
            return false;
        }
        AverageWage averageNetWage = (AverageWage) o;
        return Objects.equals(categories, averageNetWage.categories);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(categories);
    }

    @Override
    public String toString() {
        return "{" + " categories='" + getCategories() + "'" + "}";
    }
}
