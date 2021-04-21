package com.makstat.demo.model;

import java.util.Objects;

public class SubCategory {
    private String name;

    public SubCategory() {
    }

    public SubCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public SubCategory name(String name) {
        setName(name);
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
        return Objects.equals(name, subCategory.name);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(name);
    }

    @Override
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            "}";
    }
}
