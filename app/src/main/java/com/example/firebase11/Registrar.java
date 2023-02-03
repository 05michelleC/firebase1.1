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
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseUser;

public class Registrar extends AppCompatActivity {

    private EditText et_mail1, et_pass1;
    private Button btn_registrar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registrar);

        et_mail1 = findViewById(R.id.et_mail1);
        et_pass1 = findViewById(R.id.et_pass1);
        btn_registrar = findViewById(R.id.btn4);

        mAuth = FirebaseAuth.getInstance();

        btn_registrar.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                String mail1 = et_mail1.getText().toString();
                String pass1 = et_pass1.getText().toString();

                if(mail1.equals("") && pass1.equals("") || mail1.equals("") || pass1.equals("") ){
                    Toast.makeText(Registrar.this, "Completa los campos vacios", Toast.LENGTH_SHORT).show();

                }else if(pass1.length() <6){
                    Toast.makeText(Registrar.this, "Contraseña muy corta, 6 caracteres minimo", Toast.LENGTH_SHORT).show();
                }else{
                    mAuth.createUserWithEmailAndPassword(mail1,pass1).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                           if(task.isSuccessful()){
                               Toast.makeText(Registrar.this, "Usuario registrado correctamente", Toast.LENGTH_SHORT).show();
                               finish();
                           }else{
                               String errorCode = ((FirebaseAuthException) task.getException()).getErrorCode();
                               dameToastdeerror(errorCode);
                           }
                        }
                    });
                }
            }
        });
    }
    //Verificar si el usuario está conectado
    @Override
    protected void onStart() {
        super.onStart();

        FirebaseUser UsuarioConectado = mAuth.getCurrentUser();
        if(UsuarioConectado != null){
            UsuarioConectado.reload();
        }
    }
    public void Volver(View view){
        Intent volver = new Intent(this, MainActivity.class);
        startActivity(volver);
    }
    public void dameToastdeerror(String error) {

        switch (error) {

            case "ERROR_INVALID_CUSTOM_TOKEN":
                Toast.makeText(Registrar.this, "El formato del token personalizado es incorrecto. Por favor revise la documentación", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_CUSTOM_TOKEN_MISMATCH":
                Toast.makeText(Registrar.this, "El token personalizado corresponde a una audiencia diferente.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_INVALID_CREDENTIAL":
                Toast.makeText(Registrar.this, "La credencial de autenticación proporcionada tiene un formato incorrecto o ha caducado.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_INVALID_EMAIL":
                //Toast.makeText(Registrar.this, "La dirección de correo electrónico está mal formateada.", Toast.LENGTH_LONG).show();

                //Esta linea muestra al usuario el error en pantalla (específicamente en la caja de texto), sin necesidad de usar el TOAST.
                et_mail1.setError("La dirección de correo electrónico está mal formulada.");
                et_mail1.requestFocus();
                break;

            case "ERROR_WRONG_PASSWORD":
                Toast.makeText(Registrar.this, "La contraseña no es válida o el usuario no tiene contraseña.", Toast.LENGTH_LONG).show();
                et_pass1.setError("la contraseña es incorrecta ");
                et_pass1.requestFocus();
                et_pass1.setText("");
                break;

            case "ERROR_USER_MISMATCH":
                Toast.makeText(Registrar.this, "Las credenciales proporcionadas no corresponden al usuario que inició sesión anteriormente.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_REQUIRES_RECENT_LOGIN":
                Toast.makeText(Registrar.this,"Esta operación es sensible y requiere autenticación reciente. Inicie sesión nuevamente antes de volver a intentar esta solicitud.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_ACCOUNT_EXISTS_WITH_DIFFERENT_CREDENTIAL":
                Toast.makeText(Registrar.this, "Ya existe una cuenta con la misma dirección de correo electrónico pero diferentes credenciales de inicio de sesión. Inicie sesión con un proveedor asociado a esta dirección de correo electrónico.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_EMAIL_ALREADY_IN_USE":
                //Toast.makeText(Registrar.this, "La dirección de correo electrónico ya está siendo utilizada por otra cuenta.", Toast.LENGTH_LONG).show();
                et_mail1.setError("La dirección de correo electrónico ya está siendo utilizada por otra cuenta.");
                et_mail1.requestFocus();
                break;

            case "ERROR_CREDENTIAL_ALREADY_IN_USE":
                Toast.makeText(Registrar.this, "Esta credencial ya está asociada con una cuenta de usuario diferente.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_USER_DISABLED":
                Toast.makeText(Registrar.this, "La cuenta de usuario ha sido inhabilitada por un administrador.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_USER_TOKEN_EXPIRED":
                Toast.makeText(Registrar.this, "La credencial del usuario ya no es válida. El usuario debe iniciar sesión nuevamente.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_USER_NOT_FOUND":
                Toast.makeText(Registrar.this, "No hay ningún registro de usuario que corresponda a este identificador. Es posible que se haya eliminado el usuario.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_INVALID_USER_TOKEN":
                Toast.makeText(Registrar.this, "La credencial del usuario ya no es válida. El usuario debe iniciar sesión nuevamente.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_OPERATION_NOT_ALLOWED":
                Toast.makeText(Registrar.this, "Esta operación no está permitida. Debes habilitar este servicio en la consola.", Toast.LENGTH_LONG).show();
                break;

            case "ERROR_WEAK_PASSWORD":
                //Toast.makeText(Registrar.this, "La contraseña proporcionada no es válida..", Toast.LENGTH_LONG).show();
                et_pass1.setError("La contraseña no es válida, debe tener al menos 6 caracteres.");
                et_pass1.requestFocus();
                break;

        }
    }
}