package com.example.android_city_hunter;

import java.nio.DoubleBuffer;
import java.text.DecimalFormat;
import java.util.Objects;

public class Utility {

    // SingleTon
    private static final Utility UtilityInstance = new Utility();

    private Utility() {
    }

    public static Utility getInstance() {
        return UtilityInstance;
    }

    private static final double NORMAL_WALKING_MET = 3.5;

    public static double calculateCaloriesBurned(double weightKg, double distanceKm, int age, String gender, double heightCm) {
        boolean isMale = Objects.equals(gender, "male");

        double caloriesBurned = (weightKg * 0.035 * distanceKm / 1.6) * NORMAL_WALKING_MET;

        // Adjust for age (optional)
        caloriesBurned *= ageFactor(age, isMale, weightKg, heightCm);

        return caloriesBurned;
    }

    private static double calculateBMR(int age, boolean isMale, double weightKg, double heightCm) {
        if (isMale) {
            return 88.362 + (13.397 * weightKg) + (4.799 * heightCm) - (5.677 * age);
        } else {
            return 447.593 + (9.247 * weightKg) + (3.098 * heightCm) - (4.330 * age);
        }
    }

    private static double ageFactor(int age, boolean isMale, double weightKg, double heightCm) {
        double bmr = calculateBMR(age, isMale, weightKg, heightCm);
        return bmr / 2000.0; // Use BMR as a factor
    }

    public static double calculateBMI(double weightInKg, double heightInCentimetres) {
        if (weightInKg <= 0 || heightInCentimetres <= 0) {
            throw new IllegalArgumentException("Weight and height must be positive values");
        }

        double heightInMeters = heightInCentimetres / 100;

        // Calculate BMI
        double bmi = weightInKg / (heightInMeters * heightInMeters);

//        // Format the result to one decimal place
        DecimalFormat df = new DecimalFormat("#.#");

        return Double.parseDouble(df.format(bmi).replace(",", "."));
    }

    public static boolean updateUserExperience(double exp, User user) {
        double currentExp = 0;
        currentExp += user.getExperience() + exp;

        user.setExperience(currentExp);

        // +1 level for every 50 exp
        user.setLevel((int) (currentExp % 50));
        return true;
    }

    public static String convertLevelToTitle(int level) {
        if (level < 0) {
            return "";
        } else if (level <= 3) {
            return "Newbie";
        } else if (level <= 6) {
            return "Pro";
        } else {
            return "Professional";
        }
    }
}
