package com.example.project136.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.appcompat.widget.AppCompatButton;

import com.example.project136.R;

public class PaymentActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        AppCompatButton settingBtn = findViewById(R.id.button);
//        settingBtn.setOnClickListener(v -> startActivity(new Intent(PaymentActivity.this, MainActivity.class)));
//        Toast.makeText(PaymentActivity.this, "Payment Successful!", Toast.LENGTH_SHORT).show();

        settingBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Payment Received!", Toast.LENGTH_LONG).show();
                startActivity(new Intent(PaymentActivity.this, MainActivity.class));
            }
        });

    }

}
