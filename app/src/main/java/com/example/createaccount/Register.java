package com.example.createaccount;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;


public class Register extends AppCompatActivity {
    EditText name, emailid, pass;
    Button button;
    TextView textView2;
    FirebaseAuth fAuth;

private FirebaseAuth.AuthStateListener mAuthStateListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        name = findViewById(R.id.name);
        emailid = findViewById(R.id.emailid);
        pass = findViewById(R.id.pass);
        button = findViewById(R.id.button);
        textView2 = findViewById(R.id.textView2);
        fAuth = FirebaseAuth.getInstance();
        mAuthStateListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser mFirebaseUser=fAuth.getCurrentUser();
                if(fAuth!=null){
                    Toast.makeText(Register.this, "You Are Logged In", Toast.LENGTH_SHORT).show();
                    Intent i= new Intent(Register.this, Login.class);
                    startActivity(i);
                }
                else{
                    Toast.makeText(Register.this, "Please Login", Toast.LENGTH_SHORT).show();
                }

            }
        };
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailid.getText().toString().trim();
                String password = pass.getText().toString().trim();
                if (TextUtils.isEmpty(email)) {
                    emailid.setError("Email is required");
                    return;
                }
                if (TextUtils.isEmpty(password)) {
                    pass.setError("Password is required");
                    return;
                }
                if (password.length() < 6) {
                    pass.setError("Password must be>=6 characters");
                    return;
                }
                if (TextUtils.isEmpty(password) && TextUtils.isEmpty(email)) {
                    Toast.makeText(Register.this, "Fields are empty", Toast.LENGTH_SHORT).show();
                } else if (!(TextUtils.isEmpty(password) && TextUtils.isEmpty(email))) {
                    fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(Register.this, "Login Error, Please Login Again", Toast.LENGTH_SHORT).show();
                            } else {
                                startActivity(new Intent(Register.this, Logout.class));
                            }
                        }
                    });
                } else {
                    Toast.makeText(Register.this, "Error Occured", Toast.LENGTH_SHORT).show();

                }
            }


        });

    }

    public void nextpage(View view) {
        Intent intent = new Intent(this, Login.class);
        startActivity(intent);
    }



}



