package com.example.lab01;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class TransactionsActivity extends AppCompatActivity {

        static String[] time = new String[100];
        static String[] name = new String[100];
        static String[] transaction = new String[100];
        static String[] balan = new String[100];
        static int total = -1;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.transactions_activity);

            final ListView list = findViewById(R.id.list);

            final ArrayList<String> listContext = new ArrayList<>();
            for (int i = 0; i <= total; i++) {
                String text = time[i] + "\t|\t" + name[i] + "\t|\t" + transaction[i] + "\t|\t" + balan[i];
                listContext.add(text);
            }

            final ArrayAdapter adapter = new ArrayAdapter(TransactionsActivity.this,android.R.layout.simple_list_item_1, listContext);

            list.setAdapter(adapter);

            list.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
                @Override
                public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                    Toast.makeText(TransactionsActivity.this, name[position] + " " + transaction[position], Toast.LENGTH_LONG).show();
                    return true;
                }
            });
        }


    public static void addTransaction(String d, String n, String t, String b) {
        if (total < 99) {
            total++;
            time[total] = d;
            name[total] = n;
            transaction[total] = t;
            balan[total] = b;
        }
    }
}
