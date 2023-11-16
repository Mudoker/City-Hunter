package com.example.android_city_hunter.fragment;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.Fragment;

import com.example.android_city_hunter.MainActivity;
import com.example.android_city_hunter.R;
import com.example.android_city_hunter.User;
import com.example.android_city_hunter.Utility;

import java.util.ArrayList;
import java.util.Objects;

public class HomeFragment extends Fragment {

    CardView mustTryRoute, globalRunningDay;

    final String GLOBAL_RUNNING_DAY_WEBSITE = "https://globalrunningday.org/";
    final String MUST_TRY_ROUTE_WEBSITE = "https://vietnam.travel/things-to-do/10-unique-marathons-vietnam";

    TextView userName, rank, level, caloriesBurnt, totalSteps, totalDistance, BMI;

    ImageView profileImage;

    HorizontalScrollView horizontalScrollView;

    LinearLayout achievements;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_home, container, false);

        mustTryRoute = rootView.findViewById(R.id.home_card_must_try_route);
        globalRunningDay = rootView.findViewById(R.id.home_card_global_running_day);
        userName = rootView.findViewById(R.id.home_user_username);
        rank = rootView.findViewById(R.id.home_user_rank);
        level = rootView.findViewById(R.id.home_user_level);
        profileImage = rootView.findViewById(R.id.home_user_ava);
        totalSteps = rootView.findViewById(R.id.home_txt_step_counter);
        caloriesBurnt = rootView.findViewById(R.id.home_txt_user_calories_burnt);
        totalDistance = rootView.findViewById(R.id.home_txt_distance_counter);
        BMI = rootView.findViewById(R.id.home_txt_user_bmi);
        horizontalScrollView = rootView.findViewById(R.id.home_scroll_view_achievement);
        achievements = rootView.findViewById(R.id.home_achievement_group);
        achievements.setOrientation(LinearLayout.HORIZONTAL);

        ArrayList<Integer> achivementList = User.CURRENT_USER.getBadges();

        for (int i = 0; i < achivementList.size(); i++) {
            ImageView badge = new ImageView(rootView.getContext());
            int achievementId = achivementList.get(i);

            switch (achievementId) {
                case 1:
                    badge.setBackgroundResource(R.drawable.benthanh);
                    break;
                case 2:
                    badge.setBackgroundResource(R.drawable.rmit);
                    break;
                case 3:
                    badge.setBackgroundResource(R.drawable.congtruongdanchu);
                    break;
                case 4:
                    badge.setBackgroundResource(R.drawable.bitexco);
                    break;
                case 5:
                    badge.setBackgroundResource(R.drawable.giaclam);
                    break;
                case 6:
                    badge.setBackgroundResource(R.drawable.independancepalace);
                    break;
                case 7:
                    badge.setBackgroundResource(R.drawable.notredame);
                    break;
                case 8:
                    badge.setBackgroundResource(R.drawable.lethirieng);
                    break;
                case 9:
                    badge.setBackgroundResource(R.drawable.landmark81);
                    break;
                case 10:
                    badge.setBackgroundResource(R.drawable.vanhanhmall);
                    break;
                case 11:
                    badge.setBackgroundResource(R.drawable.studyhub);
                    break;
                default:
                    badge.setBackgroundResource(R.drawable.firsttime);
                    break;
            }

            achievements.addView(badge);
        }

        horizontalScrollView.removeAllViews();
        horizontalScrollView.addView(achievements);

        userName.setText(User.CURRENT_USER.getFullName());
        rank.setText(Utility.convertLevelToTitle(User.CURRENT_USER.getLevel()));
        level.setText(String.valueOf(User.CURRENT_USER.getLevel()));
        BMI.setText(String.valueOf(Utility.calculateBMI(User.CURRENT_USER.getWeightInKilograms(), User.CURRENT_USER.getHeightInCentimeters())));
        totalSteps.setText(String.valueOf(User.CURRENT_USER.getTotalTodaySteps()));
        totalDistance.setText(String.valueOf(User.CURRENT_USER.getTotalTodayDistanceInKilometers()));
        caloriesBurnt.setText(String.valueOf(Utility.calculateCaloriesBurned(User.CURRENT_USER.getWeightInKilograms(), User.CURRENT_USER.getTotalTodayDistanceInKilometers(), User.CURRENT_USER.getAge(), User.CURRENT_USER.getGender(), User.CURRENT_USER.getHeightInCentimeters())));


        if (Objects.equals(User.CURRENT_USER.getProfileImage(), "ava_boy")) {
            profileImage.setBackgroundResource(R.drawable.ava_boy);
        } else {
            profileImage.setBackgroundResource(R.drawable.ava_girl);
        }

        Intent openExternalLink = new Intent(Intent.ACTION_VIEW);

        // Open external links
        mustTryRoute.setOnClickListener(v -> {
            openExternalLink.setData(Uri.parse(MUST_TRY_ROUTE_WEBSITE));
            startActivity(openExternalLink);
        });

        globalRunningDay.setOnClickListener(v -> {
            openExternalLink.setData(Uri.parse(GLOBAL_RUNNING_DAY_WEBSITE));
            startActivity(openExternalLink);
        });

        return rootView;
    }
}