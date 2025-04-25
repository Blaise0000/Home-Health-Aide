package com.example.healthcare;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;

public class DoctorDetailsActivity extends AppCompatActivity {
    private String[][] doctor_details1 =
            {
                    {"Doctor Name :Clinton Odhiambo", "Hospital Address :Inuka", "Exp :5yrs", "Mobile No :0702012888", "600"},
                    {"Doctor Name :Blaise Ochieng", "Hospital Address :West", "Exp :15yrs", "Mobile No :0724924948", "900"},
                    {"Doctor Name :Nadia Akinyi", "Hospital Address :Nazareth", "Exp :8yrs", "Mobile No :0797275810", "300"},
                    {"Doctor Name :Layla Achieng", "Hospital Address :H/Bay", "Exp :6yrs", "Mobile No :0710005467", "500"},
                    {"Doctor Name :Migel Omondi", "Hospital Address :Karuri", "Exp :7yrs", "Mobile No :0724924948", "800"},
            };
    private String[][] doctor_details2 =
            {
                    {"Doctor Name :William Odhiambo", "Hospital Address :Inuka", "Exp :5yrs", "Mobile No :0702012888", "600"},
                    {"Doctor Name :Kate Ochieng", "Hospital Address :West", "Exp :15yrs", "Mobile No :0724924948", "900"},
                    {"Doctor Name :John Akinyi", "Hospital Address :Nazareth", "Exp :8yrs", "Mobile No :0797275810", "300"},
                    {"Doctor Name :Biggie Achieng", "Hospital Address :H/Bay", "Exp :6yrs", "Mobile No :0710005467", "500"},
                    {"Doctor Name :Walter Omondi", "Hospital Address :Karuri", "Exp :7yrs", "Mobile No :0724924948", "800"},
            };
    private String[][] doctor_details3 =
            {
                    {"Doctor Name :Biden Odhiambo", "Hospital Address :Inuka", "Exp :5yrs", "Mobile No :0702012888", "600"},
                    {"Doctor Name :Ramsey Ochieng", "Hospital Address :West", "Exp :15yrs", "Mobile No :0724924948", "900"},
                    {"Doctor Name :Matthew Akinyi", "Hospital Address :Nazareth", "Exp :8yrs", "Mobile No :0797275810", "300"},
                    {"Doctor Name :Edgar Achieng", "Hospital Address :H/Bay", "Exp :6yrs", "Mobile No :0710005467", "500"},
                    {"Doctor Name :Keith Omondi", "Hospital Address :Karuri", "Exp :7yrs", "Mobile No :0724924948", "800"},
            };
    private String[][] doctor_details4 =
            {
                    {"Doctor Name :Irene Odhiambo", "Hospital Address :Inuka", "Exp :5yrs", "Mobile No :0702012888", "600"},
                    {"Doctor Name :Sharon Naliaka", "Hospital Address :West", "Exp :15yrs", "Mobile No :0724924948", "900"},
                    {"Doctor Name :Grace Akinyi", "Hospital Address :Nazareth", "Exp :8yrs", "Mobile No :0797275810", "300"},
                    {"Doctor Name :Teresa Waithaka", "Hospital Address :H/Bay", "Exp :6yrs", "Mobile No :0710005467", "500"},
                    {"Doctor Name :Simon Omondi", "Hospital Address :Karuri", "Exp :7yrs", "Mobile No :0724924948", "800"},
            };
    private String[][] doctor_details5 =
            {
                    {"Doctor Name :Denier Odhiambo", "Hospital Address :Inuka", "Exp :5yrs", "Mobile No :0702012888", "1600"},
                    {"Doctor Name :Caren Jain", "Hospital Address :West", "Exp :15yrs", "Mobile No :0724924948", "1900"},
                    {"Doctor Name :Natalie Akinyi", "Hospital Address :Nazareth", "Exp :8yrs", "Mobile No :0797275810", "1300"},
                    {"Doctor Name :Peter Achieng", "Hospital Address :H/Bay", "Exp :6yrs", "Mobile No :0710005467", "1500"},
                    {"Doctor Name :Robert Omondi", "Hospital Address :Karuri", "Exp :7yrs", "Mobile No :0724924948", "1800"},
            };
     TextView tv;
     Button btn;
String[][ ]doctor_details = {};
ArrayList list;
HashMap<String,String> item;
SimpleAdapter sa;

    @SuppressLint({"MissingInflatedId", "SuspiciousIndentation"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_doctor_details);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        tv = findViewById(R.id.textViewBMCartTitle);
        btn = findViewById(R.id.buttonBMCartBack);
        Intent it = getIntent();
        String title = it.getStringExtra("title");
        tv.setText(title);

        if(title.compareTo("Family Physician")==0)
            doctor_details = doctor_details1;
        else
        if(title.compareTo("Dietician")==0)
            doctor_details = doctor_details2;
        else
        if(title.compareTo("Dentist")==0)
            doctor_details = doctor_details3;
        else
        if(title.compareTo("Surgeon")==0)
            doctor_details = doctor_details4;
        else
            doctor_details = doctor_details5;



            btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(DoctorDetailsActivity.this,FindDoctorActivity.class));
            }
        });
            list = new ArrayList();
            for(int i=0;i< doctor_details.length;i++){
                item = new HashMap<String,String>();
                item.put("line1",doctor_details[i][0]);
                item.put("line2",doctor_details[i][1]);
                item.put("line3",doctor_details[i][2]);
                item.put("line4",doctor_details[i][3]);
                item.put("line5", "Consultation Fees: "+doctor_details[i][4]+"Ksh");
                list.add(item);
            }
            sa = new SimpleAdapter(this,list,
                    R.layout.multi_lines,
                    new String[]{"line1","line2","line3","line4","line5"},
                    new int[]{R.id.line_a,R.id.line_b, R.id.line_c,R.id.line_d,R.id.line_e}

            );
        ListView lst = findViewById(R.id.imageViewHAD);
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(DoctorDetailsActivity.this,BookAppointmentActivity.class);
                it.putExtra("text1",title);
                int i = 0;
                it.putExtra("text2",doctor_details[i][0]);
                it.putExtra("text3",doctor_details[i][1]);
                it.putExtra("text4",doctor_details[i][3]);
                it.putExtra("text5",doctor_details[i][4]);
                startActivity(it);

            }
        });

    }
}