package com.example.expensemanager;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ResetActivity extends AppCompatActivity {

    private EditText mEmail;
    private Button btnResest;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reset);

        mEmail=findViewById(R.id.reset_login);
        btnResest=findViewById(R.id.btn_reset);

        mAuth=FirebaseAuth.getInstance();

        btnResest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email=mEmail.getText().toString().trim();

                if (TextUtils.isEmpty(email)){
                    Toast.makeText(ResetActivity.this,"Email is required!...",Toast.LENGTH_SHORT).show();
                }
                else {
                    mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                Toast.makeText(ResetActivity.this,"Password resetting mail is sent.",Toast.LENGTH_SHORT).show();
                                startActivity(new Intent(getApplicationContext(),MainActivity.class));
                            }
                            else{
                                String  message = task.getException().getMessage();
                                Toast.makeText(ResetActivity.this,"An error occured"+message,Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                }
            }
        });

    }
}