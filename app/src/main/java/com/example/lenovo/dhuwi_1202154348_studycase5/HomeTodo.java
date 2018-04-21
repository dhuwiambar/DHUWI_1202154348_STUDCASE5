package com.example.lenovo.dhuwi_1202154348_studycase5;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

import java.util.ArrayList;

public class HomeTodo extends AppCompatActivity {
    databasenya db; RecyclerView rc;
    todoadapter adapter;ArrayList<itemtodo> listitemnya;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_todo);

        //Menginisialisasi objek yang akan digunakan
        rc = findViewById(R.id.listtodo);
        listitemnya = new ArrayList<> ();

        db = new databasenya(this);
        db.getAllItems(listitemnya);

        //Retreive SharedPreference
        SharedPreferences pref = this.getApplicationContext().getSharedPreferences("pref", 0);
        int warna = pref.getInt("background", R.color.putih);

        //Menentukan adapter untuk recyclerview
        adapter = new todoadapter(this, listitemnya, warna);
        rc.setHasFixedSize(true);
        rc.setLayoutManager(new LinearLayoutManager(this));
        rc.setAdapter(adapter);

        //Menjalankan method
        initswipe();
    }

    //Method untuk menambhkan ItemTouchHelper pada RecyclerView
    public void initswipe(){
        ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(0,  ItemTouchHelper.LEFT|ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
                return false;
            }

            //Method ketika recyclerview digeser
            @Override
            public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
                int pos = viewHolder.getAdapterPosition();
                itemtodo cur = adapter.getItem(pos);

                if(direction==ItemTouchHelper.LEFT||direction==ItemTouchHelper.RIGHT){
                    if(db.deletedata(cur.getName())){
                        adapter.removeitem(pos);
                        Snackbar.make(findViewById(R.id.roothome), "Item deleted", 1500).show();
                    }
                }
            }
        };
        ItemTouchHelper helper = new ItemTouchHelper(callback);
        helper.attachToRecyclerView(rc);
    }

    //Method ketika menu dibuat
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    //Method ketika item pada menu dipilih
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if(id==R.id.menusetting){
            startActivity(new Intent(HomeTodo.this, SettingTodo.class));
            finish();
        }
        return true;
    }

    //Method untuk berpindah ke activity adding
    public void tambahin(View view) {
        startActivity(new Intent(this, AddingTodo.class));
        finish();
    }
}