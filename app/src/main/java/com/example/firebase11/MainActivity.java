package com.example.firebase11;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText et_mail, et_pass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        et_mail = findViewById(R.id.et_mail);
        et_pass = findViewById(R.id.et_pass);


    }
    public void CrearCuenta(View view){
        Intent registrar = new Intent(this, Registrar.class);
        startActivity(registrar);


    }
}