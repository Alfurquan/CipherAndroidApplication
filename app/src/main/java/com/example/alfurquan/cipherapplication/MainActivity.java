package com.example.alfurquan.cipherapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Intent intent;
    Button btnEncrypt,btnDecrpt;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnEncrypt = (Button) findViewById(R.id.encrypt);
        btnDecrpt = (Button) findViewById(R.id.decrypt);
        btnEncrypt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainActivity.this,EncryptionActivity.class);
                startActivity(intent);
            }
        });
        btnDecrpt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                intent = new Intent(MainActivity.this,DecryptionActivity.class);
                startActivity(intent);
            }
        });
    }
}
