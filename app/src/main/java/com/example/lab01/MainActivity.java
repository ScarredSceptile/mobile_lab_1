package com.example.lab01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.format.Time;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import java.lang.String;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public static final String EXTRA_MESSAGE =
            "com.example.lab01.extra.MESSAGE";
    public static double bal = new Random().nextInt(21) + 90;

    static boolean added = false;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TextView balance = findViewById(R.id.lbl_balance);
        final Button transactions = findViewById(R.id.btn_transactions);
        final Button transfer = findViewById(R.id.btn_transfer);
        final String[] list = {"Alice", "Bob", "Charlie", "Dawn", "Elvis", "Frode", "Gerd", "Hilda", "Ingrid", "Jack"};

        String balTxt = Double.toString(bal);
        balance.setText(balTxt);

        if (!added) {
            Time now = new Time(Time.getCurrentTimezone());
            now.setToNow();
            TransactionsActivity.addTransaction(now.format("%k:%M:%S"), "Angel", balTxt, balTxt);
            added = true;
        }

        transactions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TransactionsActivity.class);

                startActivity(intent);
            }
        });

        transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, TransferActivity.class);

                intent.putExtra(EXTRA_MESSAGE, list);

                startActivity(intent);
            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        final TextView balance = findViewById(R.id.lbl_balance);
        balance.setText(Double.toString(bal));
    }

}
