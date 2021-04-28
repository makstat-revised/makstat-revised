package com.makstat.demo.model.wageDistribution;

import java.util.Objects;

public class WageGroup {
    private String wageGroup;
    private int count;

    public WageGroup() {
    }

    public WageGroup(String wageGroup, int count) {
        this.wageGroup = wageGroup;
        this.count = count;
    }

    public String getWageGroup() {
        return this.wageGroup;
    }

    public void setWageGroup(String wageGroup) {
        this.wageGroup = wageGroup;
    }

    public int getCount() {
        return this.count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public WageGroup wageGroup(String wageGroup) {
        setWageGroup(wageGroup);
        return this;
    }

    public WageGroup count(int count) {
        setCount(count);
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (o == this)
            return true;
        if (!(o instanceof WageGroup)) {
            return false;
        }
        WageGroup wageGroup = (WageGroup) o;
        return Objects.equals(this.wageGroup, wageGroup.wageGroup) && count == wageGroup.count;
    }

    @Override
    public int hashCode() {
        return Objects.hash(wageGroup, count);
    }

    @Override
    public String toString() {
        return "{" +
            " wageGroup='" + getWageGroup() + "'" +
            ", count='" + getCount() + "'" +
            "}";
    }
}
