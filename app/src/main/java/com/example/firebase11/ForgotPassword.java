package com.example.firebase11;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgotPassword extends AppCompatActivity {
    Button recuperarBoton;
    EditText emailEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgot_password);

        recuperarBoton=findViewById(R.id.buttonrecuperar);
        emailEditText=findViewById(R.id.et_mail2);

        recuperarBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                validate();
            }
        });

    }

    private void validate() {
        String email=emailEditText.getText().toString().trim();
        if (email.isEmpty()){
            emailEditText.setError("correo invalido");
            return;
        }
        sendEmail(email);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        Intent i= new Intent(ForgotPassword.this,LoginActivity.class);
        startActivity(i);
        finish();
    }

    private void sendEmail(String email) {
        FirebaseAuth auth= FirebaseAuth.getInstance();
        String emailad=email;

        auth.sendPasswordResetEmail(emailad).addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()){
                    Toast.makeText(ForgotPassword.this, "Correo enviado", Toast.LENGTH_SHORT).show();
                    Intent i=new Intent(ForgotPassword.this,LoginActivity.class);
                    startActivity(i);
                    finish();
                }else {
                    Toast.makeText(ForgotPassword.this, "Correo invalido    ", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}