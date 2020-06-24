package com.project.bushop.bushop;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity implements View.OnClickListener {


    FirebaseAuth mAuth;
    EditText editTextemail, editTextpassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mAuth = FirebaseAuth.getInstance();
        editTextemail = (EditText) findViewById(R.id.editTextemail);
        editTextpassword = (EditText) findViewById(R.id.editTextpassword);
        findViewById(R.id.buttonlogin).setOnClickListener(this);

        //To make only Create One Clickable
        TextView txt1 = (TextView) findViewById(R.id.textViewsignup);
        String str = "No account yet? Create one";

        SpannableString ss = new SpannableString(str);

        ClickableSpan cs1 = new ClickableSpan() {
            @Override
            public void onClick(View widget) {
                startActivity(new Intent(getApplicationContext(), SignUp.class));
                finish();
            }
        };

        ss.setSpan(cs1,16,26, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        txt1.setText(ss);
        txt1.setMovementMethod(LinkMovementMethod.getInstance());
    }

    private void userLogin() {
        String email = editTextemail.getText().toString().trim();
        String password = editTextpassword.getText().toString().trim();

        if (email.isEmpty()) {
            editTextemail.setError("Enter Email");
            editTextemail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextemail.setError("Enter Valid Email-ID");
            editTextemail.requestFocus();
            return;

        }
        if (password.isEmpty()) {
            editTextpassword.setError("Enter Password");
            editTextpassword.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {

                    //User has successfully logged in, save this information
                    // We need an Editor object to make preference changes.
                    SharedPreferences settings = getSharedPreferences(MainActivity.PREFS_NAME, 0); // 0 - for private mode
                    SharedPreferences.Editor editor = settings.edit();

                    //Set "hasLoggedIn" to true
                    editor.putBoolean("hasLoggedIn", true);

                    // Commit the edits
                    editor.commit();

                    Intent intent = new Intent(getApplicationContext(), Temp.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(intent);
                    finish();
                }
                else {
                    Toast.makeText(getApplicationContext(), task.getException().getMessage(), Toast.LENGTH_LONG).show();
                }
            }
        });


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonlogin:
                userLogin();
                break;
        }
    }
}
