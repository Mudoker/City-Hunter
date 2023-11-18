package com.example.android_city_hunter;

import android.graphics.drawable.Drawable;

import com.google.android.gms.maps.model.LatLng;

public class Badge {
    private int id;
    private String title;
    private LatLng badgeLat;

    private int  experiencePoints;
    private int imageUrl;

    public Badge(int id, String title, LatLng badgeLat, int experiencePoints, int imageUrl) {
        this.id = id;
        this.title = title;
        this.badgeLat = badgeLat;
        this.experiencePoints = experiencePoints;
        this.imageUrl = imageUrl;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LatLng getBadgeLat() {
        return badgeLat;
    }

    public void setBadgeLat(LatLng badgeLat) {
        this.badgeLat = badgeLat;
    }

    public int getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(int imageUrl) {
        this.imageUrl = imageUrl;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getExperiencePoints() {
        return experiencePoints;
    }

    public void setExperiencePoints(int experiencePoints) {
        this.experiencePoints = experiencePoints;
    }

    public String getTitle() {
        return this.title;
    }
}
