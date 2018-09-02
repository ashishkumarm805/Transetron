package com.core.devhard.transetron;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {

    Button  BuyButton;
    FirebaseAuth mAuth;
    TextView nameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mAuth = FirebaseAuth.getInstance();
        BuyButton = findViewById(R.id.buy_tickets_btn_main);
        nameView = findViewById(R.id.name_main_text);
        nameView.setText(getIntent().getStringExtra("User"));
        BuyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buy();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        if(mAuth.getCurrentUser() == null){
            sendToStart();
            Log.d("MAin", "onStart: Go back to login ");
        }

    }

    private void sendToStart(){
        Intent startIntent = new Intent(MainActivity.this,StartActivity.class);
        startActivity(startIntent);
        finish();


    }

    private void buy(){
        /* Intent buyIntent = new Intent(MainActivity.this,.class);
        startActivity(buyIntent);
        finish(); */
        Toast.makeText(this, "Building the Map Activity ! Wait for moment..", Toast.LENGTH_SHORT).show();
    }
}
