package com.example.as1_cityhunter;

import java.util.UUID;

public class Badge {
    private String id;
    private Position badgeLocation;
    private double experiencePoints;
    private String badgeTitle;
    private String badgeDescription;
    private String imageUrl;
    private String category;

    public Badge(Position badgeLocation, double experiencePoints, String badgeTitle, String badgeDescription, String imageUrl, String category) {
        this.id = UUID.randomUUID().toString();
        this.badgeLocation = badgeLocation;
        this.experiencePoints = experiencePoints;
        this.badgeTitle = badgeTitle;
        this.badgeDescription = badgeDescription;
        this.imageUrl = imageUrl;
        this.category = category;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
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

    public void setExperiencePoints(double experiencePoints) {
        this.experiencePoints = experiencePoints;
    }

    public String getBadgeTitle() {
        return badgeTitle;
    }

    public void setBadgeTitle(String badgeTitle) {
        this.badgeTitle = badgeTitle;
    }

    public String getBadgeDescription() {
        return badgeDescription;
    }

    public void setBadgeDescription(String badgeDescription) {
        this.badgeDescription = badgeDescription;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
