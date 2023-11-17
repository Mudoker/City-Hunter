package com.example.android_city_hunter;

import java.util.UUID;

public class Badge {
    private int id;
    private String title;
    private Position badgeLocation;
    private int  experiencePoints;
    private String imageUrl;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Position getBadgeLocation() {
        return badgeLocation;
    }

    public void setBadgeLocation(Position badgeLocation) {
        this.badgeLocation = badgeLocation;
    }

    public double getExperiencePoints() {
        return experiencePoints;
    }

    public void setExperiencePoints(int experiencePoints) {
        this.experiencePoints = experiencePoints;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getTitle() {
        return this.title;
    }
}
