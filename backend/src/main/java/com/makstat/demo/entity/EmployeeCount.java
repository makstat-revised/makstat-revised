package com.makstat.demo.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class EmployeeCount {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private SubCategory subCategory;
    @Column(nullable = false)
    private int year;
    private boolean sex;
    @Column(nullable = false)
    private int count;

    public EmployeeCount() {
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
        if (!(o instanceof EmployeeCount)) {
            return false;
        }
        EmployeeCount employeeCount = (EmployeeCount) o;
        return Objects.equals(id, employeeCount.id) && Objects.equals(subCategory, employeeCount.subCategory)
                && year == employeeCount.year && sex == employeeCount.sex && count == employeeCount.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, subCategory, year, sex, count);
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", subCategory='" + getSubCategory() + "'" + ", year='" + getYear() + "'"
                + ", sex='" + isSex() + "'" + ", count='" + getCount() + "'" + "}";
    }

}
