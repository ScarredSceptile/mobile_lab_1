package com.example.lab01;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.format.Time;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

public class TransferActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transfer_activity);

        final Spinner dropDown = findViewById(R.id.spinner);
        final TextView amount = findViewById(R.id.txt_amount);
        final TextView check = findViewById(R.id.lbl_amount_check);
        final Button pay = findViewById(R.id.btn_pay);

        Intent intent = getIntent();
        final String[] list = intent.getStringArrayExtra(MainActivity.EXTRA_MESSAGE);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(TransferActivity.this, android.R.layout.simple_spinner_dropdown_item
        , list);

        dropDown.setAdapter(adapter);

        pay.setEnabled(false);

        amount.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                pay.setEnabled(false);
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (amount.getText().length() != 0) {
                    double payment = Double.parseDouble(amount.getText().toString());

                    String[] response = {"You cannot transfer more than your balance!",
                            "You have to transfer something!",
                            "You cannot transfer a negative value!"};

                    if (payment > MainActivity.bal) {
                        check.setText(response[0]);
                        pay.setEnabled(false);
                    } else if (payment == 0.00) {
                        check.setText(response[1]);
                        pay.setEnabled(false);
                    } else if (payment < 0.00) {
                        check.setText(response[2]);
                        pay.setEnabled(false);
                    } else {
                        check.setText("");
                        pay.setEnabled(true);
                    }
                }
            }
        });

        pay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double payment = Double.parseDouble(amount.getText().toString());
                MainActivity.bal -= payment;
                Time now = new Time(Time.getCurrentTimezone());
                now.setToNow();
                String Paid = dropDown.getSelectedItem().toString();
                TransactionsActivity.addTransaction(now.format("%k:%M:%S"), Paid, Double.toString(payment), Double.toString(MainActivity.bal));
                finish();
            }
        });

    }
}
