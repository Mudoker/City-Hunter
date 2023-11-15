package com.example.android_city_hunter;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

public class RegistrationForm extends AppCompatActivity {
    private EditText edtFullName, edtWeight, edtHeight;
    private Spinner spnAge;
    private RadioButton radMale, radFemale;
    private CardView cardAvaMale;
    private CardView cardAvaFemale;
    private String profileImage = "ava_boy", gender = "Male";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.form_user_info);

        // Initialize views
        edtFullName = findViewById(R.id.edt_fullName);
        CardView cardSubmit = findViewById(R.id.cardSubmit);
        cardAvaMale = findViewById(R.id.card_avatar_male);
        cardAvaFemale = findViewById(R.id.card_avatar_female);
        radMale = findViewById(R.id.radio_gender_male);
        radFemale = findViewById(R.id.radio_gender_female);
        spnAge = findViewById(R.id.spn_age);
        edtWeight = findViewById(R.id.edt_weight);
        edtHeight = findViewById(R.id.edt_height);

        // Avatar Selection
        cardAvaMale.setOnClickListener(v -> updateAvatar("ava_boy", cardAvaMale, cardAvaFemale));
        cardAvaFemale.setOnClickListener(v -> updateAvatar("ava_girl", cardAvaFemale, cardAvaMale));

        // Gender selection
        radMale.setOnClickListener(v -> updateGender("Male", radMale, radFemale));
        radFemale.setOnClickListener(v -> updateGender("Female", radFemale, radMale));

        // Age list
        setupAgeSpinner();

        // Submit form
        cardSubmit.setOnClickListener(v -> handleSubmit());
    }

    private void updateAvatar(String image, CardView selected, CardView unselected) {
        profileImage = image;
        selected.setCardBackgroundColor(ContextCompat.getColor(this, R.color.light_green));
        unselected.setCardBackgroundColor(ContextCompat.getColor(this, R.color.gray));
    }

    private void updateGender(String selectedGender, RadioButton selected, RadioButton unselected) {
        gender = selectedGender;
        selected.setChecked(true);
        unselected.setChecked(false);
    }

    private void setupAgeSpinner() {
        ArrayList<Integer> ageList = new ArrayList<>();
        for (int i = 1; i < 100; i++) {
            ageList.add(i);
        }

        ArrayAdapter<Integer> ageAdapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, ageList);
        spnAge.setAdapter(ageAdapter);
    }

    private void handleSubmit() {
        String fullName = edtFullName.getText().toString();
        String heightStr = edtHeight.getText().toString();
        String weightStr = edtWeight.getText().toString();

        if (fullName.isEmpty() || heightStr.isEmpty() || weightStr.isEmpty()) {
            makeToast("FullName, Height, and Weight cannot be empty");
        } else {
            try {
                int height = Integer.parseInt(heightStr);
                int weight = Integer.parseInt(weightStr);

                if (height < 100 || height >= 250) {
                    makeToast("Height should be between 100 and 249");
                } else if (weight < 30 || weight >= 500) {
                    makeToast("Weight should be between 30 and 499");
                } else {
                    makeToast(fullName + spnAge.getSelectedItem().toString() + profileImage + gender + heightStr + weightStr);
                }
            } catch (NumberFormatException e) {
                makeToast("Invalid numeric input for height or weight");
            }
        }
    }

    private void makeToast(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}
