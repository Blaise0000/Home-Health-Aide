package com.example.healthcare;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Calendar;

public class BookAppointmentActivity extends AppCompatActivity {
    EditText ed1, ed2, ed3, ed4;
    TextView tv;
    private DatePickerDialog datePickerDialog;
    private TimePickerDialog timePickerDialog;
    private Button dateButton, timeButton, btnBook, btnBack;

    String title, fullname, address, contact, fees;

    @SuppressLint({"MissingInflatedId", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_book_appointment);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        // Initialize views
        tv = findViewById(R.id.textViewAppTitle);
        ed1 = findViewById(R.id.editTextAppFullName);
        ed2 = findViewById(R.id.editTextAppAddress);
        ed3 = findViewById(R.id.editTextAppContactNumber);
        ed4 = findViewById(R.id.editTextAppFees);
        dateButton = findViewById(R.id.buttonCartTime);
        timeButton = findViewById(R.id.buttonAppTime);
        btnBook = findViewById(R.id.buttonAppBookAppointment);
        btnBack = findViewById(R.id.buttonAppBack);

        // Make fields non-editable
        ed1.setKeyListener(null);
        ed2.setKeyListener(null);
        ed3.setKeyListener(null);
        ed4.setKeyListener(null);

        // Get Intent data
        Intent it = getIntent();
        title = it.getStringExtra("text1");
        fullname = it.getStringExtra("text2");
        address = it.getStringExtra("text3");
        contact = it.getStringExtra("text4");
        fees = it.getStringExtra("text5");

        // Set data to views
        tv.setText(title);
        ed1.setText(fullname);
        ed2.setText(address);
        ed3.setText(contact);
        ed4.setText("Consultation Fees: " + fees + " Ksh");

        // Initialize pickers
        initDatePicker();
        initTimePicker();

        // Set button listeners
        dateButton.setOnClickListener(v -> datePickerDialog.show());
        timeButton.setOnClickListener(v -> timePickerDialog.show());

        btnBack.setOnClickListener(v -> startActivity(new Intent(BookAppointmentActivity.this, FindDoctorActivity.class)));

        btnBook.setOnClickListener(v -> {
            Database db = new Database(getApplicationContext(), "healthcare", null, 1);
            SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
            String username = sharedPreferences.getString("username", "");

            if (db.checkAppointmentExists(username, title + " =>" + fullname, address, contact,
                    dateButton.getText().toString(), timeButton.getText().toString()) == 1) {
                Toast.makeText(getApplicationContext(), "Appointment already booked", Toast.LENGTH_LONG).show();
            } else {
                db.addOrder(username, title + " => " + fullname, address, contact, 0,
                        dateButton.getText().toString(), timeButton.getText().toString(),
                        Float.parseFloat(fees), "appointment");
                Toast.makeText(getApplicationContext(), "Your appointment is booked successfully", Toast.LENGTH_LONG).show();
                startActivity(new Intent(BookAppointmentActivity.this, HomeActivity.class));
            }
        });
    }

    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = (datePicker, year, month, day) -> {
            month = month + 1;
            String date = day + "/" + month + "/" + year;
            dateButton.setText(date);
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style = AlertDialog.THEME_HOLO_DARK;
        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        datePickerDialog.getDatePicker().setMinDate(cal.getTimeInMillis() + 86400000); // Disable today's date
    }

    private void initTimePicker() {
        TimePickerDialog.OnTimeSetListener timeSetListener = (timePicker, hour, minute) -> {
            String time = String.format("%02d:%02d", hour, minute);
            timeButton.setText(time);
        };

        Calendar cal = Calendar.getInstance();
        int hrs = cal.get(Calendar.HOUR_OF_DAY);
        int mins = cal.get(Calendar.MINUTE);

        int style = AlertDialog.THEME_HOLO_DARK;
        timePickerDialog = new TimePickerDialog(this, style, timeSetListener, hrs, mins, true);
    }
}

