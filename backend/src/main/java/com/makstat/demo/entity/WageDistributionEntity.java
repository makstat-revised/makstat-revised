package com.makstat.demo.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "wage_distribution")
public class WageDistributionEntity {

    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    private SubCategoryEntity subCategory;
    @Column(nullable = false)
    private int year;
    @ManyToOne
    private SubCategoryEntity wageGroup;
    @Column(nullable = false)
    private int count;

    public WageDistributionEntity() {
    }

    public WageDistributionEntity(Integer id, SubCategoryEntity subCategory, int year, SubCategoryEntity wageGroup, int count) {
        this.id = id;
        this.subCategory = subCategory;
        this.year = year;
        this.wageGroup = wageGroup;
        this.count = count;
    }

    public Integer getId() {
        return this.id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SubCategoryEntity getSubCategory() {
        return this.subCategory;
    }

    public void setSubCategory(SubCategoryEntity subCategory) {
        this.subCategory = subCategory;
    }

    public int getYear() {
        return this.year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public SubCategoryEntity getWageGroup() {
        return this.wageGroup;
    }

    public void setWageGroup(SubCategoryEntity wageGroup) {
        this.wageGroup = wageGroup;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public WageDistributionEntity id(Integer id) {
        setId(id);
        return this;
    }

    public WageDistributionEntity subCategory(SubCategoryEntity subCategory) {
        setSubCategory(subCategory);
        return this;
    }

    public WageDistributionEntity year(int year) {
        setYear(year);
        return this;
    }

    public WageDistributionEntity wageGroup(SubCategoryEntity wageGroup) {
        setWageGroup(wageGroup);
        return this;
    }

    public WageDistributionEntity count(int count) {
        setCount(count);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof WageDistributionEntity)) {
            return false;
        }
        WageDistributionEntity wageDistributionEntity = (WageDistributionEntity) o;
        return Objects.equals(subCategory, wageDistributionEntity.subCategory) && year == wageDistributionEntity.year;
    }

    @Override
    public int hashCode() {
        return Objects.hash(subCategory, year);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", subCategory='" + getSubCategory() + "'" +
            ", year='" + getYear() + "'" +
            ", wageGroup='" + getWageGroup() + "'" +
            ", count='" + getCount() + "'" +
            "}";
    }
}
