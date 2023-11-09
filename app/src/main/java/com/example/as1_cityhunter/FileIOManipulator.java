package com.example.as1_cityhunter;

import static android.content.Context.MODE_PRIVATE;

import android.content.Context;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

public class FileIOManipulator {

    private Context context;

    public FileIOManipulator(Context context) {
        this.context = context;
    }

    public boolean isUserDataExist(String fileName) {
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


    public void saveUserData(User user) {
        final String FILE_NAME = user.getUsername() + ".txt";

        try (FileOutputStream fos = context.openFileOutput(FILE_NAME, MODE_PRIVATE)) {
            fos.write(user.toString().getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String loadUserData(String fileName) {
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

    public boolean deleteFile(String fileName) {
        final String FILE_NAME = fileName + ".txt";
        File file = new File(context.getFilesDir(), FILE_NAME);

        if (file.exists()) {
            return file.delete();
        }
        return false;
    }

    public boolean deleteAllTxtFiles() {
        File dir = context.getFilesDir();
        File[] txtFiles = dir.listFiles((dir1, name) -> name.endsWith(".txt"));

        if (txtFiles != null) {
            for (File file : txtFiles) {
                return file.delete();
            }
        }
        return false;
    }
}
