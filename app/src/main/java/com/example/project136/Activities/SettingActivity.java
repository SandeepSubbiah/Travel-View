package com.example.project136.Activities;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import com.example.project136.R;

public class SettingActivity extends AppCompatActivity {

    private static final int REQUEST_CALL_PERMISSION = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        LinearLayout homeBtn = findViewById(R.id.homeButton);
        homeBtn.setOnClickListener(v -> startActivity(new Intent(SettingActivity.this, MainActivity.class)));

        ImageView myButton = findViewById(R.id.imageView7);

        myButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Toast.makeText(SettingActivity.this, "calling...", Toast.LENGTH_SHORT).show();
                makePhoneCall();
            }
        });

        ImageView sendEmailButton = findViewById(R.id.imageView5);

        sendEmailButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
            }
        });

    }

    private void makePhoneCall() {
        // Check if the app has permission to make a phone call
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            // Permission is not granted, request it
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, REQUEST_CALL_PERMISSION);
        } else {
            // Permission is granted, make the phone call
            String phoneNumber = "123456789"; // Replace with the phone number you want to call
            Intent callIntent = new Intent(Intent.ACTION_CALL);
            callIntent.setData(Uri.parse("tel:" + phoneNumber));

            try {
                startActivity(callIntent);
            } catch (SecurityException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CALL_PERMISSION) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // Permission granted, make the phone call
                makePhoneCall();
            } else {
                // Permission denied
                Toast.makeText(SettingActivity.this, "Permission denied.", Toast.LENGTH_SHORT).show();
            }
        }
    }


    private void sendEmail() {

        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:ssandeepsubbiah@gmail.com"));
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject of the email");
        emailIntent.putExtra(Intent.EXTRA_TEXT, "Hello, this is the email body.");
        if (emailIntent.resolveActivity(getPackageManager()) != null) {
            startActivity(emailIntent);
            Toast.makeText(SettingActivity.this, "Email Sent!", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(SettingActivity.this, "Email Sending failed!", Toast.LENGTH_SHORT).show();
        }

    }

}
