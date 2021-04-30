package com.makstat.demo.model;

import java.util.Objects;

import com.makstat.demo.model.common.Category;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

public class UnemploymentRate {
    private CollectionModel<EntityModel<Category>> categories;

    public UnemploymentRate() {
    }

    public UnemploymentRate(CollectionModel<EntityModel<Category>> categories) {
        this.categories = categories;
    }

    public CollectionModel<EntityModel<Category>> getCategories() {
        return this.categories;
    }

    public void setCategories(CollectionModel<EntityModel<Category>> categories) {
        this.categories = categories;
    }

    public UnemploymentRate categories(CollectionModel<EntityModel<Category>> categories) {
        setCategories(categories);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof UnemploymentRate)) {
            return false;
        }
        UnemploymentRate unemploymentRate = (UnemploymentRate) o;
        return Objects.equals(categories, unemploymentRate.categories);
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
