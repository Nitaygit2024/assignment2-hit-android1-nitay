package com.example.shoppingmanagementapp;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private EditText newUsernameEditText, newPasswordEditText;
    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        newUsernameEditText = findViewById(R.id.new_username);
        newPasswordEditText = findViewById(R.id.new_password);
        Button registerButton = findViewById(R.id.register_button);

        sharedPreferences = getSharedPreferences("UserDetails", Context.MODE_PRIVATE);

        registerButton.setOnClickListener(v -> {
            String newUsername = newUsernameEditText.getText().toString();
            String newPassword = newPasswordEditText.getText().toString();

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("username", newUsername);
            editor.putString("password", newPassword);
            editor.apply();

            Toast.makeText(RegisterActivity.this, "User registered successfully!", Toast.LENGTH_SHORT).show();

            finish();
        });
    }
}
