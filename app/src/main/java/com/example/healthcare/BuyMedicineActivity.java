package com.example.healthcare;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.HashMap;

public class BuyMedicineActivity extends AppCompatActivity {
    private String[][] packages =
            {
                    {"Uprise-D3 1000IU Capule","","","","50"},
                    {"Healthvit Chromium Picolinate 200mcg Capsule","","","","305"},
                    {"Vitamin B Complex Capsules","","","","448"},
                    {"Inlife vitamin E Wheat Germ Oil Capsule","","","","539"},
                    {"Dolo 650 Tablet","","","","30"},
                    {"Crocin 650 Advanced Tablet","","","","589"},
                    {"Strepsils Medicated Lozenges for Sore Throat","","","","849"},
                    {"Tata 1mg Calcium + VitaminD3","","","","649"},
                    {"Feronia -XT Tablet","","","","999"},
            };
    private String[] package_details =
            {
                    "Building and keeping the bones & teeth strong\n"+
                            "Reducing fatigue/stress and muscular pains\n"+
                            "Boosting immunity and increasing resistance against infections",
                    "Chromium is an essential trace mineral that plays an important role in helping insulin regulate blood glucose",
                    "Provides relief from vitamin B deficiencies\n"+
                            "Helps in formation of Red Blood Cells\n"+
                            "Maintains healthy nervous system",
                    "It promotes health as well as skin benefit.\n"+
                            "It helps reduce skin blemish and pigmentation\n"+
                            "It act as safeguard for the skin from the harsh UVA and UVB sun rays ",
                    "Dolo 650 Tablet helps relieve pain and fever by blocking the releases of certain chemical messengers for fever and pain",
                    "Helps relieve fever and bring down  a high temperature \n"+
                            "Suitable for peaople with a heart condition or high blood pressure",
                    "Relieves the symptoms of a bacterial throat infection  and soothes the recovery process \n"+
                            "Provides a warm and comforting feeling  during sore throat ",
                    "Reduces the risk of calcium deficiency , Rickets  and Osteoporosis\n"+
                            "Promotes mobility and flexibility  of joints",
                    "Helps to reduce the iron deficiency due to chronic blood loss or low intake iron"
            };
    HashMap<String,String> item;
    ArrayList list;
    SimpleAdapter sa;
    ListView lst;
    Button btnBack,btnGoToCart;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_buy_medicine);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        lst = findViewById(R.id.imageViewHAD);
        btnBack = findViewById(R.id.buttonBMCartBack);
        btnGoToCart = findViewById(R.id.buttonBMGoToCart);

        btnGoToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuyMedicineActivity.this,CartBuyMedicineActivity.class));
            }
        });

        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(BuyMedicineActivity.this,HomeActivity.class));
            }
        });

        list = new ArrayList();
        for(int i=0;i<packages.length;i++){
            item = new HashMap<String, String>();
            item.put("line1", packages[i][0]);
            item.put("line2", packages[i][1]);
            item.put("line3", packages[i][2]);
            item.put("line4", packages[i][3]);
            item.put("line5", "Total Cost:"+packages[i][4]+"Ksh");
            list.add(item);
        }
        sa = new SimpleAdapter(this,list,
                R.layout.multi_lines,
                new String[]{"line1","line2","line3","line4","line5"},
                new int[]{R.id.line_a,R.id.line_b, R.id.line_c,R.id.line_d,R.id.line_e});
        lst.setAdapter(sa);

        lst.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(BuyMedicineActivity.this,BuyMedicineDetailsActivity.class);
                it.putExtra("text1",packages[position][0]);
                it.putExtra("text2",package_details[position]);
                it.putExtra("text3",packages[position][4]);
                startActivity(it);

            }
        });
    }
}