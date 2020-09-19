package com.example.simplefirebaseandroid.data.model;

import java.io.Serializable;

public class Movie implements Serializable {

    private String title;

    private String category;

    private Integer year;

    private String description;

    private Long duration;

    public Movie() {
    }

    public Movie(String title, String category, Integer year, String description, Long duration) {
        this.title = title;
        this.category = category;
        this.year = year;
        this.description = description;
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getYear() {
        return year;
    }

    public void setYear(Integer year) {
        this.year = year;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Long getDuration() {
        return duration;
    }

    public void setDuration(Long duration) {
        this.duration = duration;
    }

}
