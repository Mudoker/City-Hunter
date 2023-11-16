package com.example.android_city_hunter;

public class Utility {

    // SingleTon
    private static final Utility UtilityInstance = new Utility();

    private Utility() {
    }

    public static Utility getInstance() {
        return UtilityInstance;
    }

    private static final double NORMAL_WALKING_MET = 3.5;

    public static double calculateCaloriesBurned(double weightKg, double distanceKm, int age, boolean isMale, double heightCm) {
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

    public static double calculateBMI(double weightInKg, double heightInMeters) {
        if (weightInKg <= 0 || heightInMeters <= 0) {
            throw new IllegalArgumentException("Weight and height must be positive values");
        }

        return weightInKg / (heightInMeters * heightInMeters);
    }
}
