package com.makstat.demo.model.wageDistribution;

import java.util.Objects;

import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;

public class Year {
    private int year;
    private CollectionModel<EntityModel<WageGroup>> wageGroups;

    public Year() {
    }

    public Year(int year, CollectionModel<EntityModel<WageGroup>> wageGroups) {
        this.year = year;
        this.wageGroups = wageGroups;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public CollectionModel<EntityModel<WageGroup>> getWageGroups() {
        return this.wageGroups;
    }

    public void setWageGroups(CollectionModel<EntityModel<WageGroup>> wageGroups) {
        this.wageGroups = wageGroups;
    }

    public Year year(int year) {
        setYear(year);
        return this;
    }

    public Year wageGroups(CollectionModel<EntityModel<WageGroup>> wageGroups) {
        setWageGroups(wageGroups);
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
        return this.year == year.year && Objects.equals(wageGroups, year.wageGroups);
    }

    @Override
    public int hashCode() {
        return Objects.hash(year, wageGroups);
    }

    @Override
    public String toString() {
        return "{" +
            " year='" + getYear() + "'" +
            ", wageGroups='" + getWageGroups() + "'" +
            "}";
    }
}
