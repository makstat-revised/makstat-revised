package com.makstat.demo.entity.pet;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class SubCategory {

    @Id
    private String name;
    @ManyToOne
    private Category category;

    public SubCategory() {
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Category getCategory() {
        return this.category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof SubCategory)) {
            return false;
        }
        SubCategory subCategory = (SubCategory) o;
        return Objects.equals(name, subCategory.name) && Objects.equals(category, subCategory.category);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, category);
    }

    @Override
    public String toString() {
        return "{" + " name='" + getName() + "'" + ", category='" + getCategory() + "'" + "}";
    }

}
