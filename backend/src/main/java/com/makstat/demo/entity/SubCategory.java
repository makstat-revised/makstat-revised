package com.makstat.demo.entity;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class SubCategory {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
    @ManyToOne
    private Category category;

    public SubCategory() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
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
        return Objects.equals(id, subCategory.id) && Objects.equals(name, subCategory.name)
                && Objects.equals(category, subCategory.category);
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
