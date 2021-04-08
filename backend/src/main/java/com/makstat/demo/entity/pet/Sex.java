package com.makstat.demo.entity.pet;

import java.util.Objects;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Sex {

    @Id
    private String type;

    public Sex() {
    }

    public String getType() {
        return this.type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Sex)) {
            return false;
        }
        Sex sex = (Sex) o;
        return Objects.equals(type, sex.type);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(type);
    }

    @Override
    public String toString() {
        return "{" + " type='" + getType() + "'" + "}";
    }

}
