package com.example.lenovo.dhuwi_1202154348_studycase5;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class AddingTodo extends AppCompatActivity {
    EditText name, description, priority;
    databasenya db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_adding_todo);
//Menginisialisasi objek yang digunakan
        name = findViewById(R.id.inputname);
        description = findViewById(R.id.inputdescription);
        priority = findViewById(R.id.inputpriority);
        db = new databasenya(this);
    }

    //Method ketika tombol back ditekan
    @Override
    public void onBackPressed() {
        startActivity(new Intent(AddingTodo.this, HomeTodo.class));
        this.finish();
    }

    //Method ketika tombol tambah ditekan
    public void nambahtodo(View view) {
        if (db.insertdata(new itemtodo(name.getText().toString(), description.getText().toString(), priority.getText().toString()))) {
            Toast.makeText(this, "Todo added", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(AddingTodo.this, HomeTodo.class));
            this.finish();
        } else {
            Toast.makeText(this, "Adding todo failed", Toast.LENGTH_SHORT).show();
            name.setText(null); description.setText(null); priority.setText(null);
        }
    }
}