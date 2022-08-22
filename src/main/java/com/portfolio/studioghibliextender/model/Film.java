package com.portfolio.studioghibliextender.model;

public class Film {

    private String title;
    private String original_title;
    private String original_title_romanised;
    private String description;
    private String director;
    private String producer;
    private String release_date;
    private Integer running_time;
    private Integer rt_score;
    private String image;

    public String getTitle() {
        return title;
    }

    public String getOriginal_title() {
        return original_title;
    }

    public String getOriginal_title_romanised() {
        return original_title_romanised;
    }

    public String getDescription() {
        return description;
    }

    public String getDirector() {
        return director;
    }

    public String getProducer() {
        return producer;
    }

    public String getRelease_date() {
        return release_date;
    }

    public Integer getRunning_time() {
        return running_time;
    }

    public Integer getRt_score() {
        return rt_score;
    }

    public String getImage() {
        return image;
    }
}