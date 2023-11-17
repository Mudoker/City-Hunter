package com.example.android_city_hunter;

import androidx.fragment.app.FragmentActivity;

import android.os.Bundle;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.android_city_hunter.databinding.ActivityMapsBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    FloatingActionButton fabFinish;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        fabFinish = findViewById(R.id.fabFinish);

        fabFinish.setOnClickListener(v -> {
            setResult(RESULT_OK);
            finish();
        });
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

//        try (InputStream inputStream = getAssets().open("badges.json");
//             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
//
//            StringBuilder stringBuilder = new StringBuilder();
//            String line;
//            while ((line = reader.readLine()) != null) {
//                stringBuilder.append(line);
//            }
//
//            String json = stringBuilder.toString();
//
//            JSONArray jsonArray = new JSONArray(json);
//
//            for (int i = 0; i < jsonArray.length(); i++) {
//                JSONObject badgeObject = jsonArray.getJSONObject(i);
//                JSONObject badgeLocationObject = badgeObject.getJSONObject("badgeLocation");
//
//                String title = badgeObject.getString("title");
//                LatLng sydney = new LatLng(Double.parseDouble(badgeLocationObject.getString("latitude")), Double.parseDouble(badgeLocationObject.getString("longitude")));
//
//                mMap.addMarker(new MarkerOptions().position(sydney).title(title));
//            }
//
//        } catch (JSONException | IOException e) {
//            e.printStackTrace();
//        }
        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }
}