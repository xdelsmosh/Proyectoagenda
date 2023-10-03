package com.example.proyectoagenda;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class LoginActivity extends AppCompatActivity {
    EditText edUsername, edPassword;
    Button btn;
    Button btnregistro;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        edUsername = findViewById(R.id.editTextTextusuario);
        edPassword = findViewById(R.id.editTextTextPassword);
        btn = findViewById(R.id.buttonlog);
        btnregistro = findViewById(R.id.buttonVistaRegistro);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String usuario = edUsername.getText().toString();
                String password = edPassword.getText().toString();
                Database db = new Database(getApplicationContext(),"Proyectoagenda",null,1);
                if (usuario.length()==0 || password.length()==0){

                    Toast.makeText(getApplicationContext(),"Contraseña o usuario incorrecto",Toast.LENGTH_SHORT).show();
                }else {
                    if (db.login(usuario,password)==1){
                        Toast.makeText(getApplicationContext(),"Login exitoso",Toast.LENGTH_SHORT).show();
                        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        editor.putString("usuario",usuario);
                        editor.apply();
                        startActivity(new Intent(LoginActivity.this,HomeActivity.class));
                    }else {
                        Toast.makeText(getApplicationContext(),"Contraseña o usuario incorrecto",Toast.LENGTH_SHORT).show();

                    }
                }
            }
        });
        btnregistro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(LoginActivity.this, RegistroActivity.class));
            }
        });

    }
}