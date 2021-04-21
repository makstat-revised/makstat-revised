package com.makstat.demo.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "employee_count")
public class EmployeeCountEntity {

    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    private SubCategoryEntity subCategory;
    @Column(nullable = false)
    private int year;
    private boolean sex;
    @Column(nullable = false)
    private int count;

    public EmployeeCountEntity() {
    }

    public EmployeeCountEntity(Integer id, SubCategoryEntity subCategory, int year, boolean sex, int count) {
        this.id = id;
        this.subCategory = subCategory;
        this.year = year;
        this.sex = sex;
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

    public EmployeeCountEntity id(Integer id) {
        setId(id);
        return this;
    }

    public EmployeeCountEntity subCategory(SubCategoryEntity subCategory) {
        setSubCategory(subCategory);
        return this;
    }

    public EmployeeCountEntity year(int year) {
        setYear(year);
        return this;
    }

    public EmployeeCountEntity sex(boolean sex) {
        setSex(sex);
        return this;
    }

    public EmployeeCountEntity count(int count) {
        setCount(count);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof EmployeeCountEntity)) {
            return false;
        }
        EmployeeCountEntity employeeCountEntity = (EmployeeCountEntity) o;
        return Objects.equals(id, employeeCountEntity.id) && Objects.equals(subCategory, employeeCountEntity.subCategory) && year == employeeCountEntity.year && sex == employeeCountEntity.sex && count == employeeCountEntity.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, subCategory, year, sex, count);
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", subCategory='" + getSubCategory() + "'" +
            ", year='" + getYear() + "'" +
            ", sex='" + isSex() + "'" +
            ", count='" + getCount() + "'" +
            "}";
    }
}
