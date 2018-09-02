package com.core.devhard.transetron;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
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

public class LoginActivity extends AppCompatActivity {

    private static final String TAG = "LoginActivity";
    EditText emailText,pwdText;
    Button LoginBtn;
    FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mAuth = FirebaseAuth.getInstance();
        emailText = findViewById(R.id.emailText);
        pwdText = findViewById(R.id.passText);
        LoginBtn=findViewById(R.id.login_log_btn);

    }

    private void login(String id,String pass){

        mAuth.signInWithEmailAndPassword(id, pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            if (user != null) {
                                updateUI(user);
                            }
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(LoginActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null);
                        }

                        // ...
                    }
                });


    }

    private void updateUI(FirebaseUser user){
        Intent logEmIntent = new Intent(LoginActivity.this,MainActivity.class);
        logEmIntent.putExtra(user.getUid(), "User");
        startActivity(logEmIntent);
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
            LoginBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(TextUtils.isEmpty(emailText.getText())||TextUtils.isEmpty(pwdText.getText())){
                        LoginBtn.setEnabled(false);
                    }else {

                        final String email = emailText.getText().toString();
                        final String pwd = pwdText.getText().toString();
                        login(email, pwd);
                    }
                }
            });
        }

    }

