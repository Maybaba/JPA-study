package com.jpastudy.chap06_querydsl.dto;


import java.util.Objects;

public class GroupAverageAgeDto {
    private String groupName;
    private double averageAge;

    public GroupAverageAgeDto(String groupName, double averageAge) {
        this.groupName = groupName;
        this.averageAge = averageAge;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public double getAverageAge() {
        return averageAge;
    }

    public void setAverageAge(double averageAge) {
        this.averageAge = averageAge;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        GroupAverageAgeDto that = (GroupAverageAgeDto) o;
        return Double.compare(averageAge, that.averageAge) == 0 && Objects.equals(groupName, that.groupName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(groupName, averageAge);
    }
}
