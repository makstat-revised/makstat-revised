package com.makstat.demo.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "category")
public class CategoryEntity {

    @Id
    private Integer id;
    @Column(unique = true)
    private String name;

    public CategoryEntity() {
    }

    public CategoryEntity(Integer id, String name) {
        this.id = id;
        this.name = name;
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

    public CategoryEntity id(Integer id) {
        setId(id);
        return this;
    }

    public CategoryEntity name(String name) {
        setName(name);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof CategoryEntity)) {
            return false;
        }
        CategoryEntity categoryEntity = (CategoryEntity) o;
        return Objects.equals(id, categoryEntity.id) && Objects.equals(name, categoryEntity.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", name='" + getName() + "'" + "}";
    }
}
