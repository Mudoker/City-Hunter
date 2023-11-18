package com.example.android_city_hunter;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.FragmentActivity;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.example.android_city_hunter.databinding.ActivityMapsBinding;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, SensorEventListener {

    private final FileIOManipulator fileIOManipulator = new FileIOManipulator(this);

    private GoogleMap mMap;
    private ActivityMapsBinding binding;

    private static final int CODE_REQUEST_LOCATION = 999;
    private static final long LOCATION_UPDATE_INTERVAL = 300;

    private ArrayList<Badge> badgeLists = new ArrayList<>();

    private SensorManager sensorManager;
    private Sensor pedometer;

    Location currentUserLatLng;
    FloatingActionButton fabFinish;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        binding = ActivityMapsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        sensorManager = (SensorManager) this.getSystemService(SENSOR_SERVICE);
        pedometer = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

        fabFinish = findViewById(R.id.fabFinish);

        fabFinish.setOnClickListener(v -> {
            setResult(RESULT_OK);
            finish();
        });

        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);
        getPermissionFromUser();
        loadBadges();
    }

    public void getPermissionFromUser() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, CODE_REQUEST_LOCATION);
        } else {
            getUserLocation();
        }
    }

    private boolean checkLocationPermission() {
        return ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        if (requestCode == CODE_REQUEST_LOCATION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                getUserLocation();
                Toast.makeText(this, "Permission Granted", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Permission Denied", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    public void getUserLocation() {
        UserLocationListener userLocationListener = new UserLocationListener();
        LocationManager locationManager = (LocationManager) getSystemService(LOCATION_SERVICE);
        if (checkLocationPermission()) {
            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, LOCATION_UPDATE_INTERVAL, 3f, userLocationListener);
        }

        MapsThread thread = new MapsThread();
        thread.start();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

    }

    private class UserLocationListener implements LocationListener {

        @Override
        public void onLocationChanged(@NonNull Location location) {
            currentUserLatLng = location;
        }
    }

    private class MapsThread extends Thread {
        Location previousLocation;

        MapsThread() {
            super();
            previousLocation = new Location("Start");

            previousLocation.setLatitude(10.78638821215372);
            previousLocation.setLongitude(106.6663340687045);
        }
        @Override
        public void run() {
            while (true) {
                try {
                    if (previousLocation.distanceTo(currentUserLatLng) == 0) {
                        continue;
                    }
                    previousLocation = currentUserLatLng;

                    runOnUiThread(() -> {
                        if (currentUserLatLng != null) {
                            mMap.clear();

                            LatLng currentUserLocation = new LatLng(currentUserLatLng.getLatitude(), currentUserLatLng.getLongitude());
                            Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.ava_boy);
                            Bitmap resizedBitmap = Bitmap.createScaledBitmap(originalBitmap, 300, 300, false);

                            mMap.addMarker(
                                    new MarkerOptions()
                                            .position(currentUserLocation)
                                            .title("User")
                                            .snippet("This is my location")
                                            .icon(BitmapDescriptorFactory.fromBitmap(resizedBitmap))
                            );

                            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(currentUserLocation, 15f));
                            mMap.getUiSettings().setZoomControlsEnabled(true);
                            mMap.getUiSettings().setZoomGesturesEnabled(true);
                        }

                        for (Badge badge : badgeLists) {
                            LatLng badgeLatLng = new LatLng(badge.getBadgeLat().latitude, badge.getBadgeLat().longitude); // Assuming Badge has latitude and longitude properties

                            Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), badge.getImageUrl());
                            Bitmap resizedBitmap = Bitmap.createScaledBitmap(originalBitmap, 350, 350, false);

                            mMap.addMarker(
                                    new MarkerOptions()
                                            .position(badgeLatLng)
                                            .title(badge.getTitle())
                                            .snippet("Experience Points: " + badge.getExperiencePoints())
                                            .icon(BitmapDescriptorFactory.fromBitmap(resizedBitmap))
                            );

                            Location badgeLocation = new Location("Badge Location");
                            badgeLocation.setLatitude(badgeLatLng.latitude);
                            badgeLocation.setLongitude(badgeLatLng.longitude);

                            // 5 meters
                            if (currentUserLatLng.distanceTo(badgeLocation) < 5) {
                                if (!User.CURRENT_USER.getBadges().contains(badge.getId())) {
                                    User.CURRENT_USER.addBadges(badge.getId());
                                    System.out.println("User badges: " + User.CURRENT_USER.getBadges());
                                    Utility.updateUserExperience(badge.getExperiencePoints(), User.CURRENT_USER);
                                    fileIOManipulator.save(User.CURRENT_USER.getUsername(), User.CURRENT_USER.toString());

                                    String userSaved = fileIOManipulator.load(User.CURRENT_USER.getUsername());

                                    System.out.println("User saved: " + userSaved);
                                    Toast.makeText(getApplicationContext(), "You have unlocked new badge", Toast.LENGTH_SHORT).show();
                                }
                            }
                        }
                    });

                    Thread.sleep(1000);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void loadBadges() {
        try (InputStream inputStream = getAssets().open("badges.json");
             BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {

            StringBuilder stringBuilder = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                stringBuilder.append(line);
            }

            String json = stringBuilder.toString();

            JSONArray jsonArray = new JSONArray(json);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject badgeObject = jsonArray.getJSONObject(i);
                JSONObject badgeLocationObject = badgeObject.getJSONObject("badgeLocation");

                String title = badgeObject.getString("title");
                LatLng badgeLocation = new LatLng(Double.parseDouble(badgeLocationObject.getString("latitude")), Double.parseDouble(badgeLocationObject.getString("longitude")));
                int experiencePoints = Integer.parseInt(badgeObject.getString("experiencePoints"));

                String imageUrl = badgeObject.getString("imageUrl");
                int imageId = this.getResources().getIdentifier(imageUrl, "drawable", this.getPackageName());

                Badge badge = new Badge(i + 1, title, badgeLocation, experiencePoints, imageId);
                badgeLists.add(badge);
            }

        } catch (JSONException | IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void onResume() {
        super.onResume();

        pedometer = sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER);

        if (pedometer == null) {
            Toast.makeText(this, "No sensor", Toast.LENGTH_SHORT).show();
        } else {
            sensorManager.registerListener(this, pedometer, SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    public void onPause() {

        super.onPause();

        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        if (event.sensor.getType() == Sensor.TYPE_STEP_COUNTER) {
            int steps = (int) event.values[0];

            User.CURRENT_USER.setTotalSteps(steps);

            System.out.println("Steps: " + steps);
            fileIOManipulator.save(User.CURRENT_USER.getUsername(), User.CURRENT_USER.toString());
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}