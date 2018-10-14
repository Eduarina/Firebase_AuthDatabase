package com.firebase_authdatabase;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText user, pass;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user = findViewById(R.id.edittext);
        pass = findViewById(R.id.edittext2);

        findViewById(R.id.boton2).setOnClickListener(this);
        findViewById(R.id.boton1).setOnClickListener(this);

        mAuth = FirebaseAuth.getInstance();

    }

    private void signIn(String u, String p){
        mAuth.signInWithEmailAndPassword(u,p)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Intent exito = new Intent(MainActivity.this, PlayList.class);
                            startActivity(exito);
                        }else{
                            Toast.makeText(MainActivity.this, "Usuario/Contraseña Incorrecto.",
                                    Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {
        if(v.getId() == R.id.boton1){
            signIn(user.getText().toString(), pass.getText().toString());
        }
        if(v.getId() == R.id.boton2) {
            Intent intento = new Intent(MainActivity.this, Registra.class);
            startActivity(intento);
        }
    }
}
