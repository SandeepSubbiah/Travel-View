package com.example.project136.Activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import com.example.project136.R;

public class SignUpActivity extends Activity {

    private EditText editTextEmail, editTextPassword;
    private Button buttonSignup;
    private FirebaseAuth firebaseAuth;
    private TextView buttonResetPassword;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_signup);

            firebaseAuth = FirebaseAuth.getInstance();
            editTextEmail = findViewById(R.id.editTextTextPersonName3);
            editTextEmail = findViewById(R.id.editTextTextPersonName);
            editTextPassword = findViewById(R.id.editTextTextPersonName2);
            buttonSignup = findViewById(R.id.button);


            buttonSignup.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String email = editTextEmail.getText().toString().trim();
                    String password = editTextPassword.getText().toString().trim();

                    // Validate email and password
                    if (email.isEmpty() || password.isEmpty()) {
                        Toast.makeText(SignUpActivity.this, "Please fill in all fields.", Toast.LENGTH_SHORT).show();
                    } else {
                        // Create a new user with email and password
                        firebaseAuth.createUserWithEmailAndPassword(email, password)
                                .addOnCompleteListener(SignUpActivity.this, new OnCompleteListener<AuthResult>() {
                                    @Override
                                    public void onComplete(@NonNull Task<AuthResult> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(SignUpActivity.this, "Signup successful!", Toast.LENGTH_SHORT).show();
//                                            startActivity(new Intent(SignUpActivity.this, LoginActivity.class));
                                            finish();
                                            // You can add additional actions here, like navigating to the home screen
                                        } else {
                                            Toast.makeText(SignUpActivity.this, "Signup failed. Try again.", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }
                }
            });


            buttonResetPassword = findViewById(R.id.textResetPassword);

            buttonResetPassword.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String email = editTextEmail.getText().toString().trim();

                    // Validate email
                    if (email.isEmpty()) {
                        Toast.makeText(SignUpActivity.this, "Please enter your email.", Toast.LENGTH_SHORT).show();
                    } else {
                        // Send a password reset email
                        firebaseAuth.sendPasswordResetEmail(email)
                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(SignUpActivity.this,
                                                    "Password reset email sent. Check your inbox.", Toast.LENGTH_SHORT).show();
                                        } else {
                                            Toast.makeText(SignUpActivity.this,
                                                    "Failed to send password reset email. Check your email address.", Toast.LENGTH_SHORT).show();
                                        }
                                    }
                                });
                    }
                }
            });

        }
}
