package com.firebase_authdatabase;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Registra extends AppCompatActivity implements View.OnClickListener {

    private String user,pass;
    private FirebaseAuth mAuth;
    private EditText cor,con;
    private Button btn1;
    private static final String TAG = "EmailPassword";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.registro);

        cor = findViewById(R.id.edittext3);
        con = findViewById(R.id.edittext4);
        mAuth = FirebaseAuth.getInstance();

        btn1 = findViewById(R.id.boton3);

        btn1.setOnClickListener(this);
    }

    private void CreaUsuario(String email, String pass){
        Log.i(TAG,"Creo que hay pedos");
        mAuth.createUserWithEmailAndPassword(email,pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Log.d(TAG, "createUserWithEmail:success");
                            finish();
                        }else{
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                        }
                    }
                });
    }

    @Override

    public void onClick(View v) {
        if(v.getId() == R.id.boton3){
            CreaUsuario(cor.getText().toString(),con.getText().toString());
        }
    }
}
