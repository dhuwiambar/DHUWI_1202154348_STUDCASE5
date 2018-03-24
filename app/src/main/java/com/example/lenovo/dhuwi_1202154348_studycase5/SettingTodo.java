package com.example.lenovo.dhuwi_1202154348_studycase5;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;

public class SettingTodo extends AppCompatActivity {
    int warna; TextView warnanya;
    AlertDialog.Builder bld; SharedPreferences.Editor edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting_todo);

        //Menginisialisasi objek yang digunakan
        bld = new AlertDialog.Builder(this);

        //Mendapatkan SharedPreference dan menentukan editor untuk SharedPreference
        SharedPreferences pref = getApplicationContext().getSharedPreferences("pref", 0);
        edit = pref.edit();
        warna = pref.getInt("background", R.color.putih);
        warnanya = findViewById(R.id.warnanya); warnanya.setText(getwarna(warna));
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId()==android.R.id.home){
            this.onBackPressed();
        }
        return true;
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(SettingTodo.this, HomeTodo.class));
        finish();
    }

    //Method untuk menampilkan dialog memilih warna
    public void bukadialogwarna(View view) {
        bld.setTitle("Choose Color");
        View v = getLayoutInflater().inflate(R.layout.colordialog, null);
        bld.setView(v);

        //Menentukan radiobutton yang dipilih
        final RadioGroup rg = v.findViewById(R.id.rg);
        rg.check(getIntCOlor(warna));

        //Method ketika menekan OK
        bld.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                int check = rg.getCheckedRadioButtonId();
                switch (check){
                    case R.id.warnabiru:
                        warna = R.color.biru;
                        break;
                    case R.id.warnahijau:
                        warna = R.color.hijau;
                        break;
                    case R.id.warnamerah:
                        warna = R.color.merah;
                        break;
                    case R.id.warnaputih:
                        warna = R.color.putih;
                        break;
                }

                //Mengatur SharedPreference dan mengubah text
                warnanya.setText(getwarna(warna));
                edit.putInt("background", warna);
                edit.commit();
            }
        });

        //Method ketika menekan Cancel
        bld.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                dialogInterface.dismiss();
            }
        });

        //Menampilkan dialog
        bld.create().show();
    }

    //Method untuk mendapatkan String warna
    public String getwarna(int i){
        if(i==R.color.hijau){
            return "Green";
        }else if(i==R.color.biru){
            return "Blue";
        }else if(i==R.color.merah){
            return "Red";
        }else{
            return "White";
        }
    }
    //Method untuk mendapatkan id warna
    public int getIntCOlor(int i){
        if(i==R.color.hijau){
            return R.id.warnahijau;
        }else if(i==R.color.biru){
            return R.id.warnabiru;
        }else if(i==R.color.merah){
            return R.id.warnamerah;
        }else{
            return R.id.warnaputih;
        }
    }
}

