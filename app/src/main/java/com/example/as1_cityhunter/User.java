package com.example.as1_cityhunter;

import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User {
    private String username;
    private String password;

    private String profileImage;
    private String fullName;
    private int age;
    private String gender;
    private double heightInCentimeters;
    private double weightInKilograms;
    private int totalSteps;
    private double totalOverallDistanceInKilometers;
    private double totalTodayDistanceInKilometers;
    private int totalTodaySteps;
    private double totalCaloriesBurned;
    private Map<String, Date> badges = new HashMap<>(); // Using a Map to store badges and their unlock dates
    private int level; // User's level in the walking app
    private double experience; // User's experience points (exp)

    private Position currentPosition = new Position(0,0);

    private Boolean isFirstTimeLogin;

    public  User() {

    }
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        isFirstTimeLogin = true;
    }

    public String getUsername() {
        return username;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getHeightInCentimeters() {
        return heightInCentimeters;
    }

    @Override
    public String toString() {
        return
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", profileImage='" + profileImage + '\'' +
                ", fullName='" + fullName + '\'' +
                ", age=" + age +
                ", gender='" + gender + '\'' +
                ", heightInCentimeters=" + heightInCentimeters +
                ", weightInKilograms=" + weightInKilograms +
                ", totalSteps=" + totalSteps +
                ", totalOverallDistanceInKilometers=" + totalOverallDistanceInKilometers +
                ", totalTodayDistanceInKilometers=" + totalTodayDistanceInKilometers +
                ", totalTodaySteps=" + totalTodaySteps +
                ", totalCaloriesBurned=" + totalCaloriesBurned +
                ", level=" + level +
                ", experience=" + experience +
                ", currentPosition=" + currentPosition +
                ", isFirstTimeLogin=" + isFirstTimeLogin;
    }

    public void setHeightInCentimeters(double heightInCentimeters) {
        this.heightInCentimeters = heightInCentimeters;
    }

    public double getWeightInKilograms() {
        return weightInKilograms;
    }

    public void setWeightInKilograms(double weightInKilograms) {
        this.weightInKilograms = weightInKilograms;
    }

    public int getTotalSteps() {
        return totalSteps;
    }

    public void setTotalSteps(int totalSteps) {
        this.totalSteps = totalSteps;
    }

    public double getTotalOverallDistanceInKilometers() {
        return totalOverallDistanceInKilometers;
    }

    public void setTotalOverallDistanceInKilometers(double totalOverallDistanceInKilometers) {
        this.totalOverallDistanceInKilometers = totalOverallDistanceInKilometers;
    }

    public double getTotalTodayDistanceInKilometers() {
        return totalTodayDistanceInKilometers;
    }

    public void setTotalTodayDistanceInKilometers(double totalTodayDistanceInKilometers) {
        this.totalTodayDistanceInKilometers = totalTodayDistanceInKilometers;
    }

    public int getTotalTodaySteps() {
        return totalTodaySteps;
    }

    public void setTotalTodaySteps(int totalTodaySteps) {
        this.totalTodaySteps = totalTodaySteps;
    }

    public double getTotalCaloriesBurned() {
        return totalCaloriesBurned;
    }

    public void setTotalCaloriesBurned(double totalCaloriesBurned) {
        this.totalCaloriesBurned = totalCaloriesBurned;
    }

    public Map<String, Date> getBadges() {
        return badges;
    }

    public void setBadges(Map<String, Date> badges) {
        this.badges = badges;
    }

    public void setCurrentPosition(Position currentPosition) {
        this.currentPosition = currentPosition;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public double getExperience() {
        return experience;
    }

    public void setExperience(double experience) {
        this.experience = experience;
    }

    public Position getCurrentPosition() {
        return currentPosition;
    }

    public void setCurrentPosition(double longitude, double latitude) {
        this.currentPosition = new Position(longitude, latitude);
    }

    public Boolean getFirstTimeLogin() {
        return isFirstTimeLogin;
    }

    public void setFirstTimeLogin(Boolean firstTimeLogin) {
        isFirstTimeLogin = firstTimeLogin;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public static User fromString(String str) {
        String[] parts = str.split(", ");
        System.out.println(Arrays.toString(parts));
        User user = new User();

        for (String part : parts) {
            String[] keyValue = part.split("=");

            if (keyValue.length == 2) {
                String attribute = keyValue[0];
                String value = keyValue[1];
                switch (attribute) {
                    case "username":
                        user.setUsername(value.replace("'", ""));
                        break;
                    case "password":
                        user.setPassword(value.replace("'", ""));
                        break;
                    case "profileImage":
                        user.setProfileImage(value.replace("'", ""));
                        break;
                    case "fullName":
                        user.setFullName(value.replace("'", ""));
                        break;
                    case "age":
                        user.setAge(Integer.parseInt(value.replace("'", "")));
                        break;
                    case "gender":
                        user.setGender(value.replace("'", ""));
                        break;
                    case "heightInCentimeters":
                        user.setHeightInCentimeters(Double.parseDouble(value));
                        break;
                    case "weightInKilograms":
                        user.setWeightInKilograms(Double.parseDouble(value));
                        break;
                    case "totalSteps":
                        user.setTotalSteps(Integer.parseInt(value));
                        break;
                    case "totalOverallDistanceInKilometers":
                        user.setTotalOverallDistanceInKilometers(Double.parseDouble(value));
                        break;
                    case "totalTodayDistanceInKilometers":
                        user.setTotalTodayDistanceInKilometers(Double.parseDouble(value));
                        break;
                    case "totalTodaySteps":
                        user.setTotalTodaySteps(Integer.parseInt(value));
                        break;
                    case "totalCaloriesBurned":
                        user.setTotalCaloriesBurned(Double.parseDouble(value));
                        break;
                    case "level":
                        user.setLevel(Integer.parseInt(value));
                        break;
                    case "experience":
                        user.setExperience(Double.parseDouble(value));
                        break;
                    case "currentPosition":
                        if (!value.equals("null")) {
                            int index = value.indexOf(",");
                            if (index != -1 && index < value.length() - 1) {
                                String positionValue = value.substring(index + 1); // Extracting the value part
                                String[] positionParts = positionValue.split(", ");

                                if (positionParts.length == 2) {
                                    double longitudeValue = Double.parseDouble(positionParts[0]);
                                    double latitudeValue = Double.parseDouble(positionParts[1]);
                                    user.setCurrentPosition(new Position(longitudeValue, latitudeValue));
                                }
                            }
                        }
                        break;
                    case "isFirstTimeLogin":
                        user.setFirstTimeLogin(Boolean.parseBoolean(value));
                        break;
                }
            }
        }
        return user;
    }
}
