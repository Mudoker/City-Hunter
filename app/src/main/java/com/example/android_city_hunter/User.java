package com.example.android_city_hunter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class User {
    public static User CURRENT_USER = new User();
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
    private double totalCaloriesBurned;
    private ArrayList<Integer> badges = new ArrayList<>();
    private int level; // User's level in the walking app
    private double experience; // User's experience points (exp)

    private Boolean isFirstTimeLogin;

    public  User() {

    }
    public User(String username, String password) {
        this.username = username;
        this.password = password;
        isFirstTimeLogin = true;
        this.badges.add(1);
        this.badges.add(2);
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
                ", totalCaloriesBurned=" + totalCaloriesBurned +
                ", level=" + level +
                ", experience=" + experience +
                ", isFirstTimeLogin=" + isFirstTimeLogin +
                ", badge=" + Arrays.toString(badges.toArray());
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

    public double getTotalCaloriesBurned() {
        return totalCaloriesBurned;
    }

    public void setTotalCaloriesBurned(double totalCaloriesBurned) {
        this.totalCaloriesBurned = totalCaloriesBurned;
    }

    public ArrayList<Integer> getBadges() {
        return this.badges;
    }

    public void setBadges(ArrayList<Integer> badges) {
        this.badges = badges;
    }

    public void addBadges( int badgeId) {
        this.badges.add(badgeId);
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
                    case "totalCaloriesBurned":
                        user.setTotalCaloriesBurned(Double.parseDouble(value));
                        break;
                    case "level":
                        user.setLevel(Integer.parseInt(value));
                        break;
                    case "experience":
                        user.setExperience(Double.parseDouble(value));
                        break;
                    case "isFirstTimeLogin":
                        user.setFirstTimeLogin(Boolean.parseBoolean(value));
                        break;
                    case "badge":
                        ArrayList<Integer> restoredList = new ArrayList<>();
                        String[] elements = value.substring(1, value.length() - 1).split(", ");

                        for (String element : elements) {
                            if (Objects.equals(element, "")) {
                                continue;
                            }
                            restoredList.add(Integer.parseInt(element));
                        }

                        user.setBadges(restoredList);
                        break;
                }
            }
        }
        return user;
    }
}
