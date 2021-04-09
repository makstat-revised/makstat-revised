package com.makstat.demo.entity;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class UnemploymentRate {

    @Id
    @GeneratedValue
    private Long id;
    @ManyToOne
    private SubCategory subCategory;
    @Column(nullable = false)
    private int year;
    private boolean sex;
    @Column(nullable = false)
    private int rate;

    public UnemploymentRate() {
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

    public int getRate() {
        return this.rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof UnemploymentRate)) {
            return false;
        }
        UnemploymentRate unemploymentRate = (UnemploymentRate) o;
        return Objects.equals(id, unemploymentRate.id) && Objects.equals(subCategory, unemploymentRate.subCategory)
                && year == unemploymentRate.year && sex == unemploymentRate.sex && rate == unemploymentRate.rate;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, subCategory, year, sex, rate);
    }

    @Override
    public String toString() {
        return "{" + " id='" + getId() + "'" + ", subCategory='" + getSubCategory() + "'" + ", year='" + getYear() + "'"
                + ", sex='" + isSex() + "'" + ", rate='" + getRate() + "'" + "}";
    }

}
