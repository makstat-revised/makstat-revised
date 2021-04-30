package com.makstat.demo.model;

import java.util.Objects;

import com.makstat.demo.model.wageDistribution.Category;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

public class WageDistribution {
    private CollectionModel<EntityModel<Category>> categories;

    public WageDistribution() {
    }

    public WageDistribution(CollectionModel<EntityModel<Category>> categories) {
        this.categories = categories;
    }

    public CollectionModel<EntityModel<Category>> getCategories() {
        return this.categories;
    }

    public void setCategories(CollectionModel<EntityModel<Category>> categories) {
        this.categories = categories;
    }

    public WageDistribution categories(CollectionModel<EntityModel<Category>> categories) {
        setCategories(categories);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof WageDistribution)) {
            return false;
        }
        WageDistribution wageDistribution = (WageDistribution) o;
        return Objects.equals(categories, wageDistribution.categories);
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
