package com.example.android_city_hunter;

import java.util.UUID;

public class Badge {
    private String id;
    private Position badgeLocation;
    private double experiencePoints;

    private String imageUrl;

    public Badge(Double badgeLongitude, Double badeLatitude, double experiencePoints, String imageUrl) {
        this.id = UUID.randomUUID().toString();
        this.badgeLocation = new Position(badgeLongitude, badeLatitude);
        this.experiencePoints = experiencePoints;
        this.imageUrl = imageUrl;
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

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
