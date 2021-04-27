package com.makstat.demo.model;

import java.util.Objects;

import com.makstat.demo.model.common.Category;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

public class EmployeeCount {
    private CollectionModel<EntityModel<Category>> categories;

    public EmployeeCount() {
    }

    public EmployeeCount(CollectionModel<EntityModel<Category>> categories) {
        this.categories = categories;
    }

    public CollectionModel<EntityModel<Category>> getCategories() {
        return this.categories;
    }

    public void setCategories(CollectionModel<EntityModel<Category>> categories) {
        this.categories = categories;
    }

    public EmployeeCount categories(CollectionModel<EntityModel<Category>> categories) {
        setCategories(categories);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof EmployeeCount)) {
            return false;
        }
        EmployeeCount employeeCount = (EmployeeCount) o;
        return Objects.equals(categories, employeeCount.categories);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(categories);
    }

    @Override
    public String toString() {
        return "{" +
            " categories='" + getCategories() + "'" +
            "}";
    }
}
