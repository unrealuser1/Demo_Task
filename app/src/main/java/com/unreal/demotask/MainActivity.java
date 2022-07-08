package com.unreal.demotask;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    RecyclerView rv;
    ArrayList<String> userID, name, number;
    DBHelper DB;
    CustomAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        rv = findViewById(R.id.rv_user_list);
        DB = new DBHelper(this);
        name = new ArrayList<>();
        number = new ArrayList<>();
        userID = new ArrayList<>();
        adapter = new CustomAdapter(this, userID, name, number);
        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this));
        displayData();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.app_bar_logout:
                goToLogin();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void goToLogin() {
        Intent toLogin = new Intent(this, LoginActivity.class);
        startActivity(toLogin);
    }

    private void displayData() {
        Cursor cursor = DB.getdata();
        if (cursor.getCount()==0){
            Toast.makeText(this, "No entry exists", Toast.LENGTH_SHORT).show();
        }else{
            while(cursor.moveToNext()){
                userID.add(cursor.getString(0));
                name.add(cursor.getString(1));
                number.add(cursor.getString(2));
            }
        }
    }
}