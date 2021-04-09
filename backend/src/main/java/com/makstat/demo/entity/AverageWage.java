package com.makstat.demo.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class AverageWage {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private SubCategory subCategory;
    @Column(nullable = false)
    private int year;
    private boolean sex;
    @Column(nullable = false)
    private int wage;

    public AverageWage() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
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

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof AverageWage)) {
            return false;
        }
        AverageWage averageWage = (AverageWage) o;
        return Objects.equals(id, averageWage.id) && Objects.equals(subCategory, averageWage.subCategory)
                && year == averageWage.year && sex == averageWage.sex && wage == averageWage.wage;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, subCategory, year, sex, wage);
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", subCategory='" + getSubCategory() + "'" + ", year='" + getYear() + "'"
                + ", sex='" + isSex() + "'" + ", wage='" + getWage() + "'" + "}";
    }

}
