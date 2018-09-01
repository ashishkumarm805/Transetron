package com.core.devhard.transetron;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    Button  BuyButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        BuyButton = findViewById(R.id.buy_tickets_btn_main);

        BuyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                buy();
            }
        });
    }

    private void buy(){
        /* Intent buyIntent = new Intent(MainActivity.this,.class);
        startActivity(buyIntent);
        finish(); */
        Toast.makeText(this, "Building the Map Activity ! Wait for moment..", Toast.LENGTH_SHORT).show();
    }
}
