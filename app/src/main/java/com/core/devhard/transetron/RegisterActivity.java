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

public class RegisterActivity extends AppCompatActivity {
    private static final String TAG = "RegisterActivity";

    Button  RegisterBtn;
    EditText NameText,EmailText,PwdText;
    FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();
        EmailText = findViewById(R.id.emailText_reg);
        PwdText = findViewById(R.id.passText_reg);
        NameText = findViewById(R.id.nameText);
        RegisterBtn=findViewById(R.id.reg_reg_btn);

    }

    @Override
    protected void onStart() {
        super.onStart();


            RegisterBtn.setEnabled(true);

            RegisterBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(TextUtils.isEmpty(NameText.getText())||TextUtils.isEmpty(EmailText.getText())||TextUtils.isEmpty(PwdText.getText())){
                        Toast.makeText(RegisterActivity.this, "Fill up all Fields", Toast.LENGTH_SHORT).show();
                    }else {
                        final String email = EmailText.getText().toString();
                        final String pwd = PwdText.getText().toString();
                        final String name = NameText.getText().toString();
                        register(email, pwd, name);
                    }
                }
            });
        }


    private void register(String id , String Pass , final String Name){

        mAuth.createUserWithEmailAndPassword(id, Pass)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI(user,Name);
                        } else {
                            // If sign in fails, display a message to the user.
                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(RegisterActivity.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            updateUI(null,null);
                        }

                        // ...
                    }
                });
    }
    private void updateUI(FirebaseUser user , String n){

        Intent newIntent = new Intent(RegisterActivity.this,MainActivity.class);
        newIntent.putExtra(n,"User");
        newIntent.putExtra(user.getUid(),"UUID");
        startActivity(newIntent);
        finish();


    }

}
