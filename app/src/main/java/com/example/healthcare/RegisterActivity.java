package com.example.healthcare;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {
    EditText edUsername, edEmail, edPassword, edConfirm;
    Button btn;
    TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        edUsername = findViewById(R.id.editTextAppFullName);
        edPassword = findViewById(R.id.editTextAppFees);
        edEmail = findViewById(R.id.editTextAppAddress);
        edConfirm = findViewById(R.id.editTextAppContactNumber);
        btn = findViewById(R.id.buttonAppBack);
        tv = findViewById(R.id.textViewExistingUser);

        tv.setOnClickListener(v -> startActivity(new Intent(RegisterActivity.this, LoginActivity.class)));

        btn.setOnClickListener(v -> {
            String username = edUsername.getText().toString();
            String email = edEmail.getText().toString();
            String password = edPassword.getText().toString();
            String confirm = edConfirm.getText().toString();
            Database db = new Database(getApplicationContext(), "healthcare", null, 1);

            if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirm.isEmpty()) {
                Toast.makeText(getApplicationContext(), "Please Fill All Details", Toast.LENGTH_SHORT).show();
            } else if (!password.equals(confirm)) {
                Toast.makeText(getApplicationContext(), "Password and confirm password didn't match", Toast.LENGTH_SHORT).show();
            } else if (!isValid(password)) {
                Toast.makeText(getApplicationContext(), "Password must contain at least 8 characters, having letter, digit, and special symbol", Toast.LENGTH_SHORT).show();
            } else {
                db.register(username, email, password);
                Toast.makeText(getApplicationContext(), "Record inserted", Toast.LENGTH_SHORT).show();
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
    }

    public static boolean isValid(String passwordhere) {
        int f1 = 0, f2 = 0, f3 = 0;
        if (passwordhere.length() >= 8) {
            for (int p = 0; p < passwordhere.length(); p++) {
                char c = passwordhere.charAt(p);
                if (Character.isLetter(c)) {
                    f1 = 1;
                }
                if (Character.isDigit(c)) {
                    f2 = 1;
                }
                if (c > 33 && c <= 46 || c == 64) {
                    f3 = 1;
                }
            }
            return f1 == 1 && f2 == 1 && f3 == 1;
        }
        return false;
    }
}
