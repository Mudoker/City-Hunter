package com.example.android_city_hunter;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

public class FileIOManipulator {

    private final Context context;

    public FileIOManipulator(Context context) {
        this.context = context;
    }

    public boolean isFileExist(String fileName) {
        final String FILE_NAME = fileName + ".txt";

        try {
            FileInputStream fileInputStream = context.openFileInput(FILE_NAME);
            fileInputStream.close();
            return true;
        } catch (FileNotFoundException e) {
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
    }


    public void save(String fileName, String content) {
        final String FILE_NAME = fileName + ".txt";

        try (FileOutputStream fos = context.openFileOutput(FILE_NAME, MODE_PRIVATE)) {
            fos.write(content.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String load(String fileName) {
        final String FILE_NAME = fileName + ".txt";

        try (FileInputStream fis = context.openFileInput(FILE_NAME);
             InputStreamReader isr = new InputStreamReader(fis);
             BufferedReader br = new BufferedReader(isr)) {

            // Get all lines and then join
            return br.lines().collect(Collectors.joining(System.lineSeparator()));

        } catch (IOException e) {
            throw new RuntimeException("Error loading user data", e);
        }
    }

    public boolean delete(String fileName) {
        final String FILE_NAME = fileName + ".txt";
        File file = new File(context.getFilesDir(), FILE_NAME);

        if (file.exists()) {
            return file.delete();
        }
        return false;
    }

    public boolean deleteAll() {
        File dir = context.getFilesDir();
        File[] txtFiles = dir.listFiles((dir1, name) -> name.endsWith(".txt"));

        if (txtFiles != null) {
            for (File file : txtFiles) {
                return file.delete();
            }
        }
        return false;
    }

    public List<Badge> getBadgeDataListFromJson(InputStream jsonInputStream) {
        Gson gson = new Gson();

        // Define the type of the List<LocationData>
        Type locationListType = new TypeToken<List<Badge>>(){}.getType();

        try (InputStreamReader reader = new InputStreamReader(jsonInputStream)) {
            // Parse the JSON data into a List<LocationData>
            return gson.fromJson(reader, locationListType);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
