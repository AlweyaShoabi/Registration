package com.example.createaccount;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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

public class Login extends AppCompatActivity {
    EditText emailid, pass;
    Button button;
    TextView textView4;
    FirebaseAuth fAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        emailid = findViewById(R.id.emailid);
        pass = findViewById(R.id.pass);
        button = findViewById(R.id.button);
        textView4 = findViewById(R.id.textView2);
        fAuth = FirebaseAuth.getInstance();

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
                    Toast.makeText(Login.this, "Fields are empty", Toast.LENGTH_SHORT).show();
                } else if (!(TextUtils.isEmpty(password) && TextUtils.isEmpty(email))) {
                    fAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(Login.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (!task.isSuccessful()) {
                                Toast.makeText(Login.this, "Signup Is Unsuccessful, Please Try Again", Toast.LENGTH_SHORT).show();
                            } else {
                                startActivity(new Intent(Login.this, Logout.class));
                            }
                        }
                    });
                } else {
                    Toast.makeText(Login.this, "Error Occured", Toast.LENGTH_SHORT).show();

                }
            }


        });
    }







    public void next_page(View view) {
        Intent intent = new Intent(Login.this, Register.class);
        startActivity(intent);
    }

}













