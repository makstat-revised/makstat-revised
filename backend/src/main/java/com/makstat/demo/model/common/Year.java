package com.makstat.demo.model.common;

import java.util.Objects;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

public class Year {
    private int year;
    private CollectionModel<EntityModel<Gender>> genders;

    public Year() {
    }

    public Year(int year, CollectionModel<EntityModel<Gender>> genders) {
        this.year = year;
        this.genders = genders;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public CollectionModel<EntityModel<Gender>> getGenders() {
        return this.genders;
    }

    public void setGenders(CollectionModel<EntityModel<Gender>> genders) {
        this.genders = genders;
    }

    public Year year(int year) {
        setYear(year);
        return this;
    }

    public Year genders(CollectionModel<EntityModel<Gender>> genders) {
        setGenders(genders);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Year)) {
            return false;
        }
        Year year = (Year) o;
        return this.year == year.year && Objects.equals(genders, year.genders);
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, genders);
    }

    @Override
    public String toString() {
        return "{" +
            " year='" + getYear() + "'" +
            ", genders='" + getGenders() + "'" +
            "}";
    }
}
