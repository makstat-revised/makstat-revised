package com.makstat.demo.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "average_wage")
public class AverageWageEntity {

    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    private SubCategoryEntity subCategory;
    @Column(nullable = false)
    private int year;
    private boolean sex;
    @Column(nullable = false)
    private int wage;

    public AverageWageEntity() {
    }

    public AverageWageEntity(Integer id, SubCategoryEntity subCategory, int year, boolean sex, int wage) {
        this.id = id;
        this.subCategory = subCategory;
        this.year = year;
        this.sex = sex;
        this.wage = wage;
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

    public boolean isSex() {
        return this.sex;
    }

    public boolean getSex() {
        return this.sex;
    }

    public void setSex(boolean sex) {
        this.sex = sex;
    }

    public int getWage() {
        return this.wage;
    }

    public void setWage(int wage) {
        this.wage = wage;
    }

    public AverageWageEntity id(Integer id) {
        setId(id);
        return this;
    }

    public AverageWageEntity subCategory(SubCategoryEntity subCategory) {
        setSubCategory(subCategory);
        return this;
    }

    public AverageWageEntity year(int year) {
        setYear(year);
        return this;
    }

    public AverageWageEntity sex(boolean sex) {
        setSex(sex);
        return this;
    }

    public AverageWageEntity wage(int wage) {
        setWage(wage);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof AverageWageEntity)) {
            return false;
        }
        AverageWageEntity averageWageEntity = (AverageWageEntity) o;
        return Objects.equals(subCategory, averageWageEntity.subCategory) && year == averageWageEntity.year;
    }

    @Override
    public int hashCode() {
        return Objects.hash(subCategory, year);
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", subCategory='" + getSubCategory() + "'" + ", year='" + getYear() + "'"
                + ", sex='" + isSex() + "'" + ", wage='" + getWage() + "'" + "}";
    }
}
