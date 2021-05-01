package com.makstat.demo.model.common;

import java.util.Objects;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class Gender {
    private String gender;
    private int count;

    public static Boolean toBoolean(String gender) {
        if (gender == null)
            return null;
        else if (gender.equals("male"))
            return false;
        else if (gender.equals("female"))
            return true;
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }

    public static String toString(Boolean gender) {
        if (gender == null)
            return null;
        else if (gender == true)
            return "female";
        else
            return "male";
    }

    public Gender() {
    }

    public Gender(String gender, int count) {
        this.gender = gender;
        this.count = count;
    }

    public String getGender() {
        return this.gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Gender gender(String gender) {
        setGender(gender);
        return this;
    }

    public Gender count(int count) {
        setCount(count);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof Gender)) {
            return false;
        }
        Gender gender = (Gender) o;
        return Objects.equals(this.gender, gender.gender) && count == gender.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(gender, count);
    }

    @Override
    public String toString() {
        return "{" + " gender='" + getGender() + "'" + ", count='" + getCount() + "'" + "}";
    }
}
