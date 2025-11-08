package com.example.chatly;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Login extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.login), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        EditText Name=findViewById(R.id.edname);
        EditText Number=findViewById(R.id.ednum);
        Button btn=findViewById(R.id.btnext);
        Number.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {
                    String text = Number.getText().toString();
                    if ("فیلد نمی تواند خالی باشد ".equals(text) ||
                            "شماره تلفن نامعتبر است".equals(text)) {
                        Number.setText("");
                        Number.setTextColor(Color.BLACK);
                        Number.setTextSize(20);
                    }
                }
            }
        });


        btn.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v) {
                boolean validnam = false;
                boolean validnamp = false;

                String name = Name.getText().toString();
                if (name.isEmpty()) {
                    Name.setText("فیلد نمی تواند خالی باشد ");
                    Name.setTextColor(Color.RED);
                    Name.setTextSize(20);
                } else {
                    Name.setTextColor(Color.GREEN);
                    Name.setTextSize(20);
                    validnam = true;
                }

                String pnum = Number.getText().toString().trim();
                if (pnum.isEmpty()) {
                    Number.setError("فیلد نمی تواند خالی باشد");
                    validnamp = false;
                } else if (!pnum.matches("^09\\d{9}$")) {
                    Number.setError("شماره تلفن نامعتبر است");
                    validnamp = false;
                } else {
                    Number.setError(null);
                    Number.setTextColor(Color.GREEN);
                    Number.setTextSize(20);
                    validnamp = true;
                }

                if (validnam && validnamp) {
                    new AlertDialog.Builder(Login.this)
                            .setTitle("تأیید شماره تلفن")
                            .setMessage("شماره تلفن: " + pnum + "\n\nآیا این شماره درست است؟")
                            .setPositiveButton("بله", (dialog, which) -> {
                                // رفتن به صفحه بعد
                                Intent intent = new Intent(Login.this, FirstPage.class);
                                startActivity(intent);
                                finish();
                            })
                            .setNegativeButton("خیر", (dialog, which) -> {
                                // خالی کردن فقط فیلد شماره
                                Number.setText("");
                                // رنگ و اندازه به حالت اولیه (یا سیاه با اندازه 20)
                                Number.setTextColor(Color.BLACK);
                                Number.setTextSize(20);
                                Number.requestFocus(); // فوکوس روی فیلد شماره
                            })
                            .setCancelable(false) // جلوگیری از بسته شدن با back یا touch outside
                            .show();
                } else {
                    Toast.makeText(Login.this, "تمام فیلد هارا بدرستی کامل کنید", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}