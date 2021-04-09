package com.makstat.demo.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class WageDistribution {

    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    private SubCategory subCategory;
    @Column(nullable = false)
    private int year;
    @ManyToOne
    private SubCategory wageGroup;
    @Column(nullable = false)
    private int count;

    public WageDistribution() {
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SubCategory getSubCategory() {
        return this.subCategory;
    }

    public void setSubCategory(SubCategory subCategory) {
        this.subCategory = subCategory;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public SubCategory getWageGroup() {
        return this.wageGroup;
    }

    public void setWageGroup(SubCategory wageGroup) {
        this.wageGroup = wageGroup;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof WageDistribution)) {
            return false;
        }
        WageDistribution wageDistribution = (WageDistribution) o;
        return Objects.equals(id, wageDistribution.id) && Objects.equals(subCategory, wageDistribution.subCategory)
                && year == wageDistribution.year && Objects.equals(wageGroup, wageDistribution.wageGroup)
                && count == wageDistribution.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, subCategory, year, wageGroup, count);
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", subCategory='" + getSubCategory() + "'" + ", year='" + getYear() + "'"
                + ", wageGroup='" + getWageGroup() + "'" + ", count='" + getCount() + "'" + "}";
    }

}
