package com.example.android_city_hunter.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.example.android_city_hunter.R;

public class ActivityFragment extends Fragment {
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_activity, container, false);

        TextView greeting = rootView.findViewById(R.id.text_activity);
        greeting.setText("Quoc 456");
        return rootView;
    }
}