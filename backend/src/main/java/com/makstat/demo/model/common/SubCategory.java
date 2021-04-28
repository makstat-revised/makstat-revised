package com.makstat.demo.model.common;

import java.util.Objects;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

public class SubCategory {
    private String subCategory;
    private CollectionModel<EntityModel<Year>> years;

    public SubCategory() {
    }

    public SubCategory(String subCategory, CollectionModel<EntityModel<Year>> years) {
        this.subCategory = subCategory;
        this.years = years;
    }

    public String getSubCategory() {
        return this.subCategory;
    }

    public void setSubCategory(String subCategory) {
        this.subCategory = subCategory;
    }

    public CollectionModel<EntityModel<Year>> getYears() {
        return this.years;
    }

    public void setYears(CollectionModel<EntityModel<Year>> years) {
        this.years = years;
    }

    public SubCategory subCategory(String subCategory) {
        setSubCategory(subCategory);
        return this;
    }

    public SubCategory years(CollectionModel<EntityModel<Year>> years) {
        setYears(years);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof SubCategory)) {
            return false;
        }
        SubCategory subCategory = (SubCategory) o;
        return Objects.equals(this.subCategory, subCategory.subCategory) && Objects.equals(years, subCategory.years);
    }

    @Override
    public int hashCode() {
        return Objects.hash(subCategory, years);
    }

    @Override
    public String toString() {
        return "{" +
            " subCategory='" + getSubCategory() + "'" +
            ", years='" + getYears() + "'" +
            "}";
    }
}
