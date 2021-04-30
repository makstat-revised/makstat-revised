package com.makstat.demo.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sub_category")
public class SubCategoryEntity {

    @Id
    @GeneratedValue
    private Integer id;
    private String name;
    @ManyToOne
    private CategoryEntity category;

    public SubCategoryEntity() {
    }

    public SubCategoryEntity(Integer id, String name, CategoryEntity category) {
        this.id = id;
        this.name = name;
        this.category = category;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public CategoryEntity getCategory() {
        return this.category;
    }

    public void setCategory(CategoryEntity category) {
        this.category = category;
    }

    public SubCategoryEntity id(Integer id) {
        setId(id);
        return this;
    }

    public SubCategoryEntity name(String name) {
        setName(name);
        return this;
    }

    public SubCategoryEntity category(CategoryEntity category) {
        setCategory(category);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof SubCategoryEntity)) {
            return false;
        }
        SubCategoryEntity subCategoryEntity = (SubCategoryEntity) o;
        return Objects.equals(id, subCategoryEntity.id) && Objects.equals(name, subCategoryEntity.name)
                && Objects.equals(category, subCategoryEntity.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, category);
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", name='" + getName() + "'" + ", category='" + getCategory() + "'"
                + "}";
    }
}
