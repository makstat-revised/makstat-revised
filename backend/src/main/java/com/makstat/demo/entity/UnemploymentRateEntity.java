package com.makstat.demo.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "unemployment_rate")
public class UnemploymentRateEntity {

    @Id
    @GeneratedValue
    private Integer id;
    @ManyToOne
    private SubCategoryEntity subCategory;
    @Column(nullable = false)
    private int year;
    private Boolean sex;
    @Column(nullable = false)
    private int rate;

    public UnemploymentRateEntity() {
    }

    public UnemploymentRateEntity(Integer id, SubCategoryEntity subCategory, int year, Boolean sex, int rate) {
        this.id = id;
        this.subCategory = subCategory;
        this.year = year;
        this.sex = sex;
        this.rate = rate;
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

    public Boolean isSex() {
        return this.sex;
    }

    public Boolean getSex() {
        return this.sex;
    }

    public void setSex(Boolean sex) {
        this.sex = sex;
    }

    public int getRate() {
        return this.rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public UnemploymentRateEntity id(Integer id) {
        setId(id);
        return this;
    }

    public UnemploymentRateEntity subCategory(SubCategoryEntity subCategory) {
        setSubCategory(subCategory);
        return this;
    }

    public UnemploymentRateEntity year(int year) {
        setYear(year);
        return this;
    }

    public UnemploymentRateEntity sex(Boolean sex) {
        setSex(sex);
        return this;
    }

    public UnemploymentRateEntity rate(int rate) {
        setRate(rate);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof UnemploymentRateEntity)) {
            return false;
        }
        UnemploymentRateEntity unemploymentRateEntity = (UnemploymentRateEntity) o;
        return Objects.equals(subCategory, unemploymentRateEntity.subCategory) && year == unemploymentRateEntity.year;
    }

    @Override
    public int hashCode() {
        return Objects.hash(subCategory, year);
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", subCategory='" + getSubCategory() + "'" + ", year='" + getYear() + "'"
                + ", sex='" + isSex() + "'" + ", rate='" + getRate() + "'" + "}";
    }
}
