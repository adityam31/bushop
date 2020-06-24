package com.project.bushop.bushop;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUp extends AppCompatActivity {
    EditText t1, t2;
    Button b1;
    TextView _loginLink;
    FirebaseAuth firebaseauth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        firebaseauth = FirebaseAuth.getInstance();

        t1 = (EditText) findViewById(R.id.txt1);
        t2 = (EditText) findViewById(R.id.txt2);
        b1 = (Button) findViewById(R.id.btn1);
        _loginLink = (TextView) findViewById(R.id.link_login);


        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = t1.getText().toString().trim();
                String password = t2.getText().toString().trim();

                if(emailValidate(email)) {

                    if(passwordValidate(password)) {
                        firebaseauth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {

                                if(task.isSuccessful()) {
                                    Toast.makeText(getApplicationContext(), "Successfully Registered. You can now Login.", Toast.LENGTH_SHORT).show();
                                    startActivity(new Intent(getApplicationContext(), Login.class));
                                    finish();
                                }
                                else
                                    Toast.makeText(getApplicationContext(),"Registration Failed. Check Connectivity.", Toast.LENGTH_LONG).show();
                            }
                        });
                    }
                    else
                    {
                        t2.setError("Minimum password length should be 6.");
                        t2.requestFocus();
                    }

                }
                else
                {
                    t1.setError("Enter Valid Email-ID");
                    t1.requestFocus();
                }
            }
        });


        //To make only Login clickable
        String str = "Already a member? Login";
        SpannableString ss = new SpannableString(str);

        ClickableSpan cs1 = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                // Finish the registration screen and return to the Login activity
                Intent intent = new Intent(getApplicationContext(),Login.class);
                startActivity(intent);
                finish();
                //overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        };

        ss.setSpan(cs1,18,23, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        _loginLink.setText(ss);
        _loginLink.setMovementMethod(LinkMovementMethod.getInstance());
    }

    public boolean emailValidate(String email)
    {
        String emailPattern = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

        if (!email.matches(emailPattern)) {
            return false;
        }
        else
            return true;
    }

    public boolean passwordValidate(String password)
    {
        if(password.length() < 6)
            return false;
        else
            return true;
    }
}
